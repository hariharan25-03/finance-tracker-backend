package org.example.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
public class AssetResponseDTO {
    private UUID assetId;
    private String tickerSymbol;
    private String assetType;
    private String name;
    private BigDecimal currentPrice;
    private BigDecimal currentValue;
    private BigDecimal units;
    private String currency;
    private LocalDateTime lastUpdated;
}

