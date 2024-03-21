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
import java.util.Objects;
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

        var entity = exchangeCurrencyRepository.save(ExchangeCurrency.newOf(base, target, response.rate()));

        return ExchangeCurrencyInfo.from(entity);
    }

    @Override
    public List<ExchangeCurrencyInfo> fetchAll() {
        return StreamSupport.stream(exchangeCurrencyRepository.findAll().spliterator(), false)
                .map(ExchangeCurrencyInfo::from)
                .toList();
    }

    @Override
    public ExchangeCurrencyInfo fetchByBaseAndTargetCurrencyCode(String baseAndTargetCode) {
        if (baseAndTargetCode.isEmpty() || baseAndTargetCode.isBlank())
            throw new IllegalArgumentException("Коды валют пары отсутствуют в адресе - 400");

        String baseCurrencyCode;
        String targetCurrencyCode;

        try {
            baseAndTargetCode = baseAndTargetCode.toUpperCase();
            baseCurrencyCode = baseAndTargetCode.substring(0, 2);
            targetCurrencyCode = baseAndTargetCode.substring(3, baseAndTargetCode.length() - 1);
        } catch (Exception exception) {
            throw new IllegalArgumentException("База данных недоступна");
        }


        var entity = StreamSupport.stream(exchangeCurrencyRepository.findAll().spliterator(), false)
                .toList()
                .stream()
                .filter(it -> {
                    var base = it.getBaseId();
                    var target = it.getTargetId();
                    return Objects.equals(base.getCode(), baseCurrencyCode) &&
                            Objects.equals(target.getCode(), targetCurrencyCode);
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Обменный курс для пары не найден - 404"));

        return ExchangeCurrencyInfo.from(entity);
    }
}
