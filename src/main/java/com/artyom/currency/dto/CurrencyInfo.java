package com.artyom.currency.dto;

import com.artyom.currency.entity.Currency;
import lombok.Getter;
import lombok.Setter;

public record CurrencyInfo(
        @Setter
        @Getter
        Integer id,
        @Setter
        @Getter
        String code,
        @Setter
        @Getter
        String name,
        @Setter
        @Getter
        String sign) {
    public static CurrencyInfo from(Currency currency) {
        return new CurrencyInfo(currency.getId(), currency.getCode(), currency.getFullName(), currency.getSign());
    }
}
