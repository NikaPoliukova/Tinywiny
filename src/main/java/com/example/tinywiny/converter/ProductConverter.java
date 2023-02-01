package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductConverter {

  Product toProduct(ProductDto productDto);

  List<ProductDto> toProductDto(List<Product> product);
}
