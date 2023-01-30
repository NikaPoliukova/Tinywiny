package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.TokensDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class UserController {

  @Value("${jwt.secretKey}")
  private String secretKey;

  private final UserService userService;
  private final UserConverter userConverter;


  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/correct-user")
  protected User correctUser(@RequestBody User user) {
    return userService.save(user);
  }


  @GetMapping
  protected List<UserDto> findAllUsersByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<User> page = userService.getUserByPage(pageNumber - 1, pageSize);
    List<User> users = page.getContent();
    return userConverter.toDto(users);
  }

  @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/user/{userId}")
  protected User findUser(@RequestBody User user) {
    return userService.findUserByUserId(user.getUserId());


  }
  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION) String authorizationHeader) {
    if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
      try {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        String username = getUsernameFromRefreshToken(refreshToken, secretKey);
        String accessToken = generateRefreshedAccessToken(username, request.getRequestURL().toString(), secretKey);
        TokensDto dto = new TokensDto();
        dto.setAccess_token(accessToken);
        dto.setRefreshToken(refreshToken);

        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), dto);
      } catch (Exception e) {
        log.info("Error logging in {}", e.getMessage());
      }
    } else {
      throw new RuntimeException("Refresh token is missing");
    }
  }
}