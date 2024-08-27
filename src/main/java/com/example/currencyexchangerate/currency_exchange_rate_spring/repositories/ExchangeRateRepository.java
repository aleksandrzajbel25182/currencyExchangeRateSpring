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

  @Query(value = "SELECT ex FROM ExchangeRate ex "
      + "WHERE ex.baseCurrency.id = (SELECT c.id FROM Currency c WHERE c.charCode = :baseCurrency) "
      + "AND ex.targetCurrency.id = (SELECT c.id FROM Currency c WHERE c.charCode = :targetCurrency)")
  Optional<ExchangeRate> findExchangeRateByBaseCurrencyAndTargetCurrency(
      @Param("baseCurrency") String baseCurrency, @Param("targetCurrency") String targetCurrency);

}
