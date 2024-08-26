package com.example.currencyexchangerate.currency_exchange_rate_spring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "url")
  private String url;

  @ManyToOne
  @JoinColumn(name = "basecurrencyid")
  private Currency baseCurrency;

  @ManyToOne
  @JoinColumn(name = "targetcurrencyid")
  private Currency targetCurrency;

  @Column(name = "rate")
  private BigDecimal rate;

  @Column(name = "date")
  private Date date;

  @Column(name = "status")
  private String status;

  // Добавьте геттеры и сеттеры

  // Добавьте конструкторы

  // Добавьте другие методы по необходимости
}