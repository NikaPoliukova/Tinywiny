package com.example.tinywiny.dto;

import lombok.Value;

@Value
public class JwtResponseDto {
  String accessToken;
  String refreshToken;
}
