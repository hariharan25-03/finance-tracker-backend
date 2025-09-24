package org.example.dto.response;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
public class PortfolioInsightDTO {
    private UUID portfolioId;
    private String portfolioName;
    private BigDecimal diversificationScore;
    private String recommendation;
    private List<AssetResponseDTO> assets;
}

