package com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces;

import com.example.currencyexchangerate.currency_exchange_rate_spring.error.ResourceNotFoundException;
import java.math.BigDecimal;

public interface ExchangeService {
  BigDecimal getRate(String from, String to) throws ResourceNotFoundException;
}
