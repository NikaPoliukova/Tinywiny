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

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {

  @Value("${security.secretKey}")
  private String jwtSecret;
  @Value("${security.session-duration-minutes}")
  private Long sessionDurationMinutes;

  public String generateToken(User user) {
    final Instant instant = LocalDateTime.now()
        .plus(Duration.ofMinutes(sessionDurationMinutes))
        .toInstant(ZoneOffset.UTC);
    final Date date = Date.from(instant);
    return Jwts.builder()
        .setSubject(user.getUserId().toString())
        .claim("login", user.getUserName())
        .claim("roleName", user.getRole())
        .setExpiration(date)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
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

  public Claims getTokenClaims(final String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
  }
}
