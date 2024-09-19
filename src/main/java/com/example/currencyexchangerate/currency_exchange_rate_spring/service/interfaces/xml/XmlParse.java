package com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.xml;

import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ValCurs;

public interface XmlParse {

  ValCurs parseXml(String xmlData);
}
