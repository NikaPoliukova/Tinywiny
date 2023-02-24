package com.example.tinywiny.dto;

import com.example.tinywiny.model.ProductInBucket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountDto {
  int discountId;
  int size;
  List<ProductInBucketDto> productInBucketDto;
}
