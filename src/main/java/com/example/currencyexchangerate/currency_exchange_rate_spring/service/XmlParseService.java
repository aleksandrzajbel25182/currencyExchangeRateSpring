package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.adapter.BigDecimalAdapter;
import com.example.currencyexchangerate.currency_exchange_rate_spring.dto.cbrfDto.ValCurs;
import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.xml.XmlParse;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Service;


@Service
public class XmlParseService implements XmlParse {

  @Override
  public ValCurs parseXml(String xmlData) {
    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      unmarshaller.setAdapter(new BigDecimalAdapter());
      return (ValCurs) unmarshaller.unmarshal(new StringReader(xmlData));
    } catch (JAXBException e) {
      throw new RuntimeException(e);
    }
  }
}
