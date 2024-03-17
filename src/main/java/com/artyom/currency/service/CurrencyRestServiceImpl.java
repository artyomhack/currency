package com.artyom.currency.service;

import com.artyom.currency.dto.CreateCurrency;
import com.artyom.currency.dto.CurrencyInfo;
import com.artyom.currency.dto.UpdateCurrency;
import com.artyom.currency.entity.Currency;
import com.artyom.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrencyRestServiceImpl implements CurrencyRestService {

    private final CurrencyRepository currencyRepository;

    @Override
    public CurrencyInfo create(CreateCurrency currency) {
        var entity  = currencyRepository.save(Currency.newOf(currency.code(), currency.price()));

        return new CurrencyInfo(entity.getCode(), entity.getPrice());
    }

    @Override
    public void updateById(Integer id, UpdateCurrency currency) {
        currencyRepository.findById(id).ifPresentOrElse(it -> {
            it.setCode(currency.code());
            it.setPrice(currency.price());
        }, () -> {throw new NoSuchElementException("Такой валюты нет.");});
    }

    @Override
    public CurrencyInfo getCurrencyById(Integer id) {
        var entity = currencyRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Такой валюты нет."));

        return new CurrencyInfo(entity.getCode(), entity.getPrice());
    }

    @Override
    public CurrencyInfo getCurrencyByCode(String code) {
        var entity = currencyRepository.findByCode(code).orElseThrow(() ->
                new NoSuchElementException("Такой валюты нет."));

        return new CurrencyInfo(entity.getCode(), entity.getPrice());
    }

    @Override
    public List<CurrencyInfo> getCurrencies() {
        return currencyRepository.findAll().stream()
                .map(it -> new CurrencyInfo(it.getCode(), it.getPrice()))
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        currencyRepository.deleteById(id);
    }
}
