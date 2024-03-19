package com.artyom.currency.controller;

import com.artyom.currency.dto.CreateCurrency;
import com.artyom.currency.dto.CurrencyInfo;
import com.artyom.currency.service.CurrencyRestService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyRestService currencyRestService;

    @PostMapping("/currencies")
    public ResponseEntity<?> createCurrency(@RequestBody CreateCurrency response, UriComponentsBuilder componentsBuilder) {
        var currencyInfo = currencyRestService.create(response);
        return ResponseEntity.created(componentsBuilder
                .replacePath("/currencies")
                .build()
                .toUri()
        ).body(currencyInfo);
    }

    @GetMapping("/currencies")
    public ResponseEntity<List<CurrencyInfo>> getCurrencies() {
        var list = currencyRestService.fetchAll();

        if (list.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/currencies/{code}")
    public ResponseEntity<?> getCurrencyByCode(@PathVariable(name = "code") String code) {
        var currencyInfo = currencyRestService.fetchByCode(code);

        return new ResponseEntity<>(currencyInfo, HttpStatus.OK);
    }
}
