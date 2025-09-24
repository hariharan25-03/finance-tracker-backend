package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.DefaultHttpRequestRetryStrategy;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.util.TimeValue;
import org.example.entity.Asset;
import org.example.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class AssetPriceService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    @Autowired
    private AssetRepository assetRepository;

    private final RestTemplate restTemplate = restTemplate();

    @Scheduled(fixedRateString = "${asset.update.rate}")
    public void updateAssetPrices() {
        List<Asset> assets = assetRepository.findAll();
        for (Asset asset : assets) {
            String url = buildApiUrl(asset.getTickerSymbol(), asset.getAssetType());
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            BigDecimal price = extractPrice(response.getBody(), asset.getAssetType());
            if(price.intValue()!=0)
            {
                asset.setCurrentValue(price);
                asset.setCurrentPrice(price);
            }
            asset.setLastUpdated(LocalDateTime.now());
            assetRepository.save(asset);
        }
    }

    private String buildApiUrl(String ticker, String type) {
        if(type.equals("Crypto")) {
            return "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency="
                    + ticker + "&to_currency=INR&apikey=" + apiKey;
        }
        return "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="
                + ticker + "&apikey=" +apiKey;
    }

    private BigDecimal extractPrice(String jsonResponse, String type) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(jsonResponse);

            if (root.has("Note")) {
                System.err.println(" API Limit: " + root.get("Note").asText());
                return BigDecimal.ZERO;
            }
            if (root.has("Error Message")) {
                System.err.println("API Error: " + root.get("Error Message").asText());
                return BigDecimal.ZERO;
            }

            if ("Crypto".equalsIgnoreCase(type)) {
                JsonNode rateNode = root.path("Realtime Currency Exchange Rate").path("5. Exchange Rate");
                if (rateNode.isTextual()) {
                    return new BigDecimal(rateNode.asText());
                } else {
                    System.err.println("Crypto price not found in response: " + jsonResponse);
                }
            } else {
                JsonNode priceNode = root.path("Global Quote").path("05. price");
                if (priceNode.isTextual()) {
                    return new BigDecimal(priceNode.asText());
                } else {
                    System.err.println(" Stock price not found in response: " + jsonResponse);
                }
            }
        } catch (Exception e) {
            System.err.println("Error parsing price: " + e.getMessage());
        }
        return BigDecimal.ZERO;
    }



    @Bean
    public RestTemplate restTemplate() {
        DefaultHttpRequestRetryStrategy retryStrategy = new DefaultHttpRequestRetryStrategy(3, TimeValue.ofSeconds(2));

        CloseableHttpClient httpClient = HttpClients.custom()
                .setRetryStrategy(retryStrategy)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }

}

