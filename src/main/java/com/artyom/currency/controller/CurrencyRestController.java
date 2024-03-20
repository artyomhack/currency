package com.artyom.currency.controller;

import com.artyom.currency.dto.currency.CreateCurrency;
import com.artyom.currency.dto.currency.CurrencyInfo;
import com.artyom.currency.service.currency.CurrencyRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
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
                .replacePath("redirect:/currencies")
                .build()
                .toUri()
        ).body(currencyInfo);
    }

    @GetMapping("/currencies")
    public ResponseEntity<?> getCurrencies() {
        var list = currencyRestService.fetchAll();

        if (list.isEmpty()) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "База данных недоступна"
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/currencies/{code}")
    public ResponseEntity<?> getCurrencyByCode(@PathVariable(name = "code") String code) {
        var currencyInfo = currencyRestService.fetchByCode(code);

        return new ResponseEntity<>(currencyInfo, HttpStatus.OK);
    }
}
