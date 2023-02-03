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
public class OrderDto {

  String commentOrder;
  Long userId;
  Long deliveryInformationId;
  int deliveryTypeId;
  int discountId;
}
