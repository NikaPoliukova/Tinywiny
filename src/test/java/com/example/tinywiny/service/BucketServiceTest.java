package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Discount;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.ProductInBucketRepository;
import com.example.tinywiny.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BucketServiceTest {

  @Mock
  private BucketRepository bucketRepository;
  @Mock
  private ProductService productService;
  @Mock
  private ProductInBucketRepository productInBucketRepository;
  @Mock
  private DiscountService discountService;
  @Mock
  private ProductRepository productRepository;
  @InjectMocks
  private BucketService bucketService;


  @Test
  void findAllProductInBucket() {
    when(bucketRepository.findBucketByBucketId(any(Long.class))).thenReturn(Optional.of(prepareBucket()));
    when(productInBucketRepository.findProductInBucketsByBucket(any(Bucket.class))).thenReturn(List.of(prepareProductInBucket()));
    List<ProductInBucket> actual = bucketService.findAllProductInBucket(1L);

    assertEquals(List.of(new ProductInBucket(1L, 1, null, prepareProduct())), actual);
  }


  @Test
  void findBucketByUserId() {
    when(bucketRepository.findBucketByUserUserId(any(Long.class))).thenReturn(Optional.of(prepareBucket()));
    Bucket actual = bucketService.findBucketByUserId(1L);

    assertEquals(new Bucket(1L, new User(), List.of(prepareProductInBucket())), actual);
  }

  @Test
  void updateCountProduct() {
    when(productInBucketRepository.findProductInBucketById(any(Long.class))).thenReturn(prepareProductInBucket());
    when(productInBucketRepository.save(any(ProductInBucket.class))).thenReturn(prepareProductInBucket());

    assertEquals(prepareProductInBucket(), bucketService.updateCountProduct(prepareProductInBucketDto()));
  }

  @Test
  void addProductInBucket() {
    when(bucketRepository.findBucketByUserUserId(any(Long.class))).thenReturn(Optional.of(prepareBucket()));
    when(productService.findProductByProductId(any(Long.class))).thenReturn(prepareProduct());
    when(productInBucketRepository.save(any(ProductInBucket.class))).thenReturn(prepareProductInBucket());
    bucketService.addProductInBucket(1L);

    verify(bucketRepository).findBucketByUserUserId(any());
    verify(productService).findProductByProductId(any());
    verify(productInBucketRepository).save(any());
  }

  @Test
  void getSumWithDiscount() {
    when(discountService.findDiscount(any(Integer.class))).thenReturn(prepareDiscount());
    int actual = bucketService.getSumWithDiscount(100);

    assertEquals(80, actual);
  }

  @Test
  void findProductPrice() {
    when(productRepository.findProductPrice(any(Long.class))).thenReturn(800);
    int actual = bucketService.findProductPrice(1L);

    assertEquals(800, actual);
  }

  @Test
  void getSumProductInBucket() {
    when(productRepository.findProductPrice(any(Long.class))).thenReturn(800);
    int actual = bucketService.getSumProductInBucket(List.of(prepareProductInBucket()));

    assertEquals(800, actual);
  }

  @Test
  void deleteProductInBucket() {
    doNothing().when(productInBucketRepository).deleteProductInBucketById(any(Long.class));
    bucketService.deleteProductInBucket(1L);

    verify(productInBucketRepository).deleteProductInBucketById(any());
  }

  @Test
  void deleteAllProductsInBucket() {
    when(bucketRepository.findBucketByBucketId(any(Long.class))).thenReturn(Optional.of(prepareBucket()));
    doNothing().when(productInBucketRepository).deleteAllByBucket(any(Bucket.class));
    bucketService.deleteAllProductsInBucket(1L);

    verify(productInBucketRepository).deleteAllByBucket(any());
  }

  @Test
  void createBucket() {
    doNothing().when(bucketRepository).createBucket(any(Long.class));
    bucketService.createBucket(1L);

    verify(bucketRepository).createBucket(any());
  }

  private Bucket prepareBucket() {
    return new Bucket(1L, new User(), List.of(prepareProductInBucket()));
  }

  private ProductInBucket prepareProductInBucket() {
    return new ProductInBucket(1L, 1, null, prepareProduct());
  }

  private Product prepareProduct() {
    return new Product(1L, "productName", 123, "description", 2, new TypeProduct(), new ArrayList<>(), new ArrayList<>());
  }

  private ProductInBucketDto prepareProductInBucketDto() {
    return new ProductInBucketDto(1L, 8, null, 1L);
  }

  private Discount prepareDiscount() {
    return new Discount(1, 20);
  }
}