package com.example.currencyexchangerate.currency_exchange_rate_spring.converter;


import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ExchangeRateDto;
import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.CurrencyService;
import com.example.currencyexchangerate.currency_exchange_rate_spring.util.RateCalculator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateConverter {

  @Autowired
  private CurrencyService currencyService;

  public List<ExchangeRate> converterToEntities(List<ExchangeRateDto> exchangeRateDto, Date date)
      throws ResourceNotCorrectException, ResourceNotFoundException {
    var baseCurrencyId = currencyService.getCurrencyByCharCode("RUB");

    List<ExchangeRate> exchangeRates = new ArrayList<>();
    for (ExchangeRateDto entry : exchangeRateDto) {
      var targetCurrencyId = currencyService.getCurrencyByCharCode(entry.getCharCode());

      ExchangeRate exchangeRate = new ExchangeRate();
      exchangeRate.setBaseCurrency(baseCurrencyId);
      exchangeRate.setTargetCurrency(targetCurrencyId);
      exchangeRate.setDate(date);
      exchangeRate.setRate(RateCalculator.calculateRateBasedOnNominal(entry));
      exchangeRates.add(exchangeRate);
    }
    return exchangeRates;
  }
}
