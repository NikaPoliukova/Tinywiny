package com.example.tinywiny.service;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ImageRepository;
import com.example.tinywiny.repository.ProductRepository;
import com.example.tinywiny.service.amazonService.AwsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static com.example.tinywiny.service.BucketServiceTest.prepareProduct;
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
  @Mock
  private ProductRepository productRepository;
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
    MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes());

    when(productService.findProductByProductName(any(String.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(null);
    doNothing().when(imageRepository).setNewImage(any(String.class), any(Long.class));
    doNothing().when(storageService).uploadFile(any(InputStream.class), any(String.class));
    imageService.addImage("product", file);
    verify(productService).findProductByProductName(any(String.class));
    verify(imageRepository).setNewImage(any(String.class), any(Long.class));
    verify(imageRepository).findImageByProductId(any(Long.class));
    verify(storageService).uploadFile(any(InputStream.class), any(String.class));
  }

  @Test
  void addImageWithImage() throws IOException {
    MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes());

    when(productService.findProductByProductName(any(String.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(prepareImage());
    doNothing().when(imageRepository).updateImage(any(String.class), any(Long.class));
    doNothing().when(storageService).uploadFile(any(InputStream.class), any(String.class));
    imageService.addImage("product", file);
    verify(productService).findProductByProductName(any(String.class));
    verify(imageRepository).findImageByProductId(any(Long.class));
    verify(imageRepository).updateImage(any(String.class), any(Long.class));
    verify(storageService).uploadFile(any(InputStream.class), any(String.class));
  }

  @Test
  void addProductImageByIdWithoutImage() throws IOException {
    MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes());

    when(productService.findProductByProductId(any(Long.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(null);
    doNothing().when(imageRepository).setNewImage(any(String.class), any(Long.class));
    doNothing().when(storageService).uploadFile(any(InputStream.class), any(String.class));
    imageService.addProductImage(1L, file);

    verify(productService).findProductByProductId(any(Long.class));
    verify(imageRepository).setNewImage(any(String.class), any(Long.class));
    verify(imageRepository).findImageByProductId(any(Long.class));
    verify(storageService).uploadFile(any(InputStream.class), any(String.class));
  }

  @Test
  void addProductImageByIdWithImage() throws IOException {
    MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
        "Hello, World!".getBytes());

    when(productService.findProductByProductId(any(Long.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(prepareImage());
    doNothing().when(imageRepository).updateImage(any(String.class), any(Long.class));
    doNothing().when(storageService).uploadFile(any(InputStream.class), any(String.class));
    imageService.addProductImage(1L, file);

    verify(productService).findProductByProductId(any(Long.class));
    verify(imageRepository).findImageByProductId(any(Long.class));
    verify(imageRepository).updateImage(any(String.class), any(Long.class));
    verify(storageService).uploadFile(any(InputStream.class), any(String.class));
  }

  @Test
  void getImagePath() throws URISyntaxException {
    URI uri = new URI("image");
    when(storageService.getImagePath(any(String.class))).thenReturn(uri);
    imageService.getImagePath("image");
    verify(storageService).getImagePath(any(String.class));
  }

  @Test
  void deleteImage() {
    when(productService.findProductByProductId(any(Long.class))).thenReturn(prepareProduct());
    when(imageRepository.findImageByProductId(any(Long.class))).thenReturn(prepareImage());
    doNothing().when(storageService).deleteImage(any(String.class));
    doNothing().when(imageRepository).deleteImageByProductId(any(Long.class));
    imageService.deleteImage(5L);

    verify(productService).findProductByProductId(any(Long.class));
    verify(imageRepository).findImageByProductId(any(Long.class));
    verify(storageService).deleteImage(any(String.class));
    verify(imageRepository).deleteImageByProductId(any(Long.class));
  }

  private Image prepareImage() {
    return new Image(1L, "image.png", 2L);
  }
}