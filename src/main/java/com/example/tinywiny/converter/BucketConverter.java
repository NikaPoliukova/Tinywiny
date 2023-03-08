package com.example.tinywiny.converter;

import com.example.tinywiny.dto.BucketDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.ProductInBucket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface BucketConverter {
  BucketDto toBucket(Bucket bucket);

  @Mapping(target = "productsInBucketDto", source = "productsInBucket")
  @Mapping(target = "userId", source = "bucket.user.userId")
  BucketDto toBucketDto(Bucket bucket, List<ProductInBucket> productsInBucket);

  List<ProductInBucketDto> toProductInBucketDto(List<ProductInBucket> productInBucket);

  @Mapping(target = "productDto", source = "productInBucket.product")
  @Mapping(target = "bucketId", source = "productInBucket.bucket.bucketId")
  ProductInBucketDto toProductInBucketDto(ProductInBucket productInBucket);
}
