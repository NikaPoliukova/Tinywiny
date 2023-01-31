package com.example.tinywiny.converter;

import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserConverter {

  List<UserDto> toDto(List<User> users);

  UserDto toDto(User user);

  User toUser(UserDto userDto);
}

