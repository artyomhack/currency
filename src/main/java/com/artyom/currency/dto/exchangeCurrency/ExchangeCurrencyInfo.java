package com.artyom.currency.dto.exchangeCurrency;

import com.artyom.currency.dto.currency.CurrencyInfo;
import com.artyom.currency.entity.ExchangeCurrency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record ExchangeCurrencyInfo(
        Integer id,
        CurrencyInfo baseCurrency,
        CurrencyInfo targetCurrency,
        BigDecimal rate
) {
    public static ExchangeCurrencyInfo from(ExchangeCurrency exchangeCurrency) {
        return new ExchangeCurrencyInfo(
                exchangeCurrency.getId(),
                CurrencyInfo.from(exchangeCurrency.getBaseId()),
                CurrencyInfo.from(exchangeCurrency.getTargetId()),
                exchangeCurrency.getRate()
        );
    }
}
