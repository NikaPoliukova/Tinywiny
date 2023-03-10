package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.TokensDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.example.tinywiny.security.jwt.JwtUtils.generateRefreshedAccessToken;
import static com.example.tinywiny.security.jwt.JwtUtils.getUsernameFromRefreshToken;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;
  private final UserConverter userConverter;

  @PutMapping
  protected void updateUser(@RequestBody UserDto userDto) {
    User user = userService.findUserByUserId(userDto.getUserId());
    userService.updateUser(userDto, user);
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

}
