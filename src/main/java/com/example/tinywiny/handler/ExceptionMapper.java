package com.example.tinywiny.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
@Slf4j
@RestControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleException(final Exception exception) {
    log.error("Request failed", exception);
    return ResponseEntity.status(500).body(Map.of("message", exception.getMessage()));
  }
}


