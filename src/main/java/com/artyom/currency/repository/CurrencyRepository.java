package com.artyom.currency.repository;

import com.artyom.currency.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Optional<Currency> findById(Integer id);

    @Query("SELECT mc FROM Currency mc where mc.code = :code")
    Optional<Currency> findByCode(@Param("code") String code);

    List<Currency> findAll();
}
