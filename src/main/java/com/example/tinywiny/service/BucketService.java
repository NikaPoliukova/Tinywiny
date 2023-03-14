package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Discount;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import com.example.tinywiny.repository.ProductRepository;
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
  private final UtilClass utilClass;
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
    Optional<Bucket> bucket = bucketRepository.findBucketByUserUserId(userId);
    return bucket.get();
  }

  public ProductInBucket updateCountProduct(ProductInBucketDto productInBucketDto) {
    ProductInBucket productInBucket = productInBucketRepository.findProductInBucketById(productInBucketDto.getId());
    productInBucket.setCount(productInBucketDto.getCount());
    return productInBucketRepository.save(productInBucket);
  }


  public void addProductInBucket(Long productId) {
    final int count = 1;
    Long userId =utilClass.getIdCurrentUser();
    Optional<Bucket> bucket = bucketRepository.findBucketByUserUserId(userId);
    ProductInBucket productInBucket = new ProductInBucket();
    Product product = productService.findProductByProductId(productId);
    productInBucket.setBucket(bucket.get());
    productInBucket.setProduct(product);
    productInBucket.setCount(count);
    productInBucketRepository.save(productInBucket);
  }

  public int getSumWithDiscount(int sum) {
    Discount discount;
    if (sum > 0) {
      discount = discountService.findDiscount(sum);
      int sumDiscount = (sum / 100) * discount.getSize();
      return sum - sumDiscount;
    } else {
      throw new RuntimeException("sum order incorrect");
    }
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
    Optional<Bucket> bucket = bucketRepository.findBucketByBucketId(bucketId);
    productInBucketRepository.deleteAllByBucket(bucket.get());
  }

  public void createBucket(Long userId) {
    bucketRepository.createBucket(userId);
  }
}
