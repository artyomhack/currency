package com.artyom.currency.dto;

import com.artyom.currency.entity.Currency;

public record CurrencyInfo(
        Integer id,
        String code,
        String name,
        String sign) {
    public static CurrencyInfo from(Currency currency) {
        return new CurrencyInfo(currency.getId(), currency.getCode(), currency.getFullName(), currency.getSign());
    }
}
