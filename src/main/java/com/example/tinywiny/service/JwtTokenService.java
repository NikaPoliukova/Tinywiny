package com.example.tinywiny.service;

import com.example.tinywiny.dto.TokensDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.example.tinywiny.security.jwt.JwtUtils.generateRefreshedAccessToken;
import static com.example.tinywiny.security.jwt.JwtUtils.generateRefreshedRefreshToken;
import static com.example.tinywiny.security.jwt.JwtUtils.getUsernameFromRefreshToken;

@Log4j2
@Service
@RequiredArgsConstructor
public class JwtTokenService {

  @Value("${jwt.secretKey}")
  private String secretKey;

  public TokensDto generateToken(HttpServletRequest request, String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      String refreshToken = authorizationHeader.substring("Bearer ".length());
      String username = getUsernameFromRefreshToken(refreshToken, secretKey);
      String accessToken = generateRefreshedAccessToken(username, request.getRequestURL().toString(), secretKey);
      String refreshedRefreshToken = generateRefreshedRefreshToken(username, request.getRequestURL().toString(), secretKey);
      TokensDto dto = new TokensDto();
      dto.setAccess_token(accessToken);
      dto.setRefreshToken(refreshedRefreshToken);
      return dto;
    } else {
      throw new IllegalArgumentException("Refresh token is missing");
    }
  }
}
