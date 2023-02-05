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
  Long orderId;
  String commentOrder;
  Long userId;
  Long deliveryInformationId;
  int deliveryTypeId;
  String status;

}
