package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.repositories.CurrencyRepository;

import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.CurrencyService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

  @Autowired
  private CurrencyRepository currencyRepository;

  @Override
  public List<Currency> getAllCurrencies() {
    return currencyRepository.findAll();
  }

  @Override
  public Currency getCurrencyById(Long id) throws ResourceNotFoundException {
    return currencyRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException("Currency with id " + id + " not found"));
  }

  @Override
  public Currency getCurrencyByCharCode(String charCode) throws ResourceNotFoundException {
    return currencyRepository.findByCharCode(charCode).orElseThrow(
        () -> new ResourceNotFoundException("Currency with char code " + charCode + " not found"));
  }

  @Transactional
  @Override
  public void saveCurrencyAll(List<Currency> currencies) {
    currencyRepository.saveAll(currencies);
  }

  @Transactional
  @Override
  public void upsertCurrencyAll(List<Currency> currencies) {
    currencyRepository.upsertCurrencyAll(currencies);
  }

}
