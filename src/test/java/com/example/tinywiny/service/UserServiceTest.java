package com.example.tinywiny.service;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
  @Mock
  private UserRepository userRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private HashPassService hashPassService;
  @Mock
  private UserConverter userConverter;
  @Mock
  private BucketService bucketService;

  @InjectMocks
  private UserService userService;


  @Test
  void findUserByUserNameAndPassword() {
    when(userRepository.findUserByUserName(any(String.class))).thenReturn(Optional.of(prepareUser()));
    User actual = userService.findUserByUserName("pika");
    assertEquals(prepareUser(), actual);
  }

  @Test
  void save() {
    final UserDto userDto = new UserDto(8L, "Otrr", "123",
        "lra@mail.ru", "123456788");

    when(userRepository.findUserByUserName(any(String.class))).thenReturn(Optional.ofNullable(null));
    when(userConverter.toUser(any(UserDto.class))).thenReturn(prepareUser());
    when(passwordEncoder.encode(any(String.class))).thenReturn("123");
    when(userRepository.save(any(User.class))).thenReturn(prepareUser());
    doNothing().when(bucketService).createBucket(any(Long.class));
    userService.save(userDto);
  }

  @Test
  void findUserByUserId() {
    when(userRepository.findUserByUserId(any(Long.class))).thenReturn(Optional.of(prepareUser()));
    User actual = userService.findUserByUserId(2L);
    assertEquals(prepareUser(), actual);
  }


  @Test
  void findUserByUserName() {
    when(userRepository.findUserByUserName(any(String.class))).thenReturn(Optional.of(prepareUser()));
    User actual = userService.findUserByUserName("pika");
    assertEquals(prepareUser(), actual);
  }

  @Test
  void getUsersByPage() {
    Page<User> users = mock(Page.class);
    Pageable page = PageRequest.of(1, 20);
    Mockito.when(userRepository.findAllBy(page)).thenReturn(users);
    userService.getUsersByPage(1, 20);
  }

  @Transactional
  @Modifying
  @Test
  void updateUser() {
    UserDto userDto = new UserDto(2L, "pika", "123", "vvxvdvdf", "1234856");

    when(hashPassService.hashPass(any(String.class))).thenReturn("$2a$10$hhzNac/7tjQoiWs4v88EpecdjnIucWvxuha3nvQRdcvIZ0HtIRr.q");
    when(userRepository.save(any(User.class))).thenReturn(prepareUser());
    User actual = userService.updateUser(userDto, prepareUser());
    assertEquals(prepareUser(), actual);
  }

  public User prepareUser() {
    return new User(2L, "pika", "$2a$10$hhzNac/7tjQoiWs4v88EpecdjnIucWvxuha3nvQRdcvIZ0HtIRr.q",
        null, "nika@mail.ru", "123456789", "USER");
  }
}
