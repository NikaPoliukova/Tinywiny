package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ImageDto;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ImageConverter {

  @Mapping(target = "product", source = "productDto")
  Image toImage(ImageDto imageDto, ProductDto productDto);

  @Mapping(target = "productId", source = "product.productId")
  ImageDto toImageDto(Image image);

}
