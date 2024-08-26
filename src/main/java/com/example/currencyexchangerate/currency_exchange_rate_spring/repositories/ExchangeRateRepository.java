package com.example.currencyexchangerate.currency_exchange_rate_spring.repositories;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

}
