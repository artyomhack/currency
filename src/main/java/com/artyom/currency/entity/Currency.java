package com.artyom.currency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private String fullName;

    @Setter
    private String sign;

    @OneToMany
    public List<ExchangeCurrency> exchangeCurrencies;

    public Currency() {
    }

    public Currency(Integer id, String code, String fullName, String sign, List<ExchangeCurrency> exchangeCurrencies) {
        this.id = id;
        this.code = code;
        this.fullName = fullName;
        this.sign = sign;
        this.exchangeCurrencies = exchangeCurrencies;
    }

    public static Currency newOf(String code, String fullName, String sign) {
        return new Currency(null, code, fullName, sign, List.of());
    }
}
