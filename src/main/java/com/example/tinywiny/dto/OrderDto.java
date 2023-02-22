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
  Date createdAt;
  String statusOrder;
  String paymentStatus;
  Long userId;
  DeliveryInformationDto deliveryInformation;
  int deliveryTypeId;
  int discountId;
  List<ProductInOrderDto> productsInOrder;


}
