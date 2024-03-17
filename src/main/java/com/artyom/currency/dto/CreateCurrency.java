package com.artyom.currency.dto;

import java.math.BigDecimal;

public record CreateCurrency(String code, BigDecimal price) {
}
