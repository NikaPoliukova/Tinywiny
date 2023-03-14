package com.example.tinywiny.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class UserRefreshToken {
  Long userId;
  String token;
}