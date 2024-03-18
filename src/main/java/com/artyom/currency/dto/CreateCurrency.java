package com.artyom.currency.dto;

import lombok.Getter;
import lombok.Setter;


public record CreateCurrency(
        @Setter
        @Getter
        String code,
        @Setter
        @Getter
        String name,
        @Setter
        @Getter
        String sign) {
}
