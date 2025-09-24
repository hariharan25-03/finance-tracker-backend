package org.example.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InvestmentRequestDTO {
    private UUID portfolioId;
    private UUID assetId;
    private Integer units;
    private BigDecimal buyPrice;
    private String currency = "INR";
    private LocalDateTime purchaseDate;
}



