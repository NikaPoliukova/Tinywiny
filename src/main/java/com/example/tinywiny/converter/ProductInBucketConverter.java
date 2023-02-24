package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.ProductInBucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductInBucketConverter {

  List<ProductInBucketDto> toProductInBucketDto(List<ProductInBucket> product);

  @Mapping(target = "productDto", source = "productInBucket.product")
  @Mapping(target = "bucketId", source = "productInBucket.bucket.bucketId")
  //@Mapping(target = "discountId", source = "productInBucket.discount.discountId")
  ProductInBucketDto toProductInBucketDto(ProductInBucket productInBucket);
}