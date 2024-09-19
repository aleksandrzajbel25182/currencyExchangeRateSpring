package com.example.currencyexchangerate.currency_exchange_rate_spring.util;

import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ExchangeRateDto;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class RateCalculator {

  private static final MathContext mc = new MathContext(5, RoundingMode.HALF_UP);

  public static BigDecimal calculateRate(BigDecimal value) {
    return BigDecimal.ONE.divide(value, mc);
  }

  public static BigDecimal calculateRateBasedOnNominal(ExchangeRateDto entry) {
    if (entry.getNominal() > 1) {
      return calculateRate(entry.getVunitRate());
    } else {
      return calculateRate(entry.getValue());
    }
  }
}
