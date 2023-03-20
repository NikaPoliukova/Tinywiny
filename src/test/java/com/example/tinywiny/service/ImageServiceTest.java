package com.example.tinywiny.service;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ImageRepository;
import com.example.tinywiny.service.amazonService.AwsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {
  @Mock
  private AwsService storageService;
  @Mock
  private ImageRepository imageRepository;
  @Mock
  private ProductService productService;
  @InjectMocks
  private ImageService imageService;

  @Test
  void saveNewImage() {
    doNothing().when(imageRepository).setNewImage(any(String.class), any(Long.class));
    imageService.saveNewImage("nika", 1L);
    verify(imageRepository).setNewImage(any(String.class), any(Long.class));
  }

  @Test
  void findImageByProductId() {
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(prepareImage());
    Image actual = imageService.findImageByProductId(1L);
    assertEquals(new Image(1L, "image.png", 2L), actual);
  }

  @Test
  void updateImage() {
    doNothing().when(imageRepository).updateImage(any(String.class), any(Long.class));
    imageService.updateImage("rings", 3L);
    verify(imageRepository).updateImage(any(String.class), any(Long.class));
  }

  @Test
  void upload() {
    doNothing().when(storageService).uploadFile(any(InputStream.class), anyString());
    imageService.upload(InputStream.nullInputStream(), "file");

    verify(storageService).uploadFile(any(InputStream.class), anyString());
  }

  @Test
  void addImageWhereNoImage() throws IOException {
    when(productService.findProductByProductName(any(String.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(prepareImage());
    doNothing().when(imageRepository).setNewImage(any(String.class), any(Long.class));
    doNothing().when(storageService).uploadFile(any(InputStream.class),any(String.class));
   // imageService.addImage(any(String.class),MultipartFile);
    verify(productService).findProductByProductName(any(String.class));
    verify(imageRepository).setNewImage(any(String.class), any(Long.class));
    verify(storageService).uploadFile(any(InputStream.class), any(String.class));
  }

  @Test
  void addProductImage() {
  }

  @Test
  void getImagePath() {
  }

  @Test
  void deleteImage() {
  }

  private Image prepareImage() {
    return new Image(1L, "image.png", 2L);
  }

  private Product prepareProduct() {
    return new Product(1L, "productName", 123, "description", 2, new TypeProduct(), new ArrayList<>(), new ArrayList<>());
  }

}