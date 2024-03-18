package com.artyom.currency.controller;

import com.artyom.currency.dto.CreateCurrency;
import com.artyom.currency.dto.CurrencyInfo;
import com.artyom.currency.service.CurrencyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyRestService currencyRestService;

    @PostMapping("/currencies")
    public ResponseEntity<?> createCurrencies(@RequestBody CreateCurrency response, UriComponentsBuilder componentsBuilder) {
        var currencyInfo = currencyRestService.create(response);
        return ResponseEntity.created(componentsBuilder
                .replacePath("/currencies")
                .build()
                .toUri()
        ).body(currencyInfo);
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyInfo>> getCurrencies() {
        return ResponseEntity.ok(currencyRestService.fetchAll());
    }
}
