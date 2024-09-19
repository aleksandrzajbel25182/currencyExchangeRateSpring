package com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ValCurs")
public class ValCurs {

  private String date;
  private String name;
  private List<ExchangeRateDto> exchangeRateDtos;

  @XmlAttribute(name = "Date")
  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @XmlAttribute(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement(name = "Valute")
  public List<ExchangeRateDto> getValutes() {
    return exchangeRateDtos;
  }

  public void setValutes(List<ExchangeRateDto> exchangeRateDtos) {
    this.exchangeRateDtos = exchangeRateDtos;
  }
}
