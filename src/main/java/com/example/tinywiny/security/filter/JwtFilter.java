//package com.example.tinywiny.security.filter;
//
//
//import com.example.tinywiny.security.UserDetailsServiceImpl;
//import com.example.tinywiny.security.jwt.JwtUtils;
//import com.nimbusds.oauth2.sdk.util.CollectionUtils;
//import io.jsonwebtoken.Claims;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
//import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.GenericFilterBean;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class JwtFilter extends OncePerRequestFilter {//extends GenericFilterBean {
//
//  private static final String TOKEN_NAME = "JWT";
//  private final JwtUtils jwtProvider;
//
//  @Override
//  public void doFilterInternal(
//      final HttpServletRequest request,
//      final HttpServletResponse response,
//      final FilterChain filterChain
//  ) throws IOException, ServletException {
//
//    final String token = getTokenFromRequest(request);
//
//    if (jwtProvider.validateToken(token)) {
//      final Claims claims = jwtProvider.getTokenClaims(token);
//
//      final var oAuth2User = new DefaultOAuth2User(Collections.emptyList(), claims, "sub");
//      final OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(
//          oAuth2User, Collections.emptyList(), "google");
//      SecurityContextHolder.getContext().setAuthentication(auth);
//    }
//    filterChain.doFilter(request, response);
//  }
//
//  private String getTokenFromRequest(final HttpServletRequest request) {
//    return Optional.of(request)
//        .map(HttpServletRequest::getCookies)
//        .map(Arrays::asList)
//        .filter(CollectionUtils::isNotEmpty)
//        .stream()
//        .flatMap(List::stream)
//        .filter(cookie -> TOKEN_NAME.equals(cookie.getName()))
//        .map(Cookie::getValue)
//        .findFirst()
//        .orElse("");
//  }
//
//
//
//
//
//
//
//
///*
//
//  public static final String AUTHORIZATION = "Authorization";
//  private final UserDetailsServiceImpl customUserDetailsService;
//  private final JwtUtils jwtProvider;
//  private static final String TOKEN_NAME = "JWT";
//
//  private String getTokenFromRequest(final HttpServletRequest request) {
//    return Optional.of(request)
//        .map(HttpServletRequest::getCookies)
//        .map(Arrays::asList)
//        .filter(CollectionUtils::isNotEmpty)
//        .stream()
//        .flatMap(List::stream)
//        .filter(cookie -> TOKEN_NAME.equals(cookie.getName()))
//        .map(Cookie::getValue)
//        .findFirst()
//        .orElse("");
//  }
//
//  @Override
//  public void doFilter(
//      ServletRequest servletRequest,
//      ServletResponse servletResponse,
//      FilterChain filterChain
//  ) throws IOException, ServletException {
//    logger.info("do filter...");
//    String token = getTokenFromRequest((HttpServletRequest) servletRequest);
//    if (token != null && jwtProvider.validateAccessToken(token)) {
//      String userLogin = jwtProvider.getLoginFromToken(token);
//      UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
//      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//          customUserDetails, null, customUserDetails.getAuthorities());
//      SecurityContextHolder.getContext().setAuthentication(auth);//отсюда получаем инфу,кто залогинен на всем приложении
//    }
//    filterChain.doFilter(servletRequest, servletResponse);
//  }
//
// */
//
//}
