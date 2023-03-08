package com.example.tinywiny.controller;

import com.example.tinywiny.dto.CredentialsDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.security.jwt.JwtProvider;
import com.example.tinywiny.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthControllerV2 {

  private final JwtProvider jwtProvider;
  private final UserService userService;


  @PostMapping("/login")
  public String login(@RequestBody CredentialsDto request, HttpServletResponse response) {
    User user = userService.findUserByUserNameAndPassword(request.getUserName(), request.getPassword());
    if (user != null) {
      String token = jwtProvider.generateToken(user);
      response.setStatus(HttpStatus.OK.value());
      return token;
    } else {
      response.setStatus(HttpStatus.CONFLICT.value());
      return null;
    }
  }
}

