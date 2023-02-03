package com.example.tinywiny.converter;

import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.model.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderConverter {
  Order toOrder(OrderDto orderDto);

  OrderDto toOrderDto(Order order);
  List <OrderDto> toOrderDto(List<Order> order);
}
