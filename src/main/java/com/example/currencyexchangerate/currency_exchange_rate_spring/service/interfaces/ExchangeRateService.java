package com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces;


import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import java.util.List;

public interface ExchangeRateService {

  List<ExchangeRate> getAllExchangeRates();

  ExchangeRate getExchangeRateById(Long id) throws ResourceNotFoundException;

  ExchangeRate findExchangeRateByBaseCurrencyAndTargetCurrency(String baseCurrency, String targetCurrency)
      throws ResourceNotFoundException;

  void saveExchangeRateAll(List<ExchangeRate> exchangeRates);

  void upsertExchangeRateAll(List<ExchangeRate> exchangeRates);

}
