package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.repositories.CurrencyRepository;
import com.example.currencyexchangerate.currency_exchange_rate_spring.repositories.ExchangeRateRepository;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeRateService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

  @Autowired
  private CurrencyRepository currencyRepository;

  @Override
  public List<ExchangeRate> getAllExchangeRates() {
    return exchangeRateRepository.findAll();
  }

  @Override
  public ExchangeRate getExchangeRateById(Long id) throws ResourceNotFoundException {
    return exchangeRateRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Exchanger rate with id " + id + " not found"));
  }

  @Override
  public ExchangeRate findExchangeRateByBaseCurrencyAndTargetCurrency(String baseCurrency,
      String targetCurrency)
      throws ResourceNotFoundException {
    return exchangeRateRepository.findExchangeRateByBaseCurrencyAndTargetCurrency(baseCurrency,
            targetCurrency)
        .orElseThrow(
            () -> new ResourceNotFoundException(
                "Exchanger rate with id " + baseCurrency + " not found"));
  }

  @Override
  public void saveExchangeRateAll(List<ExchangeRate> exchangeRates) {
    exchangeRateRepository.saveAll(exchangeRates);
  }

  @Transactional
  @Override
  public void upsertExchangeRateAll(List<ExchangeRate> exchangeRates) {
    for (ExchangeRate exchangerate : exchangeRates) {
      exchangeRateRepository.upsertExchangeRate(exchangerate);
    }
  }
}
