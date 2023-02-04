package com.example.tinywiny.converter;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.dto.TypeProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TypeProductConverter {

  TypeProductDto toDto(TypeProduct typeProduct);

}
