package com.example.currencyexchangerate.currency_exchange_rate_spring.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomExchangeRate {

  String baseCurrency;
  String targetCurrency;
  BigDecimal rate;
  BigDecimal amount;
  BigDecimal convertToAmount;
}
