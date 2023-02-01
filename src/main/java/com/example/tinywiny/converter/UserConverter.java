package com.example.tinywiny.converter;

import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {

  UserDto toDto(User user);
  List<UserDto> toDto(List<User> users);

  User toUser(UserDto userDto);
}

