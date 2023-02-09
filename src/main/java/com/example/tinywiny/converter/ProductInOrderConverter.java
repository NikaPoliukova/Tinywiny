package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.model.ProductInOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductInOrderConverter {

 List<ProductInOrder> toProductInOrder(List<ProductInOrderDto> productInOrderDto);

  @Mapping(target = "order.orderId", source = "orderId")
  @Mapping(target = "product.productId", source = "productId")
  ProductInOrder toProductInOrder(ProductInOrderDto productInOrderDto);

  List<ProductInOrderDto> toProductInOrderDto(List<ProductInOrder> products);

 @Mapping(target = "orderId", source = "order.orderId")
 @Mapping(target = "productId", source = "product.productId")
 ProductInOrderDto toProductInOrderDto(ProductInOrder productInOrder);
}
