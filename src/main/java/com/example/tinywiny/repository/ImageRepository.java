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
  void deleteImageByProduct(@Param("product") Product product);

}