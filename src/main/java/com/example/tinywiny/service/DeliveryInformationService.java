package com.example.tinywiny.service;

import com.example.tinywiny.converter.DeliveryInformationConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.repository.DeliveryInformationRepository;
import com.example.tinywiny.repository.DeliveryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeliveryInformationService {

  private final UserService userService;
  private final UserConverter userConverter;
  private final DeliveryInformationRepository repository;
  private final DeliveryInformationConverter converter;
  private final DeliveryTypeRepository deliveryTypeRepository;

  public void save(DeliveryInformationDto DeliveryInformationDto) {
    UserDto userDto = userConverter.toDto(userService.findUserByUserId(DeliveryInformationDto.getUserId()));
    repository.save(converter.toDeliveryInformation(DeliveryInformationDto, userDto));
  }

  public DeliveryInformation findDeliveryInformation(Long id) {
    return repository.findDeliveryInformationByDeliveryInformationId(id);
  }
}
