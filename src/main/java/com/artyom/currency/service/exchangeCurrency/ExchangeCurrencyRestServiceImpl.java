package com.artyom.currency.service.exchangeCurrency;

import com.artyom.currency.dto.exchangeCurrency.CreateExchangeCurrency;
import com.artyom.currency.dto.exchangeCurrency.ExchangeCurrencyInfo;
import com.artyom.currency.entity.Currency;
import com.artyom.currency.entity.ExchangeCurrency;
import com.artyom.currency.repository.CurrencyRepository;
import com.artyom.currency.repository.ExchangeCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ExchangeCurrencyRestServiceImpl implements ExchangeCurrencyRestService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeCurrencyRepository exchangeCurrencyRepository;

    @Override
    public ExchangeCurrencyInfo create(CreateExchangeCurrency response) {
        var base = currencyRepository.findByCode(response.baseCurrency().code()).orElseThrow(() ->
                new NoSuchElementException("Валюты " + response.baseCurrency().code() + " нет в базе данных"));

        var target = currencyRepository.findByCode(response.targetCurrency().code()).orElseThrow(() ->
                new NoSuchElementException("Валюты " + response.targetCurrency().code() + " нет в базе данных"));

        var entity = exchangeCurrencyRepository.save(ExchangeCurrency.newOf(base, target, new BigDecimal(response.rate())));

        return ExchangeCurrencyInfo.from(entity);
    }

    @Override
    public List<ExchangeCurrencyInfo> fetchAll() {
        return StreamSupport.stream(exchangeCurrencyRepository.findAll().spliterator(), false)
                .map(ExchangeCurrencyInfo::from)
                .toList();
    }
}
