package com.example.tinywiny.security.jwt;

import com.example.tinywiny.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

  @Value("${security.secretKey}")
  private String secretKey;
  @Value("${security.refresh_secret}")
  private static String jwtRefreshSecret;

  public String generateAccessToken(User user) {
    LocalDateTime now = LocalDateTime.now();
    Instant accessExpirationInstant = now.plusMinutes(15).atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(accessExpirationInstant);
    return Jwts.builder()
        .setSubject(user.getUserName())
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .claim("role", user.getRole())
        .claim("userId", user.getUserId())
        .compact();
  }

  public String generateRefreshToken(String login) {
    LocalDateTime now = LocalDateTime.now();
    Instant refreshExpirationInstant = now.plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
    Date date = Date.from(refreshExpirationInstant);
    return Jwts.builder()
        .setSubject(login)
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtRefreshSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException expEx) {
      log.info("Token expired");
    } catch (UnsupportedJwtException unsEx) {
      log.info("Unsupported jwt");
    } catch (MalformedJwtException mjEx) {
      log.info("Malformed jwt");
    } catch (SignatureException sEx) {
      log.info("Invalid signature");
    } catch (Exception e) {
      log.info("invalid token");
    }
    return false;
  }

  private boolean validateToken(String token, String secret) {
    Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    return true;
  }

  public boolean validateAccessToken(String accessToken) {
    return validateToken(accessToken);
  }

  public boolean validateRefreshToken(String refreshToken) {
    return validateToken(refreshToken);
  }

  private String getLoginFromToken(String token, String secret) {
    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  public String getLoginFromAccessToken(String token) {
    return getLoginFromToken(token, secretKey);
  }

  public String getLoginFromRefreshToken(String token) {
    return getLoginFromToken(token, jwtRefreshSecret);
  }
}