package com.artyom.currency.service;

import com.artyom.currency.dto.CreateCurrency;
import com.artyom.currency.dto.CurrencyInfo;
import com.artyom.currency.dto.UpdateCurrency;
import com.artyom.currency.entity.Currency;
import com.artyom.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyRestServiceImpl implements CurrencyRestService {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyInfo create(CreateCurrency response) {
        var currency = Currency.newOf(response.code(), response.name(), response.sign());
        return CurrencyInfo.from(currencyRepository.save(currency));
    }

    @Override
    public void updateById(Integer id, UpdateCurrency response) {

    }

    @Override
    public CurrencyInfo fetchById(Integer id) {
        return null;
    }

    @Override
    public List<CurrencyInfo> fetchAll() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
