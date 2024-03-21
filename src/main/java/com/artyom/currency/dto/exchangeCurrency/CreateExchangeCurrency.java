package com.artyom.currency.dto.exchangeCurrency;

import com.artyom.currency.dto.currency.CreateCurrency;

import java.math.BigDecimal;

public record CreateExchangeCurrency(
        CreateCurrency baseCurrency,
        CreateCurrency targetCurrency,
        BigDecimal rate
) {

}
