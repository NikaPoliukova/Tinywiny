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
  ProductInBucketDto toProductInBucketDto(ProductInBucket productInBucket);

  List<ProductInBucket> toProductInBucket(List<ProductInBucketDto> productsInBucketDto);

  /*@Mapping(target = "productInBucket.product", source = "productDto")
  @Mapping(target = "productInBucket.bucket.bucketId", source = "bucketId")
  ProductInBucket toProductInBucket(ProductInBucketDto productInBucketDto);*/
}