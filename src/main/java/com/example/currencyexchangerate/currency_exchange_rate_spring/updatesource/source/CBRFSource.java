package com.example.currencyexchangerate.currency_exchange_rate_spring.updatesource.source;

import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ValCurs;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.xml.XmlData;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.xml.XmlParse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CBRFSource implements ExchangeRateSource<ValCurs> {

  @Autowired
  private XmlData xmlData;

  @Autowired
  private XmlParse xmlParse;

  @Override
  public Optional<ValCurs> get() {
    String xmlString = xmlData.getXmlData("https://cbr.ru/scripts/XML_daily.asp?");
    ValCurs valCurs = xmlParse.parseXml(xmlString);
    return Optional.ofNullable(valCurs);
  }

}
