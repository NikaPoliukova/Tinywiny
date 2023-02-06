package com.example.tinywiny.service;

import com.example.tinywiny.converter.DeliveryInformationConverter;
import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.repository.DeliveryInformationRepository;
import com.example.tinywiny.repository.DeliveryTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeliveryInformationService {

  private final DeliveryInformationRepository repository;
  private final DeliveryInformationConverter converter;
  private final DeliveryTypeRepository deliveryTypeRepository;
  public void save(DeliveryInformationDto DeliveryInformationDto) {
    repository.save(converter.toDeliveryInformation(DeliveryInformationDto));
  }
  public DeliveryType findDeliveryType(int deliveryTypeId) {
    return deliveryTypeRepository.findByIdType(deliveryTypeId);
  }
}
