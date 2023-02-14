package com.example.tinywiny.controller;

import com.example.tinywiny.dto.TokensDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.service.JwtTokenService;
import com.example.tinywiny.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginRestController {

  private final UserService userService;
  private final JwtTokenService jwtTokenService;

  @PostMapping
  protected void userAuthorization(@RequestBody UserDto user) {
    userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
  }
//+method GET TOKEN?
  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION) String authorizationHeader) {
    TokensDto dto = jwtTokenService.generateToken(request, authorizationHeader);
    response.setContentType(APPLICATION_JSON_VALUE);
    try {
      new ObjectMapper().writeValue(response.getOutputStream(), dto);
    } catch (IOException e) {
      log.info("Error logging in {}", e.getMessage());
    }
  }
}
