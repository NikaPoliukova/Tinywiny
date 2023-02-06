package com.example.tinywiny.converter;

import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.model.DeliveryInformation;
import org.mapstruct.Mapper;

@Mapper
public interface DeliveryInformationConverter {
  DeliveryInformation toDeliveryInformation(DeliveryInformationDto deliveryInformationDto);

}
