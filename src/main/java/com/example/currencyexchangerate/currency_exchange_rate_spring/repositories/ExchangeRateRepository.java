package com.example.currencyexchangerate.currency_exchange_rate_spring.repositories;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;
import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  @Query(value = "SELECT * FROM exchangerates " +
      "JOIN currencies baseCurrency ON exchangerates.basecurrencyid = baseCurrency.id " +
      "JOIN currencies targetCurrency ON exchangerates.targetcurrencyid = targetCurrency.id " +
      "WHERE baseCurrency.charcode = :#{#baseCurrency} AND targetCurrency.charcode = :#{#targetCurrency}", nativeQuery = true)
  Optional<ExchangeRate> findExchangeRateByBaseCurrencyAndTargetCurrency(
      @Param("baseCurrency") String baseCurrency, @Param("targetCurrency") String targetCurrency);
}
