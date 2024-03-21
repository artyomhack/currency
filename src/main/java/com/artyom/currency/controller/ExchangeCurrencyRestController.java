package com.artyom.currency.controller;

import com.artyom.currency.dto.exchangeCurrency.CreateExchangeCurrency;
import com.artyom.currency.dto.exchangeCurrency.ExchangeCurrencyInfo;
import com.artyom.currency.service.exchangeCurrency.ExchangeCurrencyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchangeRates")
public class ExchangeCurrencyRestController {

    private final ExchangeCurrencyRestService exchangeCurrencyRestService;

    @GetMapping()
    public ResponseEntity<?> getExchangeCurrencies() {
        var list = exchangeCurrencyRestService.fetchAll();

        if (list.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "База данных недоступна"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createExchangeCurrency(@RequestBody CreateExchangeCurrency response, UriComponentsBuilder componentsBuilder) {
        var exchangeCurrencyInfo = exchangeCurrencyRestService.create(response);
        return ResponseEntity.created(componentsBuilder
                .replacePath("redirect:/exchangeRates")
                .build()
                .toUri()
        ).body(exchangeCurrencyInfo);
    }

    @GetMapping("/{baseAndTargetCode}")
    public ResponseEntity<?> getExchangeCurrencyByBaseAndTargetCode(@PathVariable String baseAndTargetCode) {

        var exchangeCurrencyInfo = exchangeCurrencyRestService.fetchByBaseAndTargetCurrencyCode(baseAndTargetCode);

        return new ResponseEntity<>(exchangeCurrencyInfo, HttpStatus.OK);
    }
}
