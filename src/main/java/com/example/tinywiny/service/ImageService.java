package com.example.tinywiny.service;

import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ImageRepository;
import com.example.tinywiny.service.amazonService.AwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Log4j2
@Service
@RequiredArgsConstructor
public class ImageService {

  private final AwsService storageService;
  private final ImageRepository imageRepository;
  private final ProductService productService;

  @Transactional
  @Modifying
  public void saveNewImage(String imageName, Long productId) {
    imageRepository.setNewImage(imageName, productId);
  }

  public Image findImageByProductId(Long productId) {
    return imageRepository.findImageByProductId(productId);
  }

  @Transactional
  @Modifying
  public void updateImage(String imageName, Long productId) {
    imageRepository.updateImage(imageName, productId);
  }

  @Transactional
  @Modifying
  public void upload(InputStream stream, String fileName) {
    storageService.uploadFile(stream, fileName);
  }

  @Transactional
  @Modifying
  public void addImage(String productName, MultipartFile file) throws IOException {
    Product product = productService.findProductByProductName(productName);
    Image image = findImageByProductId(product.getProductId());
    if (image != null) {
      updateImage(file.getOriginalFilename(), product.getProductId());
    } else {
      saveNewImage(file.getOriginalFilename(), product.getProductId());
    }
    upload(file.getInputStream(), file.getOriginalFilename());
  }

  @Transactional
  @Modifying
  public void addProductImage(Long productId, MultipartFile file) throws IOException {
    Product product = productService.findProductByProductId(productId);
    Image image = findImageByProductId(product.getProductId());
    if (image != null) {
      updateImage(file.getOriginalFilename(), product.getProductId());
    } else {
      saveNewImage(file.getOriginalFilename(), product.getProductId());
    }
    upload(file.getInputStream(), file.getOriginalFilename());
  }

  public URI getImagePath(String imageName) throws URISyntaxException {
    return storageService.getImagePath(imageName);
  }

  @Transactional
  @Modifying
  public void deleteImage(Long productId) {
    Product product = productService.findProductByProductId(productId);
    if (product == null) {
      throw new RuntimeException("no product");
    }
    String imageName = findImageByProductId(productId).getImageName();
    storageService.deleteImage(imageName);
    imageRepository.deleteImageByProductId(productId);
  }
}


