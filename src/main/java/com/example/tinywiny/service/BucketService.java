package com.example.tinywiny.service;

import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BucketService {

  private final BucketRepository bucketRepository;
  private final ProductInBucketRepository productInBucketRepository;

  /*public List<Product> findAllProductInBucket(Long bucketId) {
    return productInBucketRepository.findAllProductInBucket(bucketId);
  }*/

  public void addProductInBucket(Long bucketId, Long productId) {
    productInBucketRepository.addProductInBucket(bucketId, productId);
  }

  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketRepository.deleteProductInBucketById(productInBucketId);
  }

  public void createBucket(Long userId) {
    bucketRepository.createBucket(userId);
  }

}
