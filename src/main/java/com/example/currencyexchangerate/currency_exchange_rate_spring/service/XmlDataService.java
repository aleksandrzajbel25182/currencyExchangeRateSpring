package com.example.currencyexchangerate.currency_exchange_rate_spring.service;

import com.example.currencyexchangerate.currency_exchange_rate_spring.service.interfaces.xml.XmlData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class XmlDataService implements XmlData {
  private final WebClient webClient;

  public XmlDataService(WebClient webClient) {
    this.webClient = webClient;
  }
  @Override
  public String getXmlData(String url) {
    return webClient
        .get()
        .uri(url)
        .retrieve()
        .bodyToMono(String.class)
        .block();
  }
}
