package com.example.currencyexchangerate.currency_exchange_rate_spring.converter;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConverter {

  private final ModelMapper modelMapper = new ModelMapper();

  public List<Currency> convertToEntity(HashMap<String, String> charCodeAndNameMap) {
    List<Currency> currencies = new ArrayList<>();
    for (Map.Entry<String, String> entry : charCodeAndNameMap.entrySet()) {
      Currency currency = modelMapper.map(new Currency(entry.getKey(), entry.getValue()),
          Currency.class);
      currencies.add(currency);
    }
    return currencies;
  }
}
