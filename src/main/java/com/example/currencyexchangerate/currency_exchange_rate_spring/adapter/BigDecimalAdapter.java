package com.example.currencyexchangerate.currency_exchange_rate_spring.adapter;

import java.math.BigDecimal;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BigDecimalAdapter extends XmlAdapter<String, BigDecimal> {

  @Override
  public BigDecimal unmarshal(String value) {
    return new BigDecimal(value.replace(',', '.'));
  }

  @Override
  public String marshal(BigDecimal value) {
    return value.toString();
  }
}