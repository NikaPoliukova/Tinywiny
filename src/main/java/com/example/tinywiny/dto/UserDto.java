package com.example.tinywiny.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

  private String userName;

  private String password;

  private String email;

  private String phoneNumber;
}
