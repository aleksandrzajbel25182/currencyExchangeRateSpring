package com.example.currencyexchangerate.currency_exchange_rate_spring.updatesource.source;

import java.util.Optional;

public interface ExchangeRateSource<T> {

  Optional<T> get();

}
