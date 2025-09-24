package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "assets", uniqueConstraints = @UniqueConstraint(columnNames = {"ticker_symbol"}))
public class Asset {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "asset_id", updatable = false, nullable = false)
    private UUID assetId;

    @Column(name = "ticker_symbol", nullable = false, unique = true)
    private String tickerSymbol;

    @Column(name = "asset_type")
    private String assetType;

    @Column(name = "name")
    private String name;

    @Column(name = "amount_invested", nullable = false)
    private BigDecimal amountInvested;

    @Column(name = "currency")
    private String currency = "INR";

    @Column(name = "current_price", nullable = false)
    private BigDecimal currentPrice;

    @Column(name = "current_value", nullable = false)
    private BigDecimal currentValue;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "units", nullable = false)
    private BigDecimal units;
}

