package com.example.tinywiny.repository;

import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ImageRepository extends JpaRepository<Image, Long> {

  Image findImageByImageName(@Param("imageName") String imageName);

  Image findImageByProduct(@Param("product") Product product);

  @Modifying
  @Query(value ="update image set image_name=:imageName where product_id=:productId",nativeQuery = true)
  void updateImage(@Param("imageName") String imageName, @Param("productId") long productId);

  @Modifying
  void deleteImageByProduct(@Param("product") Product product);

  @Modifying
  @Query(value ="insert into image (image_name, product_id) values (:imageName, :productId)", nativeQuery= true)
  void setNewImage(@Param("imageName") String imageName, @Param("productId") long productId);
}