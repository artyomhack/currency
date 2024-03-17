package com.artyom.currency.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

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
}
