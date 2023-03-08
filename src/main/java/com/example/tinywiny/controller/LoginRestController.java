package com.example.tinywiny.controller;

/*import java.io.IOException;


import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class LoginRestController {

  private final UserService userService;
  private final AuthContext authContext;

  private static final String TOKEN_NAME = "JWT";

  @PostMapping
  protected void userAuthorization(@RequestBody UserDto user) {
    User my = userService.findUserByUserNameAndPassword(user.getUserName(), user.getPassword());
    authContext.setUserId(my.getUserId());
  }

  /
  @PostMapping("/login")
  public void login(@RequestBody UserDto userDto, HttpServletResponse response) {
    User authorizedUser = userService.findUserByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
    String accessToken = generateAccessTokenV2(authorizedUser);

    final Cookie cookie = new Cookie(TOKEN_NAME, accessToken);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
    response.setStatus(HttpStatus.OK.value());
  }

  @GetMapping("/token/refresh")
  public void refreshToken(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION) String authorizationHeader) {
    AuthResultDto dto = jwtTokenService.generateToken(request, authorizationHeader);
    response.setContentType(APPLICATION_JSON_VALUE);
    try {
      new ObjectMapper().writeValue(response.getOutputStream(), dto);
    } catch (IOException e) {
      log.info("Error logging in {}", e.getMessage());
    }
  }
}*/
