package com.example.currencyexchangerate.currency_exchange_rate_spring.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<ApplicationError> catchResourceNotFoundException(
      ResourceNotFoundException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(new ApplicationError(HttpStatus.NOT_FOUND.value(), e.getMessage()),
        HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<ApplicationError> catchResourceNotCorrectException(
      ResourceNotCorrectException e) {
    log.error(e.getMessage(), e);
    return new ResponseEntity<>(new ApplicationError(HttpStatus.BAD_REQUEST.value(), e.getMessage()),
        HttpStatus.BAD_REQUEST);
  }
}
