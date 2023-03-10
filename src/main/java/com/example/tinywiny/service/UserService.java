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
    Optional<User> user = userRepository.findUserByUserNameAndPassword(name, passwordEncoder.encode(password));
    if (user.isEmpty()) {
      throw new RuntimeException("enter incorrect password or login");
    }
    return user.get();
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

  public Page<User> getUserByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return userRepository.findAllBy(page);
  }

  private User prepareUserForUpdate(UserDto userDto, User user) {
    if (userDto.getUserName() != null) {
      if (userRepository.findUserByUserName(userDto.getUserName()).isPresent()) {
        throw new RuntimeException("this name already exist");
      } else {
        user.setUserName(userDto.getUserName());
      }
    }
    if (userDto.getPassword() != null) {
      if (!(user.getPassword().equals(user.getPassword()))) {
        String hashPass = hashPassService.hashPass(userDto.getPassword());
        user.setPassword(hashPass);
      }else {
        throw new RuntimeException("this password already exist");
      }
    }
    if (userDto.getEmail() != null) {
      user.setEmail(userDto.getEmail());
    }
    if (userDto.getPhoneNumber() != null) {
      user.setPhoneNumber(userDto.getPhoneNumber());
    }
    return user;
  }
}



