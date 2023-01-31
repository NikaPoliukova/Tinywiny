package com.example.tinywiny.repository;

import com.example.tinywiny.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

  @Modifying
  void deleteImageByImageId(@Param("imageId")Long imageId);



  }