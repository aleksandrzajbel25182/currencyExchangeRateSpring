package com.example.currencyexchangerate.currency_exchange_rate_spring.updatesource.source;

import com.example.currencyexchangerate.currency_exchange_rate_spring.converter.CurrencyConverter;
import com.example.currencyexchangerate.currency_exchange_rate_spring.converter.ExchangeRateConverter;
import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ExchangeRateDto;
import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ValCurs;
import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotCorrectException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.CurrencyService;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.ExchangeRateService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class UpdateSource {

  @Scheduled(cron = "0 0 22 * * ?")
  public void scheduledUpdateSource() {
    updateSource();
  }

  @Autowired
  private CurrencyConverter currencyConverter;

  @Autowired
  private ExchangeRateConverter exchangeRateConverter;

  @Autowired
  private CurrencyService currencyService;

  @Autowired
  private ExchangeRateService exchangeRateService;

  @Autowired
  private ExchangeRateSource<ValCurs> cbrfSource;

  public void updateSource() {

    Optional<ValCurs> valCurs = cbrfSource.get();

    if (!valCurs.isEmpty()) {
      var charCodeAndNameMap = new HashMap<String, String>();
      for (ExchangeRateDto exchangeRateDto : valCurs.get().getValutes()) {
        charCodeAndNameMap.put(exchangeRateDto.getCharCode(), exchangeRateDto.getName());
      }
      List<Currency> currencies = currencyConverter.convertToEntity(charCodeAndNameMap);
      currencyService.upsertCurrencyAll(currencies);

      SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
      try {
        Date date = dateFormat.parse(valCurs.get().getDate());
        List<ExchangeRate> exchangeRates = exchangeRateConverter.converterToEntities(
            valCurs.get().getValutes(),
            date);
        exchangeRateService.upsertExchangeRateAll(exchangeRates);
      } catch (
          ResourceNotCorrectException | ResourceNotFoundException e) {
        throw new RuntimeException(e);
      } catch (ParseException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
