package com.example.currencyexchangerate.currency_exchange_rate_spring.controller;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.CurrencyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

  @Autowired
  private CurrencyService currencyService;

  @GetMapping("/currencies")
  public ResponseEntity<List<Currency>> getAllCurrencies() {
    return ResponseEntity.ok(currencyService.getAllCurrencies());
  }

  @GetMapping("/currency/{id}")
  public ResponseEntity<Currency> getCurrencyById(@PathVariable Long id)
      throws ResourceNotFoundException {
    return ResponseEntity.ok(currencyService.getCurrencyById(id));
  }

  @GetMapping("/currencyByCharCode/{charCode}")
  public ResponseEntity<Currency> getCurrencyByCharCode(@PathVariable String charCode)
      throws ResourceNotFoundException, ResourceNotCorrectException {
    return ResponseEntity.ok(currencyService.getCurrencyByCharCode(charCode));
  }
}
