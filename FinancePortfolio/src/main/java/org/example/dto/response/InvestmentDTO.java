package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvestmentDTO {

    private UUID investmentId;
    private PortFolioDTO portfolio;
    private AssetResponseDTO asset;
    private String assetName;
    private Integer amountInvested;
    private Integer units;
    private Integer buyPrice;
    private String currency;
    private LocalDateTime purchaseDate;

}
