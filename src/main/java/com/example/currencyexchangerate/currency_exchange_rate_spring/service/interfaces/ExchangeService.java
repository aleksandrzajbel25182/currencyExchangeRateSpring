package com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces;

import java.math.BigDecimal;

public interface ExchangeService {
  BigDecimal getRate(String from, String to);
}
