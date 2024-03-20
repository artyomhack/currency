package com.artyom.currency.dto.currency;

import com.artyom.currency.entity.Currency;


public record UpdateCurrency(
        String code,
        String name,
        String sign) {
    public static UpdateCurrency from(Currency currency) {
        return new UpdateCurrency(currency.getCode(), currency.getFullName(), currency.getSign());
    }

}
