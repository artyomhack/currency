package com.artyom.currency.service.currency;

import com.artyom.currency.dto.currency.CreateCurrency;
import com.artyom.currency.dto.currency.CurrencyInfo;
import com.artyom.currency.dto.currency.UpdateCurrency;

import java.util.List;

public interface CurrencyRestService {
    CurrencyInfo create(CreateCurrency response);

    void updateById(Integer id, UpdateCurrency response);

    CurrencyInfo fetchById(Integer id);

    CurrencyInfo fetchByCode(String code);

    List<CurrencyInfo> fetchAll();

    void deleteById(Integer id);
}
