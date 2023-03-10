package com.example.tinywiny.controller;

import com.example.tinywiny.dto.CredentialsDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.security.jwt.JwtUtils;
import com.example.tinywiny.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {

  private final JwtUtils jwtUtils;
  private final UserService userService;
  private static final String TOKEN_NAME = "JWT";
  private static final long EXPIRATION = Duration.ofHours(3).toSeconds();

  @PostMapping("/login")
  public void login(@RequestBody CredentialsDto request, HttpServletResponse response) {
    User user = userService.findUserByUserNameAndPassword(request.getUserName(), request.getPassword());
    if (user != null) {
      String token = jwtUtils.generateAccessToken(user);
      final Cookie cookie = new Cookie(TOKEN_NAME, token);
      cookie.setPath("/");
      cookie.setHttpOnly(true);
      cookie.setMaxAge((int) EXPIRATION);
      response.addCookie(cookie);
      response.setStatus(HttpStatus.OK.value());
    } else {
      response.setStatus(HttpStatus.CONFLICT.value());
    }
  }
}


