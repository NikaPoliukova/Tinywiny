package com.example.tinywiny.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleException(final Exception exception) {
    return ResponseEntity.status(500).body(Map.of("message", exception.getMessage()));
  }
}


