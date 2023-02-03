package com.example.tinywiny.repository;

import com.example.tinywiny.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ImageRepository extends JpaRepository<Image, Long> {

  @Transactional
  @Modifying
  void deleteImageByImageId(@Param("imageId")Long imageId);



  }