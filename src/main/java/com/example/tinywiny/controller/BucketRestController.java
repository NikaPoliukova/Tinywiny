package com.example.tinywiny.controller;

import com.example.tinywiny.converter.BucketConverter;
import com.example.tinywiny.converter.ProductInBucketConverter;
import com.example.tinywiny.dto.BucketDto;
import com.example.tinywiny.dto.OrderSumDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.service.BucketService;
import com.example.tinywiny.service.UtilClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/bucket")
@RestController
@RequiredArgsConstructor
public class BucketRestController {

  private final BucketService bucketService;
  private final ProductInBucketConverter converter;
  private final BucketConverter bucketConverter;
  private final UtilClass utilClass;
  @GetMapping
  public List<ProductInBucketDto> findAllProductsInBucket() {
    Long userId= utilClass.getIdCurrentUser();
    Bucket bucket = bucketService.findBucketByUserId(userId);
    return converter.toProductInBucketDto(bucketService.findAllProductInBucket(bucket.getBucketId()));
  }

  @GetMapping("/sum/all")
  public OrderSumDto getSumProductInBucket() {
    Long userId= utilClass.getIdCurrentUser();
    Bucket bucket = bucketService.findBucketByUserId(userId);
    List<ProductInBucket> productInBucket = bucketService.findAllProductInBucket(bucket.getBucketId());
    int sum = bucketService.getSumProductInBucket(productInBucket);
    int sumWithDiscount = bucketService.getSumWithDiscount(sum);
    return new OrderSumDto(sum, sumWithDiscount);
  }

  @PostMapping("/product/{productId}")
  protected void addProductInBucket(@PathVariable Long productId) {
    bucketService.addProductInBucket(productId);
  }

  @PutMapping
  public ProductInBucketDto updateCountProduct(@RequestBody ProductInBucketDto productInBucketDto) {
    return converter.toProductInBucketDto(bucketService.updateCountProduct(productInBucketDto));
  }

  @DeleteMapping("/product/{productInBucketId}")
  public void deleteProductInBucket(@PathVariable Long productInBucketId) {
    bucketService.deleteProductInBucket(productInBucketId);
  }

  @DeleteMapping
  public void deleteAllProductsInBucket() {
    Long userId= utilClass.getIdCurrentUser();
    Bucket bucket = bucketService.findBucketByUserId(userId);
    bucketService.deleteAllProductsInBucket(bucket.getBucketId());
  }

  @GetMapping("/user")
  public BucketDto findBucketByUserId(@RequestParam Long userId) {
    Bucket bucket = bucketService.findBucketByUserId(userId);
    List<ProductInBucket> productsInBucket = bucket.getProductsInBucket();
    return bucketConverter.toBucketDto(bucket, productsInBucket);
  }
   /*//ТУТ ДОЛЖНО БЫТЬ ДРОБНОЕ ЧИСЛО
  @GetMapping("/final-sum")
  protected int getSumWithDiscount(@RequestParam int sum) {
    return bucketService.getSumWithDiscount(sum);
  }*/

/*
  @GetMapping
  public BucketDto findBucketByBucketId(@RequestBody Long bucketId) {
    return bucketConverter.toBucket(bucketService.findBucketByBucketId(bucketId));
  }

  @GetMapping("/sum/all")
  protected int getSumProductInBucket(@RequestBody List<ProductInBucketDto> productInBucketDto) {
    return bucketService.getSumProductInBucket(productInBucketDto);
  }*/
}