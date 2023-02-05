package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.ProductInBucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ProductInBucketConverter {


  List<ProductInBucketDto> toProductInBucketDto(List<ProductInBucket> product);

  @Mapping(target = "productId",source = "productInBucket.product.productId")
  @Mapping(target = "bucketId",source = "productInBucket.bucket.bucketId")
  ProductInBucketDto  toProductInBucketDto(ProductInBucket productInBucket);
}