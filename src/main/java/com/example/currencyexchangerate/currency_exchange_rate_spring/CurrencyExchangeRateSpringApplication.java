package com.example.currencyexchangerate.currency_exchange_rate_spring;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class CurrencyExchangeRateSpringApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(CurrencyExchangeRateSpringApplication.class, args);

	}

}
