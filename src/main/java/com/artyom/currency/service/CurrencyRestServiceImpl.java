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
import java.util.stream.StreamSupport;

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
        currencyRepository.findById(id).ifPresentOrElse(it -> {
            it.setCode(response.code());
            it.setSign(response.sign());
            it.setFullName(response.name());
        }, () -> {throw new NoSuchElementException("Данной валюты не существует");});
    }

    @Override
    public CurrencyInfo fetchById(Integer id) {
        return CurrencyInfo.from(currencyRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Данной валюты не существует")));
    }

    @Override
    public CurrencyInfo fetchByCode(String code) {
        return CurrencyInfo.from(currencyRepository.findByCode(code).orElseThrow(() ->
                new NoSuchElementException("Валюты с таким кодом не существует")));
    }

    @Override
    public List<CurrencyInfo> fetchAll() {
        return StreamSupport.stream(currencyRepository.findAll().spliterator(), false)
                .map(CurrencyInfo::from)
                .toList();
    }

    @Override
    public void deleteById(Integer id) {
        currencyRepository.deleteById(id);
    }
}
