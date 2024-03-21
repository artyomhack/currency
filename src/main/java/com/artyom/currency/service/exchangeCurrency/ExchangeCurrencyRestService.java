package com.artyom.currency.service.exchangeCurrency;

import com.artyom.currency.dto.exchangeCurrency.CreateExchangeCurrency;
import com.artyom.currency.dto.exchangeCurrency.ExchangeCurrencyInfo;

import java.util.List;

public interface ExchangeCurrencyRestService {

    ExchangeCurrencyInfo create(CreateExchangeCurrency response);
    List<ExchangeCurrencyInfo> fetchAll();
    ExchangeCurrencyInfo fetchByBaseAndTargetCurrencyCode(String baseAndTargetCode);

}
