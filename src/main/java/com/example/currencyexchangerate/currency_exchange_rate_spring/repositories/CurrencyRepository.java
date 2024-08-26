package com.example.currencyexchangerate.currency_exchange_rate_spring.repositories;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Currency;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {

  Optional<Currency> findByCharCode(String charCode);


  @Modifying
  @Query(value = "INSERT INTO currencies (charcode, fullname) " +
      "VALUES (:#{#currencies.![charcode]}, :#{#currencies.![fullname]}) " +
      "ON DUPLICATE KEY UPDATE charcode = VALUES(charcode), fullname = VALUES(fullname)", nativeQuery = true)
  void upsertCurrencyAll(@Param("currencies") List<Currency> currencies);
}
