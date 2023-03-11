package com.example.tinywiny.security.filter;

import com.example.tinywiny.security.UserDetailsServiceImpl;
import com.example.tinywiny.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
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

  public static final String AUTHORIZATION = "Authorization";

  private final JwtUtils jwtUtils;
  private final UserDetailsServiceImpl userDetailsService;


  @Override
  protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
    String token = getTokenFromRequest(servletRequest);
    if (token != null && jwtUtils.validateAccessToken(token)) {
      String userName = jwtUtils.getLoginFromAccessToken(token);
      UserDetails customUserDetails = userDetailsService.loadUserByUsername(userName);
      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
          customUserDetails, null, customUserDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(auth);
    }
    filterChain.doFilter(servletRequest, servletResponse);
  }

  private String getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader(AUTHORIZATION);
    if (bearer != null && bearer.startsWith("Bearer ")) {
      return bearer.substring(7);
    } else {
      Cookie cookie = WebUtils.getCookie(request, "JWT");
      if (cookie != null) {
        return cookie.getValue();
      }
    }
    return null;
  }
}