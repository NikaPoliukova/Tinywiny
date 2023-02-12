package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BucketService {

  private final BucketRepository bucketRepository;
  private final ProductService productService;
  private final ProductInBucketRepository productInBucketRepository;

  public List<ProductInBucket> findAllProductInBucket(Long bucketId) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(bucketId);
    if (bucket.isEmpty()) {
      return Collections.emptyList();
    }
    return productInBucketRepository.findAllByBucket(bucket.get());
  }

  public ProductInBucket updateCountProduct(ProductInBucketDto productInBucketDto) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(productInBucketDto.getBucketId());
    ProductInBucket productInBucket = productInBucketRepository.findProductInBucketByBucket(bucket.get());
    productInBucket.setCount(productInBucketDto.getCount());
    return productInBucketRepository.save(productInBucket);
  }


  public void addProductInBucket(ProductInBucketDto productInBucketDto) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(productInBucketDto.getBucketId());
    ProductInBucket productInBucket = new ProductInBucket();
    Product product = productService.findProductByProductId(productInBucketDto.getProductId());
    productInBucket.setBucket(bucket.get());
    productInBucket.setProduct(product);
    productInBucket.setCount(productInBucketDto.getCount());
    productInBucketRepository.save(productInBucket);
  }

  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketRepository.deleteProductInBucketById(productInBucketId);
  }

  public void createBucket(Long userId) {
    bucketRepository.createBucket(userId);
  }
}
