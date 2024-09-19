package com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto;

import com.example.currencyexchangerate.currency_exchange_rate_spring.adapter.BigDecimalAdapter;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Valute")
public class ExchangeRateDto {
  private String id;
  private int numCode;
  private String charCode;
  private int nominal;
  private String name;
  private BigDecimal value;
  private BigDecimal vunitRate;

  @XmlElement(name = "ID")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @XmlElement(name = "NumCode")
  public int getNumCode() {
    return numCode;
  }

  public void setNumCode(int numCode) {
    this.numCode = numCode;
  }

  @XmlElement(name = "CharCode")
  public String getCharCode() {
    return charCode;
  }

  public void setCharCode(String charCode) {
    this.charCode = charCode;
  }

  @XmlElement(name = "Nominal")
  public int getNominal() {
    return nominal;
  }

  public void setNominal(int nominal) {
    this.nominal = nominal;
  }

  @XmlElement(name = "Name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @XmlElement(name = "Value")
  @XmlJavaTypeAdapter(BigDecimalAdapter.class)
  public BigDecimal getValue() {
    return value;
  }

  public void setValue(BigDecimal value) {
    this.value = value;
  }

  @XmlElement(name = "VunitRate")
  @XmlJavaTypeAdapter(BigDecimalAdapter.class)
  public BigDecimal getVunitRate() {
    return vunitRate;
  }

  public void setVunitRate(BigDecimal vunitRate) {
    this.vunitRate = vunitRate;
  }

}
