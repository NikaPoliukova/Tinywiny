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

  public boolean validateAccessToken(String accessToken) {
    return validateToken(accessToken);
  }

  public boolean validateRefreshToken(String refreshToken) {
    return validateToken(refreshToken);
  }

  public String getLoginFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    return claims.getSubject();
  }

  public Claims getTokenClaims(final String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
  }
/*
  public static String generateAccessToken(User user, String requestUrl, Algorithm algorithm) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
        .withIssuer(requestUrl)
        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .sign(algorithm);
  }

  public static String generateRefreshToken(User user, String requestUrl, Algorithm algorithm) {
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
        .withIssuer(requestUrl)
        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .sign(algorithm);
  }

  public static String generateRefreshedAccessToken(String username, String requestUrl, String secretKey) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
    List<String> roles = new ArrayList<>();
    roles.add("USER");
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
        .withIssuer(requestUrl)
        .withClaim("roles", roles)
        .sign(algorithm);
  }

  public static String generateRefreshedRefreshToken(String username, String requestUrl, String secretKey) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
    List<String> roles = new ArrayList<>();
    roles.add("USER");
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + 120 * 60 * 1000))
        .withIssuer(requestUrl)
        .withClaim("roles", roles)
        .sign(algorithm);
  }

  public static String getUsernameFromRefreshToken(String refreshToken, String secretKey) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    DecodedJWT decodedJWT = verifier.verify(refreshToken);
    return decodedJWT.getSubject();
  }

  public static DecodedJWT getDecodedJWTToken(String token, String secretKey) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey.getBytes());
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  public static String generateAccessTokenV2(com.example.tinywiny.model.User user) {
    return JWT.create()
        .withSubject(user.getUserId().toString())
        .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //TODO move it to property
        .withClaim("login", user.getUserName())
        .sign(Algorithm.HMAC256("nika".getBytes()));
  }*/
}
