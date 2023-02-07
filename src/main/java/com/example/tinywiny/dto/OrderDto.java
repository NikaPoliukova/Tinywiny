package com.example.tinywiny.dto;

import com.example.tinywiny.model.DeliveryInformation;
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
  DeliveryInformation deliveryInformation;
  int deliveryTypeId;
  String statusOrder;
  List<ProductInOrderDto> productsInOrder;
  Date createdAt;


}
