package com.example.currencyexchangerate.currency_exchange_rate_spring.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationError {

  private int statusCode;
  private String message;
}
