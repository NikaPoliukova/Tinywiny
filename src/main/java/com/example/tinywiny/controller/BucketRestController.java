package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.converter.ProductInBucketConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.service.BucketService;
import com.example.tinywiny.service.ProductInOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  private final ProductInOrderService productInOrderService;

  //work
  @GetMapping
  public List<ProductInBucketDto> findAllProductsInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    List<ProductInBucket> products = bucketService.findAllProductInBucket(productInBucketDto);
    return converter.toProductInBucketDto(products);
  }

  //WORK
  @PutMapping
  public ProductInBucketDto updateCountProduct(@RequestBody ProductInBucketDto productInBucketDto) {
    return converter.toProductInBucketDto(bucketService.updateCountProduct(productInBucketDto));
  }

  //WORK
  @DeleteMapping
  public void deleteProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.deleteProductInBucket(productInBucketDto.getId());
  }

  @PostMapping
  public void addProductsInOrder(List<ProductInBucketDto> productInBucket) {
    productInOrderService.addProductsInOrder(productInBucket);
  }
}
