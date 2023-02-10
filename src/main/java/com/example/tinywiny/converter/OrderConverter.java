package com.example.tinywiny.converter;

import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.model.Order;
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
  @Mapping(target = "deliveryInformation", source = "deliveryInformation")
  @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
  @Mapping(target = "statusOrder", source = "statusOrder")
  OrderDto toOrderDto(Order order);

  @Mapping(target = "userId", source = "order.user.userId")
  @Mapping(target = "deliveryInformation", source = "deliveryInformation")
  @Mapping(target = "productsInOrder", source = "productInOrderDto")
  @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
  OrderDto toOrderDto(Order order, DeliveryInformationDto deliveryInformation, List<ProductInOrderDto> productInOrderDto);
}
