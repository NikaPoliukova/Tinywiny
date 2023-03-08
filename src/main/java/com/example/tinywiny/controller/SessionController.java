package com.example.tinywiny.controller;

import com.example.tinywiny.model.User;
import com.example.tinywiny.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class SessionController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<User> getCurrentUser() {
    DefaultOAuth2User principal = (DefaultOAuth2User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    Long userId = Long.valueOf(principal.getName());
    return new ResponseEntity<User>(userService.findUserByUserId(userId), HttpStatus.OK);
  }
}
