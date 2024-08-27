package com.example.currencyexchangerate.currency_exchange_rate_spring.repositories;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.ExchangeRate;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

  @Query(value = "select ex from ExchangeRate ex "
      + "where ex.baseCurrency.id = (select c.id from Currency c where c.charCode = :baseCurrency) "
      + "and ex.targetCurrency.id = (select c.id from Currency c where c.charCode = :targetCurrency)")
  Optional<ExchangeRate> findExchangeRateByBaseCurrencyAndTargetCurrency(
      @Param("baseCurrency") String baseCurrency, @Param("targetCurrency") String targetCurrency);

  @Modifying
  @Query(value = "insert into exchangerates (basecurrencyid,targetcurrencyid,rate,date) "
      + "values (:#{#exchangerate.baseCurrency.id}, :#{#exchangerate.targetCurrency.id} , "
      + ":#{#exchangerate.rate} , :#{#exchangerate.date} ) "
      + "on conflict (basecurrencyid,targetcurrencyid,date) do update "
      + "set rate = excluded.rate, date = excluded.date", nativeQuery = true)
  void upsertExchangeRate(@Param("exchangerate") ExchangeRate exchangerate);
}
