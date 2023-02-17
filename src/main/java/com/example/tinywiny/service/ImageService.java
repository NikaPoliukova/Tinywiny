package com.example.tinywiny.service;

import com.example.tinywiny.converter.ImageConverter;
import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.dto.ImageDto;
import com.example.tinywiny.dto.ProductDto;
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
  private final ImageConverter converter;
  private final ProductService productService;
  private final ProductConverter productConverter;

  @Transactional
  @Modifying
  public void saveNewImage(Image image) {
    if (image != null) {
      imageRepository.save(image);
    } else {
      throw new RuntimeException("no image");
    }
  }
  public Image findImageByProduct(Product product) {
    return imageRepository.findImageByProduct(product);
  }

  @Modifying
  public void updateImage(ImageDto imageDto, MultipartFile file) throws IOException {
    Product product = productService.findProductByProductId(imageDto.getProductId());
    ProductDto productDto = productConverter.toProductDto(product);
    Image image = converter.toImage(imageDto, productDto);
    if (findImageByProduct(product) != null) {
      image.setImageName(imageDto.getImageName());
      imageRepository.save(image);
    } else {
      saveNewImage(image);
    }
    upload(file.getInputStream(), file.getOriginalFilename());
  }

  public URI getImagePath(String imageName) throws URISyntaxException {
    return storageService.getImagePath(imageName);
  }
//может удалять по айди картинки?
  public void deleteImage(Long productId) {
    Product product = productService.findProductByProductId(productId);
    if (product == null) {
      throw new RuntimeException("no product");
    }
    String imageName = findImageByProduct(product).getImageName();
    storageService.deleteImage(imageName);
    imageRepository.deleteImageByProduct(product);
  }

  public void upload(InputStream stream, String fileName) {
    storageService.uploadFile(stream, fileName);
  }

 /* public Image findImageByImageName(String imageName) {
    if (imageName == null) {
      throw new RuntimeException("no image");
    } else {
      return imageRepository.findImageByImageName(imageName);
    }
  }*/

}


