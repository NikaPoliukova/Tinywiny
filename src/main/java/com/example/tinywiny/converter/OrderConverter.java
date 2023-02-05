package com.example.tinywiny.converter;

import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderConverter {
  Order toOrder(OrderDto orderDto);

@Mapping(target = "userId", source ="order.user.userId" )
@Mapping(target = "deliveryInformationId",source ="order.deliveryInformation.deliveryInformationId" )
@Mapping(target= "deliveryTypeId", source ="order.deliveryType.idType")
  OrderDto toOrderDto(Order order);

  List <OrderDto> toOrderDto(List<Order> order);
}
