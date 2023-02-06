package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.model.ProductInOrder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductInOrderConverter {

  List<ProductInOrder> toProductInOrder(List<ProductInOrderDto> productInOrderDto);

}
