package com.example.tinywiny.controller;

import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.User;
import com.example.tinywiny.security.UserDetailsServiceImpl;
import com.example.tinywiny.security.jwt.JwtUtils;
import com.example.tinywiny.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRestController.class)

class UserRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;
  @MockBean
  private  UserConverter userConverter;
  @MockBean
  private JwtUtils jwtUtils;
  @MockBean
  private UserDetailsServiceImpl userDetailsService;
  final static User user= new User(2L,"Ola","555666", new Date(),"ola@mail.ru","55566655", "User");


  @BeforeAll
  static void beforeAll() {

  }

  @Test
  public void findUser() throws Exception {
    final Long userId = 5L;
    mockMvc.perform(MockMvcRequestBuilders
        .put("/api/v1/users/{userId}", userId)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
       then(userService).should().findUserByUserId(userId);
      then(userConverter).should().toDto(user);
    }

    @Test
  public void updateUser() throws Exception {
   final UserDto userDto = new UserDto(2L,"liza",null,null,null);
      mockMvc.perform(MockMvcRequestBuilders
          .put("/api/v1/users", userDto)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
     then(userService).should().findUserByUserId(2L);
     then(userService).should().updateUser(userDto,user);
    }

    @Test
  public void findUserByUserNameAndPassword() throws Exception {
      String userName = "ola";
      String password = "ola123";
      mockMvc.perform(MockMvcRequestBuilders
          .put("/api/v1/users/user", userName, password)
          .accept(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());

      then(userService).should().findUserByUserNameAndPassword(userName, password);
      then(userConverter).should().toDto(user);
    }

  @Test
  public void findAllUsersByPage() throws Exception {
   final  Integer pageNumber =1;
    final Integer pageSize =20;
    final List<User> users = new ArrayList<User>();
    users.add(user);
    users.add(user);
    users.add(new User());
      mockMvc.perform(MockMvcRequestBuilders
        .put("/api/v1/users", pageNumber, pageSize)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    then(userService).should().getUsersByPage(pageNumber, pageSize);
    then(userConverter).should().toDto(users);
  }

}