package com.artyom.currency.repository;

import com.artyom.currency.entity.ExchangeCurrency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeCurrencyRepository extends CrudRepository<ExchangeCurrency, Integer> {
}
