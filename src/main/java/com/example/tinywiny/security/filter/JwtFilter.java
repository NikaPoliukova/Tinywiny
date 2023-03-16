package com.example.tinywiny.security.filter;

import com.example.tinywiny.security.UserDetailsServiceImpl;
import com.example.tinywiny.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;
  @Value("${security.secretKey}")
  private String secretKey;
  @Value("${security.refresh_secret}")
  private static String jwtRefreshSecret;
  @Override
  protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
    String token = getAccessTokenFromRequest(servletRequest);
    String refreshToken = getRefreshTokenFromRequest(servletRequest);
    if (token != null && jwtUtils.validateAccessToken(token,secretKey)) {
      setAuthentication(token);
//    } else if (jwtUtils.isTokenExpired(token)&& jwtUtils.validateRefreshToken(refreshToken,secretKey)) {
//      final String refreshedToken = jwtUtils.refreshAccessToken(token);
//      setAuthentication(refreshedToken);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String getAccessTokenFromRequest(HttpServletRequest request) {
      Cookie cookie = WebUtils.getCookie(request, "JWT");
      if (cookie != null) {
        return cookie.getValue();
      }
      return  null;
  }
  private String getRefreshTokenFromRequest(HttpServletRequest request) {
    Cookie cookie = WebUtils.getCookie(request, "JWT-REFRESH");
    if (cookie != null) {
      return cookie.getValue();
    }
    return  null;
  }
  private void setAuthentication(String token) {
    String userName = jwtUtils.getLoginFromAccessToken(token);
    UserDetails customUserDetails = userDetailsService.loadUserByUsername(userName);
    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
        customUserDetails, null, customUserDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(auth);
  }

}