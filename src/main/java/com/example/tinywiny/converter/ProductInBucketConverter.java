package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProductInBucketConverter {

  ProductInBucket toProductInBucket(ProductInBucketDto productDto);

  List<ProductInBucketDto> toProductInBucketDto(ProductInBucket product);
}
