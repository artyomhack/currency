package com.artyom.currency.entity;

import com.artyom.currency.dto.exchangeCurrency.CreateExchangeCurrency;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@Getter
@Entity
@Table(schema = "money", name= "exchange_currency")
public class ExchangeCurrency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "base_id")
    private Currency baseId;

    @ManyToOne
    @JoinColumn(name = "target_id")
    private Currency targetId;

    private BigDecimal rate;

    public ExchangeCurrency() {
    }

    public ExchangeCurrency(Integer id, Currency baseId, Currency targetId, BigDecimal rate) {
        this.id = id;
        this.baseId = baseId;
        this.targetId = targetId;
        this.rate = rate;
    }

    public ExchangeCurrency(Integer id, Currency baseId, Currency targetId, String rate) {
        this.id = id;
        this.baseId = baseId;
        this.targetId = targetId;
        this.rate = new BigDecimal(rate);
    }

    public static ExchangeCurrency newOf(Currency base, Currency target, BigDecimal rate) {
        return new ExchangeCurrency(null, base, target, rate);
    }

    public static ExchangeCurrency fromResponse(CreateExchangeCurrency exchangeCurrency) {
        return new ExchangeCurrency(
                null,
                Currency.newOf(exchangeCurrency.baseCurrency()),
                Currency.newOf(exchangeCurrency.targetCurrency()),
                exchangeCurrency.rate()
        );
    }
}
