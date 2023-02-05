package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.converter.ProductInBucketConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/bucket")
@RestController
@RequiredArgsConstructor
public class BucketRestController {

  private final BucketService bucketService;
  private final ProductInBucketConverter converter;

//work
 @GetMapping
  public List<ProductInBucketDto> findAllProductsInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
   List<ProductInBucket> products= bucketService.findAllProductInBucket(productInBucketDto);
    return converter.toProductInBucketDto(products);
  }
  //work
  @PostMapping
  protected void addProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.addProductInBucket(productInBucketDto);
  }
//WORK
  @DeleteMapping
  public void deleteProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.deleteProductInBucket(productInBucketDto.getId());
  }

}
