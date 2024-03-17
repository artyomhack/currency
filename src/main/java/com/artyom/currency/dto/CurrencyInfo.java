package com.artyom.currency.dto;

import java.math.BigDecimal;

public record CurrencyInfo(String code, BigDecimal price) {
}
