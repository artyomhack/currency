package com.artyom.currency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Entity
@Table(schema="money", name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    private String code;

    @Setter
    private BigDecimal price;

    @OneToMany
    private List<ExchangeCurrency> historyExchanges;

    public Currency() {
    }

    public Currency(Integer id, String code, BigDecimal price, List<ExchangeCurrency> historyExchanges) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.historyExchanges = historyExchanges;
    }

    public static Currency newOf(String code, BigDecimal price) {
        return new Currency(null, code, price, new ArrayList<>());
    }
}
