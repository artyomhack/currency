package com.artyom.currency.dto;

import java.math.BigDecimal;

public record UpdateCurrency(String code, BigDecimal price) {
}
