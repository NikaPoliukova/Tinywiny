package com.example.tinywiny.repository;

import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInBucketRepository extends JpaRepository<ProductInBucket, Long> {

  @Modifying
  void deleteProductInBucketById(@Param("id")Long id);

 // @Query( value="select product_id from product_in_bucket where bucket_id =:bucketId?", nativeQuery = true)
  List<Long> getProductInBucketByBucket(@Param("bucketId") Long bucketId);


}

