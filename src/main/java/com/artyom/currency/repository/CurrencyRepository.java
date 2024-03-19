package com.artyom.currency.repository;

import com.artyom.currency.entity.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    @Query(value = "select * from money.currency where code = ?1", nativeQuery = true)
    Optional<Currency> findByCode(String code);

}
