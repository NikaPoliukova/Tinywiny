package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.converter.TypeProductConverter;
import com.example.tinywiny.dto.ImageDto;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.dto.TypeProductDto;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.service.BucketService;
import com.example.tinywiny.service.ImageService;
import com.example.tinywiny.service.ProductService;
import com.example.tinywiny.service.TypeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsRestController {
  private final ProductService productService;
  private final ProductConverter converter;
  private final BucketService bucketService;
  private final ImageService imageService;
  private final TypeProductService typeProductService;
  private final TypeProductConverter typeProductConverter;
  //GET image????
  @PostMapping("/create")
  protected ProductDto createProduct(@RequestBody ProductDto product) {
    return converter.toProductDto(productService.save(product));
  }

  @PostMapping
  protected void addProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.addProductInBucket(productInBucketDto);
  }

  //не знаю,как отобразить
  @GetMapping("/product/{productId}")
  public ProductDto getProduct(@PathVariable Long productId) throws URISyntaxException {
    Product product = productService.findProductByProductId(productId);
    URI imageUrl = null;
    if (product.getImage() != null) {
      imageUrl = imageService.getImagePath(product.getImage().getImageName()); //как ее отобразить на UI?
    }
    return converter.toProductDto(product);
  }

  @PutMapping
  public void updateProduct(@RequestBody ProductDto productDto) {
    productService.updateProduct(productDto);
  }

  @PutMapping("/update/count-in-stock")
  public void updateCountInStock(@RequestBody ProductDto product) {
    productService.updateCountInStock(product.getCountInStock(), product.getProductId());
  }

  //НЕ ЗНАЮ КАК ОБНОВИТЬ КАРТИНКУ
  @PutMapping("/image")
  public ImageDto updateImage(@RequestBody ImageDto imageDto, MultipartFile file) throws IOException {
    imageService.updateImage(imageDto, file);
    return imageDto;
  }

  @DeleteMapping("/{productId}/image")
  public void deleteImage(@PathVariable Long productId) {
    imageService.deleteImage(productId);
  }

  @GetMapping("/type/{typeName}")
  public List<ProductDto> findAllProductsByTypeAndPage(@PathVariable String typeName,
                                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.findAllProductsByTypeAndPage(typeName, pageNumber - 1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }

  @GetMapping("/type")
  public List<TypeProductDto> findAllTypes(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<TypeProduct> page = typeProductService.findAllType(pageNumber - 1, pageSize);
    List<TypeProduct> types = page.getContent();
    return typeProductConverter.toDto(types);
  }

  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }
}


