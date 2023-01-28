package com.anyankah.clusteredDataWarehouse.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FxDealOrderDTO {

    private Long id;

    @NotBlank(message = "valid currency code is required")
    private String orderingCurrency;

    @NotBlank(message = "valid currency code is required")
    private String convertingCurrency;

    @NotNull
    private LocalDateTime orderTimeStamp = LocalDateTime.now();

    @Min(value = 1, message = "amount should not be empty")
    private BigDecimal amount;
}
