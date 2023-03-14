package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.security.PrincipalUser;
import com.example.tinywiny.service.UserService;
import com.example.tinywiny.service.UtilClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;
  private final UserConverter userConverter;
  private final UtilClass utilClass;

  @PostMapping
  protected void updateUser(@RequestBody UserDto user) {
    User userDB = userService.findUserByUserId( user.getUserId());
    userService.updateUser(user, userDB);
  }

  @GetMapping
  protected List<UserDto> findAllUsersByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<User> page = userService.getUserByPage(pageNumber - 1, pageSize);
    List<User> users = page.getContent();
    return userConverter.toDto(users);
  }

  @GetMapping("/{userId}")
  protected UserDto findUser(@PathVariable Long userId) {
    User user = userService.findUserByUserId(userId);
    return userConverter.toDto(user);
  }

  @GetMapping("/user")
  protected UserDto findUserByUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {
    User user = userService.findUserByUserNameAndPassword(userName, password);
    return userConverter.toDto(user);
  }

}
