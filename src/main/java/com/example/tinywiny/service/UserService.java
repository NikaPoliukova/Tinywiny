package com.example.tinywiny.service;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final HashPassService hashPassService;
  private final UserConverter userConverter;
  private final BucketService bucketService;


  public User findUserByUserNameAndPassword(String name, String password) {
    Optional<User> user = userRepository.findUserByUserName(name);
    if (user.isPresent() && hashPassService.verify(password, user.get().getPassword())) {
      return user.get();
    } else {
      throw new RuntimeException("enter incorrect password or login");
    }
  }

  public User findUserByUserName(String name) {
    Optional<User> user = userRepository.findUserByUserName(name);
    if (user.isEmpty()) {
      throw new RuntimeException("user is not exists");
    } else {
      return user.get();
    }
  }

  @Transactional
  @Modifying
  public User updateUser(UserDto userDto, User user) {
    if (user != null) {
      return userRepository.save(prepareUserForUpdate(userDto, user));
    } else {
      throw new RuntimeException("user is not exists");
    }
  }

  @Transactional
  public User save(UserDto userDto) {
    Optional<User> userOptional = userRepository.findUserByUserName(userDto.getUserName());
    User user;
    if (userOptional.isPresent()) {
      throw new RuntimeException("User already exists");
    } else {
      user = userConverter.toUser(userDto);
      String hash = passwordEncoder.encode(userDto.getPassword());
      user.setPassword(hash);
      userRepository.save(user);
      bucketService.createBucket(user.getUserId());
      return user;
    }
  }

  public User findUserByUserId(Long userId) {
    Optional<User> user = userRepository.findUserByUserId(userId);
    if (user.isEmpty()) {
      throw new RuntimeException("no such user exists");
    }
    return user.get();
  }

  public Page<User> getUsersByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return userRepository.findAllBy(page);
  }

  private User prepareUserForUpdate(UserDto userDto, User user) {
    if (!userDto.getPassword().isBlank()) {
      String hashPass = hashPassService.hashPass(userDto.getPassword());
      user.setPassword(hashPass);
    }
    if (!userDto.getEmail().isBlank()) {
      user.setEmail(userDto.getEmail());
    }
    if (!userDto.getPhoneNumber().isBlank()) {
      user.setPhoneNumber(userDto.getPhoneNumber());
    }
    return user;
  }
}



