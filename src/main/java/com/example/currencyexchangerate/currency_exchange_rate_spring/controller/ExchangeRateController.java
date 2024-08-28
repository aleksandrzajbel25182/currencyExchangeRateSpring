package com.example.currencyexchangerate.currency_exchange_rate_spring.controller;

import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.CustomExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeRateService;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController {

  @Autowired
  private ExchangeRateService exchangeRateService;

  @Autowired
  private ExchangeService exchangeService;

  @GetMapping("/exchangerates")
  private ResponseEntity<List<ExchangeRate>> getAllExchangeRate() {
    return ResponseEntity.ok(exchangeRateService.getAllExchangeRates());
  }

  @GetMapping("/exchangerate")
  private ResponseEntity<ExchangeRate> findExchangeRateByBaseCurrencyAndTargetCurrency(
      @RequestParam("base") String baseCharCode, @RequestParam("target") String targetCharCode)
      throws ResourceNotFoundException, ResourceNotCorrectException {
    return ResponseEntity.ok(
        exchangeRateService.findExchangeRateByBaseCurrencyAndTargetCurrency(baseCharCode,
            targetCharCode));
  }

  @GetMapping("/exchangerate/{id}")
  private ResponseEntity<ExchangeRate> getExchangeRateById(@PathVariable Long id)
      throws ResourceNotFoundException {
    return ResponseEntity.ok(exchangeRateService.getExchangeRateById(id));
  }


  @GetMapping("/exchangerate/сalculation")
  private ResponseEntity<CustomExchangeRate> getExchangeRateCalculation(
      @RequestParam("from") String fromCharCode, @RequestParam("to") String toCharCode,
      @RequestParam("amount")
      BigDecimal amount) throws ResourceNotFoundException {

    BigDecimal rate = exchangeService.getRate(fromCharCode, toCharCode);
    return ResponseEntity.ok(new CustomExchangeRate(
        fromCharCode,
        toCharCode,
        rate,
        amount,
        rate.multiply(amount)
    ));
  }

}
