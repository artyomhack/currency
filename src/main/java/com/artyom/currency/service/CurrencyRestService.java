package com.artyom.currency.service;

import com.artyom.currency.CurrencyApp;
import com.artyom.currency.dto.CreateCurrency;
import com.artyom.currency.dto.CurrencyInfo;
import com.artyom.currency.dto.UpdateCurrency;

import java.util.List;

public interface CurrencyRestService {
    CurrencyInfo create(CreateCurrency currency);

    void updateById(Integer id, UpdateCurrency currency);

    CurrencyInfo getCurrencyById(Integer id);

    CurrencyInfo getCurrencyByCode(String code);

    List<CurrencyInfo> getCurrencies();

    void deleteById(Integer id);
}
