package com.artyom.currency.dto.currency;

import lombok.Getter;
import lombok.Setter;


public record CreateCurrency(
        String code,
        String name,
        String sign) {
}
