package com.example.currencyexchangerate.currency_exchange_rate_spring.repositories;

import com.example.currencyexchangerate.currency_exchange_rate_spring.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}