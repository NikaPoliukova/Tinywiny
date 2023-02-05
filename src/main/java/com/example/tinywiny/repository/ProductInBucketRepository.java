package com.example.tinywiny.repository;

import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductInBucketRepository extends JpaRepository<ProductInBucket, Long> {

   List<ProductInBucket> findAllByBucket(@Param("bucket") Bucket bucket);

  @Transactional
  @Modifying
  void deleteProductInBucketById(@Param("id") Long id);
}

