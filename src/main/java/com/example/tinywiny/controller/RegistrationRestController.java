package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.model.User;
import com.example.tinywiny.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
  private final UserService userService;
  private final UserConverter userConverter;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  protected User saveUser(@RequestBody User user) {
    return userService.save(user);
  }
}
