package com.example.tinywiny.repository;

import com.example.tinywiny.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
  @Transactional
  @Modifying
  @Query(value = "insert into bucket (user_id) values (:userId)", nativeQuery = true)
  void createBucket(@Param("userId")Long userId);



}
