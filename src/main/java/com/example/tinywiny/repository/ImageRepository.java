package com.example.tinywiny.repository;

import com.example.tinywiny.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ImageRepository extends JpaRepository<Image, Long> {

  Image findImageByProductId(@Param("productId") Long productId);

  @Modifying
  @Query(value = "update image set image_name=:imageName where product_id=:productId", nativeQuery = true)
  void updateImage(@Param("imageName") String imageName, @Param("productId") long productId);

  @Modifying
  void deleteImageByProductId(@Param("productId") Long productId);

  @Modifying
  @Query(value = "insert into image (image_name, product_id) values (:imageName, :productId)", nativeQuery = true)
  void setNewImage(@Param("imageName") String imageName, @Param("productId") long productId);
}