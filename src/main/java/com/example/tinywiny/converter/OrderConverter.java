package com.example.tinywiny.converter;

import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.ProductInOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderConverter {

  @Mapping(target = "user.userId", source = "orderDto.userId")
  @Mapping(target = "deliveryType.idType", source = "orderDto.deliveryTypeId")
  Order toOrder(OrderDto orderDto);

  List<OrderDto> toOrderDto(List<Order> order);

  @Mapping(target = "userId", source = "order.user.userId")
  @Mapping(target = "deliveryInformationDto", source = "order.deliveryInformation")
  @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
  OrderDto toOrderDto(Order order);

  @Mapping(target = "userId", source = "order.user.userId")
  @Mapping(target = "deliveryInformationDto", source = "deliveryInformation")
  @Mapping(target = "productsInOrder", source = "productInOrder")
  @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
  OrderDto toOrderDto(Order order, DeliveryInformation deliveryInformation, List<ProductInOrder> productInOrder);

/*
 @Mapping(target = "userId", source = "order.user.userId")
 @Mapping(target = "deliveryInformationDto", source = "deliveryInformation")
 @Mapping(target = "productsInOrder", source = "productInOrder")
 @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
 OrderDto toOrderDto(Order order, DeliveryInformation deliveryInformation, List<ProductInOrder> productInOrder);*/
}
