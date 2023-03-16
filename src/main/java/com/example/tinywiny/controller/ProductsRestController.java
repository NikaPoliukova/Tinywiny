package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.converter.TypeProductConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.dto.TypeProductDto;
import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.service.ImageService;
import com.example.tinywiny.service.ProductService;
import com.example.tinywiny.service.TypeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductsRestController {
  private final ProductService productService;
  private final ProductConverter converter;
  private final ImageService imageService;
  private final TypeProductService typeProductService;
  private final TypeProductConverter typeProductConverter;


  @GetMapping("/products/{productId}")
  public ProductDto getProduct(@PathVariable Long productId) throws URISyntaxException {
    Product product = productService.findProductByProductId(productId);
    URI imageUrl = null;
    Image image = imageService.findImageByProductId(productId);
    if (image != null) {
      imageUrl = imageService.getImagePath(image.getImageName()); //как ее передать на UI?
    }
    return converter.toProductDto(product);
  }

  @PostMapping(value = "/admin/product/create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
      MediaType.APPLICATION_OCTET_STREAM_VALUE},
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity uploadNewProduct(@RequestPart("productName") final String productName,
                                         @RequestPart("price") final int price,
                                         @RequestPart("countInStock") final int countInStock,
                                         @RequestPart("description") final String description,
                                         @RequestPart("idType") final int idType,
                                         @RequestPart("file") final MultipartFile file) throws IOException {
    Product product = new Product();
    TypeProduct typeProduct = typeProductService.getType(idType);
    product.setProductName(productName);
    product.setPrice(price);
    product.setCountInStock(countInStock);
    product.setDescription(description);
    product.setTypeProduct(typeProduct);
    productService.save(product);
    imageService.addImage(productName, file);
    return ResponseEntity.ok(new InputStreamResource(file.getInputStream()));
  }



  @PutMapping("/admin/products/{productId}")
  public void updateProduct(@PathVariable Long productId, @RequestBody ProductDto productDto) {
    productService.updateProduct(productDto, productId);
  }

  @PutMapping("/products/update/count-in-stock")
  public void updateCountInStock(@RequestBody ProductDto product) {
    productService.updateCountInStock(product.getCountInStock(), product.getProductId());
  }

  @GetMapping("/products/type/{typeName}")
  public List<ProductDto> findAllProductsByTypeAndPage(@PathVariable String typeName,
                                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.findAllProductsByTypeAndPage(typeName, pageNumber - 1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }

  @GetMapping("/admin/products")
  public List<ProductDto> findAllProducts() {
    List<Product> products = productService.findAllProducts();
    return converter.toProductDto(products);
  }

  @GetMapping("/products/types")
  public List<TypeProductDto> findAllTypes(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                           @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<TypeProduct> page = typeProductService.findAllType(pageNumber - 1, pageSize);
    List<TypeProduct> types = page.getContent();
    return typeProductConverter.toDto(types);
  }

  @DeleteMapping("/admin/products/{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }

}
//GET image????
  /*@PostMapping("/products")
  protected ProductDto createProduct(@RequestBody ProductDto product) {
    return converter.toProductDto(productService.save(product));
  }*/
