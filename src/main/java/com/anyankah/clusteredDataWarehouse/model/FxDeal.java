package com.anyankah.clusteredDataWarehouse.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FxDeal {

    @Id
    @Column
    private String id;

    @NotNull(message ="Field cannot be null")
    @Column(name = "from_currency", nullable = false)
    private Currency fromCurrency;

    @NotNull(message ="Field cannot be null")
    @Column(name = "", nullable = false)
    private Currency toCurrency;

    @Column(name = "fxDeal_timestamp", nullable = false)
    private LocalDateTime orderTimeStamp;

    @Column(name ="fxDeal_amount", precision = 16)
    private BigDecimal amount;

}
