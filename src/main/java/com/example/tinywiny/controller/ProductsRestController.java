package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.User;
import com.example.tinywiny.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsRestController {
  private final ProductService productService;
  private final ProductConverter converter;

 /* @GetMapping("/{productId}")
  public Product showProduct(@PathVariable Long productId) {
    return productService.findProductByProductId(productId);
  }*/

  @PostMapping("/add")
  public void createProduct(@RequestBody ProductDto product) {
    productService.save(product);
  }

  @PostMapping("/update/{productId}")
  public void updateProduct(@RequestParam ProductDto productDto, @PathVariable Long productId) {
    productService.updateProduct(productDto);
  }

  @GetMapping
  public List<ProductDto> findAllProductsByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.findAllProducts(pageNumber-1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }

  @GetMapping("/type")
  public List<ProductDto> findAllProductsByTypeAndPage(@RequestBody TypeProduct type,
                                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.getProductByPage(type, pageNumber - 1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }

  @DeleteMapping("/delete")
  public void deleteProduct(Long productId) {
    productService.deleteProduct(productId);
  }

}


