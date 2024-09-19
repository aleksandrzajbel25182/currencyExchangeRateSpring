package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.repositories.CurrencyRepository;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.CurrencyService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Autowired
  private RedisTemplate redisTemplate;

  @Override
  public List<Currency> getAllCurrencies() {
    return currencyRepository.findAll();
  }

  @Override
  public Currency getCurrencyById(Long id) throws ResourceNotFoundException {
    return currencyRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Currency with id " + id + " not found"));
  }

  @Cacheable(value = "getCurrencyByCharCode", key = "#charCode")
  @Override
  public Currency getCurrencyByCharCode(String charCode)
      throws ResourceNotFoundException, ResourceNotCorrectException {
    if (charCode.length() != 3) {
      throw new ResourceNotCorrectException("Char code must be exactly 3 characters. Example: USD");
    }
    return currencyRepository.findByCharCode(charCode.toUpperCase()).orElseThrow(
        () -> new ResourceNotFoundException(
            "Currency with char code " + charCode + " not found"));
  }

  @Transactional
  @Override
  public void saveCurrencyAll(List<Currency> currencies) {
    currencyRepository.saveAll(currencies);
  }

  @Transactional
  @Override
  public void upsertCurrencyAll(List<Currency> currencies) {
    for (Currency currency : currencies) {
      currencyRepository.upsertCurrency(currency);
    }
  }

  @Override
  public List<Currency> findCurrenciesByCharCodeIn(List<String> charCodes) {
    return currencyRepository.findCurrenciesByCharCodeIn(charCodes);
  }

}
