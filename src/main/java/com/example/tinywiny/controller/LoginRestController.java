package com.example.tinywiny.controller;

import com.example.tinywiny.dto.UserDto;
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
@RequestMapping("/api/v1/login")
@AllArgsConstructor
public class LoginRestController {
  private final UserService userService;
//work
  @PostMapping
  protected void userAuthorization(@RequestBody UserDto user) {
    userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());


  }
}
