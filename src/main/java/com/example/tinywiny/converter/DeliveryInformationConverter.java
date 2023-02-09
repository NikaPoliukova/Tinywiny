package com.example.tinywiny.converter;

import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DeliveryInformationConverter {

  @Mapping(target = "user", source = "userDto")
  DeliveryInformation toDeliveryInformation(DeliveryInformationDto deliveryInformationDto, UserDto userDto);

  @Mapping(target = "userId", source = "deliveryInformation.user.userId")
  DeliveryInformationDto toDeliveryInformationDto(DeliveryInformation deliveryInformation);
}
