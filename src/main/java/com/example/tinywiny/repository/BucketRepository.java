package com.example.tinywiny.repository;

import com.example.tinywiny.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
  @Modifying
  @Query(value = "insert into bucket (user_id) value (:userId)", nativeQuery = true)
  Bucket createBucket(@Param("userId")Long userId);



}
