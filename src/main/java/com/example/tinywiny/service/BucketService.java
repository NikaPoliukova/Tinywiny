package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Discount;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import com.example.tinywiny.repository.ProductRepository;
import com.example.tinywiny.repository.UserRepository;
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
  private final DiscountService discountService;
  private final UserRepository userRepository;
  private final ProductRepository productRepository;

  public List<ProductInBucket> findAllProductInBucket(Long bucketId) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(bucketId);
    if (bucket.isEmpty()) {
      return Collections.emptyList();
    }
    return productInBucketRepository.findProductInBucketsByBucket(bucket.get());
  }

  public Bucket findBucketByBucketId(Long bucketId) {
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(bucketId);
    if (bucket.isEmpty()) {
      throw new RuntimeException("bucket is not exist");
    }
    return bucket.get();
  }
  public Bucket findBucketByUserId(Long userId) {
    Optional<User> user = userRepository.findUserByUserId(userId);
    if (user.isEmpty()) {
      throw new RuntimeException("no such user exists");
    }
    return user.get().getBucket();
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
    Product product = productService.findProductByProductId(productInBucketDto.getProductDto().getProductId());
    productInBucket.setBucket(bucket.get());
    productInBucket.setProduct(product);
    productInBucket.setCount(productInBucketDto.getCount());
    productInBucketRepository.save(productInBucket);
  }

  public int getSumWithDiscount(int sum) {
    Discount discount;
    if (sum > 0) {
      discount = discountService.findDiscount(sum);
      int sumDiscount = (sum / 100) * discount.getSize();
      return sum - sumDiscount;
    }
    return sum;
  }
  public int findProductPrice(Long productId) {
    return productRepository.findProductPrice(productId);
  }
  public int getSumProductInBucket(List<ProductInBucket> productInBucket) {
    int sum = 0;
    for (ProductInBucket product : productInBucket) {
      int count = product.getCount();
      sum += count * (findProductPrice(product.getProduct().getProductId()));
    }
    return sum;
  }

  public void deleteProductInBucket(Long productInBucketId) {
    productInBucketRepository.deleteProductInBucketById(productInBucketId);
  }
  public void deleteAllProductsInBucket(Long bucketId) {
    Optional<Bucket> bucket =bucketRepository.findBucketByBucketId(bucketId);
    productInBucketRepository.deleteAllByBucket(bucket.get());
  }

  public void createBucket(Long userId) {
    bucketRepository.createBucket(userId);
  }
}
