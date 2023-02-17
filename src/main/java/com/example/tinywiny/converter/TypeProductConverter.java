package com.example.tinywiny.converter;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.dto.TypeProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TypeProductConverter {

  TypeProductDto toDto(TypeProduct typeProduct);

  TypeProduct toTypeProduct(TypeProductDto type);

  List<TypeProductDto> toDto(List<TypeProduct> types);

}
