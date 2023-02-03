package com.example.tinywiny.service;

import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imageRepository;

  @Transactional
  @Modifying
  public Image save(Image image) {
    return imageRepository.save(image);
  }

  @Transactional
  @Modifying
  public void deleteImageByImageId(Long imageId) {
    imageRepository.deleteImageByImageId(imageId);
  }
}

