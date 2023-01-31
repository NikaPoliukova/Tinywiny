package com.example.tinywiny.controller;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.service.BucketService;
import com.example.tinywiny.service.ProductInBucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class BucketController {


  private final ProductInBucketService productInBucketService;

  @GetMapping("/products-in-bucket")
  public List<Product> findAllProductsInBucket(Long bucketId) {
    return productInBucketService.findAllProductInBucket(bucketId);
  }

  @PostMapping
  public void addProductInBucket(@RequestBody ProductInBucket productInBucket) {
    productInBucketService.save(productInBucket);
  }

  @DeleteMapping("/delete")
  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketService.deleteProductInBucket(productInBucketId);
  }

}
