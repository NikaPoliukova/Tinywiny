package com.example.tinywiny.service;

import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.repository.ProductInBucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductInBucketService {

  private final ProductInBucketRepository productInBucketRepository;

  public List<Product> findAll(Long bucketId) {
    productInBucketRepository.findAllByB(bucketId);
  }

  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketRepository.deleteProductInBucketById(productInBucketId);
  }

  public ProductInBucket save(ProductInBucket product) {
    return productInBucketRepository.save(product);
  }
}
