package com.anyankah.clusteredDataWarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FxDealOrderDTO {


    @Id
    private String id;

    private Currency orderingCurrency;

    private Currency convertingCurrency;

    private LocalDateTime orderTimeStamp;

    private BigDecimal amount;
}
