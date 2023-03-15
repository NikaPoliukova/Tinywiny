package com.example.tinywiny.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Builder
@Value
public class CredentialsDto {

  String userName;
  String password;

}