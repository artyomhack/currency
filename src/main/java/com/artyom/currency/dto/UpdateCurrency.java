package com.artyom.currency.dto;

import com.artyom.currency.entity.Currency;
import lombok.Getter;
import lombok.Setter;


public record UpdateCurrency(
        @Setter
        @Getter
        String code,
        @Setter
        @Getter
        String name,
        @Setter
        @Getter
        String sign) {
    public static UpdateCurrency from(Currency currency) {
        return new UpdateCurrency(currency.getCode(), currency.getFullName(), currency.getSign());
    }

}
