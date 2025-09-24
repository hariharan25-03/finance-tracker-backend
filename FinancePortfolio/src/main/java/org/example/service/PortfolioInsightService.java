package org.example.service;

import org.example.dto.response.AssetResponseDTO;
import org.example.dto.response.PortfolioInsightDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
public class PortfolioInsightService {

    // Mock method to calculate diversification score (0-100)
    public BigDecimal calculateDiversificationScore(List<AssetResponseDTO> assets) {
        if (assets == null || assets.isEmpty()) {
            return BigDecimal.ZERO;
        }

        // Example logic: more asset types = higher score
        long distinctTypes = assets.stream()
                .map(AssetResponseDTO::getAssetType)
                .distinct()
                .count();

        // Score = number of distinct asset types * 20, capped at 100
        BigDecimal score = BigDecimal.valueOf(distinctTypes * 20);
        if (score.compareTo(BigDecimal.valueOf(100)) > 0) {
            score = BigDecimal.valueOf(100);
        }
        return score.setScale(2, RoundingMode.HALF_UP);
    }

    // Mock recommendation based on missing asset types
    public String recommendAsset(List<AssetResponseDTO> assets) {
        List<String> allTypes = List.of("Stock", "ETF", "Mutual Fund", "Bond", "Cryptocurrency", "REIT", "Cash");

        // Find missing types
        List<String> presentTypes = assets.stream()
                .map(AssetResponseDTO::getAssetType)
                .distinct()
                .toList();

        for (String type : allTypes) {
            if (!presentTypes.contains(type)) {
                return "Consider adding a " + type + " to improve diversification";
            }
        }
        return "Portfolio is well diversified!";
    }

    // Combine into insight DTO
    public PortfolioInsightDTO generateInsight(UUID portfolioId, String portfolioName, List<AssetResponseDTO> assets) {
        PortfolioInsightDTO insight = new PortfolioInsightDTO();
        insight.setPortfolioId(portfolioId);
        insight.setPortfolioName(portfolioName);
        insight.setAssets(assets);
        insight.setDiversificationScore(calculateDiversificationScore(assets));
        insight.setRecommendation(recommendAsset(assets));
        return insight;
    }
}

