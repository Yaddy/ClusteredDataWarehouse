package com.anyankah.clusteredDataWarehouse.utils.validator;

import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
public class FxDealValidator {
    public boolean isISOCurrencyCodeNotValid(String currencyCode) {
        return Currency.getAvailableCurrencies().stream().noneMatch(c -> c.getCurrencyCode().equals(currencyCode));
    }

}
