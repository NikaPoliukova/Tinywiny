package com.example.tinywiny.controller;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
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


@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
  private final ProductService productService;

  @GetMapping("/{productId}")
  public Product showProduct(@PathVariable Long productId) {
    return productService.findProductByProductId(productId);
  }

  @PostMapping("/add")
  public void createProduct(@RequestBody ProductDto product) {
    productService.save(product);
  }

  @PostMapping("/update/{productId}")
  public void updateProduct(@RequestParam ProductDto productDto, @PathVariable Long productId) {
    productService.updateProduct(productDto);
  }

  @GetMapping("/type")
  public Page<Product> findAllProductsByTypeAndPage(@RequestBody TypeProduct type, Pageable pageable) {
        return productService.findAllByType(type,pageable);
  }

  //++
  @DeleteMapping("/delete")
  public void deleteProduct(Long productId) {
    productService.deleteProduct(productId);
  }

}


