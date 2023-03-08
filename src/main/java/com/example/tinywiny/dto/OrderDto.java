package com.example.tinywiny.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
  Long orderId;
  String commentOrder;
  Long userId;
  DeliveryInformationDto deliveryInformationDto;
  int deliveryTypeId;
  String statusOrder;
  Date createdAt;
  String paymentStatus;
  int sum;
  List<ProductInOrderDto> productsInOrder;


}
