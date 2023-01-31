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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    Optional<User> user = userRepository.findUserByUserNameAndPassword(name, passwordEncoder.encode(password));
    if (user.isEmpty()) {
      throw new RuntimeException("enter incorrect password or login");
    }
    return user.get();
  }

  public User updateUser(UserDto userDto) {
    String hashPass = hashPassService.hashPass(userDto.getPassword());
    userDto.setPassword(hashPass);
    return userRepository.save(userConverter.toUser(userDto));
  }

  public User save(UserDto userDto) {
    Optional<User> userOptional = userRepository.findUserByUserName(userDto.getUserName());
    User user;
    if (userOptional.isPresent()) {
      throw new RuntimeException("User already exists");
    } else {
      user = userConverter.toUser(userDto);
      user.setPassword(passwordEncoder.encode(userDto.getPassword()));
      userRepository.save(user);
    }
    bucketService.createBucket(user.getUserId());
    return user;
  }

  public User findUserByUserId(Long userId) {
    Optional<User> user = userRepository.findUserByUserId(userId);
    if (user.isEmpty()) {
      throw new RuntimeException("no such user exists");
    }
    return user.get();
  }

  public Page<User> getUserByPage(Pageable page) {
    return userRepository.findAllBy(page);
  }

  /*private void prepareUserForUpdate(UserDto userDto, User user) {
    if (userDto.getUserName() != null) {
      user.setUserName(userDto.getUserName());
    }
    if (userDto.getPassword() != null) {
      String hashPass = hashPassService.hashPass(userDto.getPassword());
      user.setPassword(hashPass);
    }
    if (userDto.getEmail() != null) {
      user.setEmail(userDto.getEmail());
    }
    if (userDto.getPhoneNumber() != null) {
      user.setPhoneNumber(userDto.getPhoneNumber());
    }
  }*/
}



