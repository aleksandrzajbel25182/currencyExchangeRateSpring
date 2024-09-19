package com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;

import java.util.List;

public interface CurrencyService {

  List<Currency> getAllCurrencies();

  Currency getCurrencyById(Long id) throws ResourceNotFoundException;

  Currency getCurrencyByCharCode(String charCode)
      throws ResourceNotFoundException, ResourceNotCorrectException;

  void saveCurrencyAll(List<Currency> currencies);

  void upsertCurrencyAll(List<Currency> currencies);

  List<Currency> findCurrenciesByCharCodeIn(List<String> charCodes);
}
