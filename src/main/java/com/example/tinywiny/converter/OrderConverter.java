package com.example.tinywiny.converter;

import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.model.Discount;
import com.example.tinywiny.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface OrderConverter {

  @Mapping(target = "user", source = "user")
  @Mapping(target = "deliveryInformation", source = "deliveryInformation")
  @Mapping(target = "deliveryType", source = "deliveryType")
  Order toOrder(OrderDto orderDto, UserDto user, DeliveryInformationDto deliveryInformation,
                DeliveryType deliveryType, List<ProductInOrderDto> productsInOrder, Discount discount);

  @Mapping(target = "userId", source = "order.user.userId")
  @Mapping(target = "deliveryInformationDto", source = "order.deliveryInformation")
  @Mapping(target = "deliveryTypeId", source = "order.deliveryType.idType")
  @Mapping(target = "status", source = "order.statusOrder")
  OrderDto toOrderDto(Order order);

  List<OrderDto> toOrderDto(List<Order> order);
}
