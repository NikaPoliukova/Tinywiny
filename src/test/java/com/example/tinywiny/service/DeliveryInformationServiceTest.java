package com.example.tinywiny.service;

import com.example.tinywiny.converter.DeliveryInformationConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.DeliveryInformationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryInformationServiceTest {

  @Mock
  private UserService userService;
  @Mock
  private UserConverter userConverter;
  @Mock
  private DeliveryInformationRepository repository;
  @Mock
  private DeliveryInformationConverter converter;
  @InjectMocks
  private DeliveryInformationService deliveryInformationService;

  @Test
  void save() {
    when(userConverter.toDto(any(User.class))).thenReturn(new UserDto());
    when(userService.findUserByUserId(any(Long.class))).thenReturn(new User());
    when(converter.toDeliveryInformation(any(DeliveryInformationDto.class), any(UserDto.class))).thenReturn(new DeliveryInformation());
    when(repository.save(any(DeliveryInformation.class))).thenReturn(new DeliveryInformation());
    deliveryInformationService.save(prepareDeliveryInformationDto());

    verify(userConverter).toDto(any(User.class));
    verify(userService).findUserByUserId(any(Long.class));
    verify(converter).toDeliveryInformation(any(DeliveryInformationDto.class), any(UserDto.class));
    verify(repository).save(any(DeliveryInformation.class));
  }

  @Test
  void findDeliveryInformation() {
    when(repository.findDeliveryInformationByDeliveryInformationId(any(Long.class))).thenReturn(prepareDeliveryInformation());
    DeliveryInformation actual = deliveryInformationService.findDeliveryInformation(1L);
    assertEquals(prepareDeliveryInformation(), actual);
  }

  public static DeliveryInformation prepareDeliveryInformation() {
    return new DeliveryInformation(1L, "customerName", "customerLastName", "customerSurname", "addressDelivery", new User(), List.of(new Order()));
  }

  private DeliveryInformationDto prepareDeliveryInformationDto() {
    return new DeliveryInformationDto(1L, "customerName", "customerLastName", "customerSurname", "addressDelivery", 1L);
  }
}