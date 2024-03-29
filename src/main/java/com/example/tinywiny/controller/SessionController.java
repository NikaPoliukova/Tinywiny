package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.service.UserService;
import lombok.RequiredArgsConstructor;
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
  private final UserConverter converter;
  @GetMapping
  public UserDto getCurrentUser() {
    final DefaultOAuth2User principal = (DefaultOAuth2User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    return converter.toDto(userService.findUserByUserName(principal.getName()));
  }
}
