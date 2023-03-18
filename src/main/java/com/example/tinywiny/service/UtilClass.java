package com.example.tinywiny.service;

import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UtilClass {
  private final UserRepository userRepository;

  public Long getIdCurrentUser() {
    DefaultOAuth2User principal = (DefaultOAuth2User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
     Optional<com.example.tinywiny.model.User> user =userRepository.findUserByUserName(principal.getName());
    return user.get().getUserId();
  }

}
