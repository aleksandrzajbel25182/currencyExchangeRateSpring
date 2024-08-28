package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.repositories.ExchangeRateRepository;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeRateService;

import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService, ExchangeService {

  @Autowired
  private ExchangeRateRepository exchangeRateRepository;

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
      throws ResourceNotFoundException, ResourceNotCorrectException {
    if (baseCurrency.length() != 3 || targetCurrency.length() != 3) {
      throw new ResourceNotCorrectException("Char code must be exactly 3 characters. Example: USD");
    }
    return exchangeRateRepository.findExchangeRateByBaseCurrencyAndTargetCurrency(
            baseCurrency.toUpperCase(),
            targetCurrency.toUpperCase())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                "The exchange rate of currencies " + baseCurrency + "and " + targetCurrency
                    + " not found"));
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

  @Override
  public BigDecimal getRate(String from, String to) throws ResourceNotFoundException {

    String fromUpperCase = from.toUpperCase();
    String toUpperCase = to.toUpperCase();

    // 1. There is a currency pair AB in the `ExchangeRates` table - we take its rate
    Optional<ExchangeRate> exchangeRate = exchangeRateRepository
        .findExchangeRateByBaseCurrencyAndTargetCurrency(fromUpperCase, toUpperCase);
    if (exchangeRate.isPresent()) {
      return exchangeRate.get().getRate();
    }
    // 2. In the `ExchangeRates` table, there is a currency pair BA - take its rate,
    //    and count the reverse to get AB
    Optional<ExchangeRate> reverseExchangeRate = exchangeRateRepository
        .findExchangeRateByBaseCurrencyAndTargetCurrency(toUpperCase, fromUpperCase);
    if (reverseExchangeRate.isPresent()) {
      return new BigDecimal(1).divide(reverseExchangeRate.get().getRate(), 2, RoundingMode.HALF_UP);
    }

    // 3. In the `ExchangeRates` table, there are currency pairs RUB-A and RUB-B
    //    calculate the AB rate from these rates
    Optional<ExchangeRate> exchangeRateRubToA = exchangeRateRepository
        .findExchangeRateByBaseCurrencyAndTargetCurrency("RUB", fromUpperCase);
    Optional<ExchangeRate> exchangeRateRubToB = exchangeRateRepository
        .findExchangeRateByBaseCurrencyAndTargetCurrency("RUB", toUpperCase);

    if (exchangeRateRubToA.isPresent() && exchangeRateRubToB.isPresent()) {

      BigDecimal rateA = exchangeRateRubToA.get().getRate();
      BigDecimal rateB = exchangeRateRubToB.get().getRate();
      BigDecimal intermediateResult = rateA.divide(rateB, 2, RoundingMode.HALF_UP);
      BigDecimal result = BigDecimal.ONE.divide(intermediateResult, 2, RoundingMode.HALF_UP);
      return result;
    }
    throw new ResourceNotFoundException(
        "Exchange rate not found for currencies " + from + " and " + to);

  }
}
