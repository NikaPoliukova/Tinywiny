package com.example.tinywiny.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class HashPassService {

  private final BCrypt.Hasher hasher;

  @Value("${security.bcrypt-secret}")
  private String secretKey;

  public HashPassService() {
    hasher = BCrypt.with(new SecureRandom(secretKey.getBytes()));
  }

  public boolean verify(String password, String hashPass) {
    final BCrypt.Result verify = BCrypt.verifyer().verify(password.getBytes(), hashPass.getBytes());
    return verify.verified;
  }

  public String hashPass(String password) {
    return hasher.hashToString(12, password.toCharArray());
  }
}
