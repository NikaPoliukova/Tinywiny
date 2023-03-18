package com.example.tinywiny.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SuccessAuthHandler extends SimpleUrlAuthenticationSuccessHandler {
  private static final String redirectUrl = "http://localhost:3000";

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication authentication) throws IOException {
    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
  }
}
//http://localhost:8080/oauth2/authorization/google