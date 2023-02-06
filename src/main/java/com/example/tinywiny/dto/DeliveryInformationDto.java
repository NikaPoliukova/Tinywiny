package com.example.tinywiny.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryInformationDto {
  Long id;
  String customerName;
  String customerLastName;
  String customerSurname;
  String addressDelivery;
  Long userId;

}
