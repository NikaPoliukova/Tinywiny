package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProductDto;
import com.example.tinywiny.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductConverter {
  @Mapping(target = "typeProduct", source = "typeProduct")
  Product toProduct(ProductDto productDto, TypeProductDto typeProduct);

  List<ProductDto> toProductDto(List<Product> product);

  @Mapping(target = "idType", source = "product.typeProduct.id")
  @Mapping(target = "imageDto", source = "image")
  ProductDto toProductDto(Product product);
}

