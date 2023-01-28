package com.anyankah.clusteredDataWarehouse.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FxDealOrderDTO {


    private String id;

    private Currency orderingCurrency;

    private Currency convertingCurrency;

    private LocalDateTime orderTimeStamp;

    private BigDecimal amount;
}
