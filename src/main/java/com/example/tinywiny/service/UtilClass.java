package com.example.tinywiny.service;

import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UtilClass {
  private final UserRepository userRepository;

  public Long getIdCurrentUser() {
    User principal = (User) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
     Optional<com.example.tinywiny.model.User> user =userRepository.findUserByUserName(principal.getUsername());
    return user.get().getUserId();
  }
}
