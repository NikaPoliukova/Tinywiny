package com.example.tinywiny.converter;

import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserConverter {

  UserDto toDto(User user);

  User toUser(UserDto userDto);
}

