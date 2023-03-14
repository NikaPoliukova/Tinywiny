package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.model.ProductInOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductInOrderConverter {

  List<ProductInOrder> toProductInOrder(List<ProductInOrderDto> productInOrderDto);

  @Mapping(target = "order.orderId", source = "productInOrderDto.orderId")
  @Mapping(target = "product", source = "productDto")
  ProductInOrder toProductInOrder(ProductInOrderDto productInOrderDto, ProductDto productDto);

  List<ProductInOrderDto> toProductInOrderDto(List<ProductInOrder> products);

  @Mapping(target = "orderId", source = "order.orderId")
  @Mapping(target = "productDto", source = "product")
  ProductInOrderDto toProductInOrderDto(ProductInOrder productInOrder);


}

