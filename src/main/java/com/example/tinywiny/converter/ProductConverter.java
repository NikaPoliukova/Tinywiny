package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.model.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductConverter {
  Product toProduct(ProductDto productDto);
}
