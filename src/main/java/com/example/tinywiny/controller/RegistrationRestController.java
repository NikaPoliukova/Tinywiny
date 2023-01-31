package com.example.tinywiny.controller;


import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/v1/registration")
@AllArgsConstructor
public class RegistrationRestController {
  private final UserService userService;
  private final UserConverter userConverter;


  @PostMapping
  protected UserDto createUser(@RequestBody UserDto user) {
      return userConverter.toDto(userService.save(user));
  }
}
