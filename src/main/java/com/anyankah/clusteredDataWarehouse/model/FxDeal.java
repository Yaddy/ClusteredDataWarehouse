package com.anyankah.clusteredDataWarehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FxDeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @NotNull(message ="Field cannot be null")
    @Column(name = "from_currency", nullable = false)
    private Currency orderingCurrency;

    @NotNull(message ="Field cannot be null")
    @Column(name = "", nullable = false)
    private Currency convertingCurrency;

    @Column(name = "fxDeal_timestamp", nullable = false)
    private LocalDateTime orderTimeStamp;

    @Column(name ="fxDeal_amount", precision = 16)
    private BigDecimal amount;

}
