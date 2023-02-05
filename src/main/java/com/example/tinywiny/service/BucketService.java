package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
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
import java.util.Map;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BucketService {

  private final BucketRepository bucketRepository;
  private final ProductService productService;
  private final ProductInBucketRepository productInBucketRepository;


  public List<ProductInBucket> findAllProductInBucket(ProductInBucketDto productInBucketDto) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(productInBucketDto.getBucketId());
    if (bucket.isEmpty()) {
      throw new RuntimeException("bucket does not exist");
    }
   return productInBucketRepository.findAllByBucket(bucket.get());
  }

  public void addProductInBucket(ProductInBucketDto productInBucketDto) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(productInBucketDto.getBucketId());
    ProductInBucket productInBucket = new ProductInBucket();
    Product product = productService.findProductByProductId(productInBucketDto.getProductId());
    if (bucket.isPresent()) {
      productInBucket.setBucket(bucket.get());
      productInBucket.setProduct(product);
      productInBucket.setCount(productInBucketDto.getCount());
      productInBucketRepository.save(productInBucket);
    } else {
      throw new RuntimeException("bucket does not exist");
    }
  }

  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketRepository.deleteProductInBucketById(productInBucketId);
  }

  public void createBucket(Long userId) {
    bucketRepository.createBucket(userId);
  }

}
