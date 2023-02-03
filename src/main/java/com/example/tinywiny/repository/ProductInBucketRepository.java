package com.example.tinywiny.repository;

import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInBucketRepository extends JpaRepository<ProductInBucket, Long> {

  @Query(value = "select product_id from product_in_bucket where bucket_id =:bucketId?", nativeQuery = true)
  List<Product> findAllProductInBucket(@Param("id") Long bucketId);

  @Transactional
  @Modifying
  @Query(value = "insert into product_in_bucket (product_id) values (:productId) where bucket_id =:bucketId?", nativeQuery = true)
  void addProductInBucket(@Param("bucketId") Long bucketId, @Param("productId") Long productId);

  @Transactional
  @Modifying
  void deleteProductInBucketById(@Param("id") Long id);
}

