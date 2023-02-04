package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.service.ProductService;
import com.example.tinywiny.service.TypeProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsRestController {
  private final ProductService productService;
  private final ProductConverter converter;
  private final TypeProductService typeProductService;

  // WORK incorrect (Не отображает тип айди =0)
  @GetMapping("/{productId}")
  public ProductDto showProduct(@PathVariable Long productId) {
    Product product = productService.findProductByProductId(productId);
    return converter.toProductDto(product);
  }

  //WORK
  @PostMapping
  protected ProductDto createProduct(@RequestBody ProductDto product) {
    Product savedProduct = productService.save(product);
        return converter.toProductDto(savedProduct);
  }

  @PostMapping("/update/{productId}")
  public void updateProduct(@RequestBody ProductDto productDto, @PathVariable Long productId) {
    productService.updateProduct(productDto);
  }
  @PostMapping("/update/count-in-stock")
  public void updateCountInStock(@RequestBody int countInStock,@RequestBody  Long productId) {
    productService.updateCountInStock(countInStock, productId);
  }

  // WORK incorrect (Не отображает тип айди)
 /* @GetMapping
  public List<ProductDto> findAllProductsByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.findAllProducts(pageNumber - 1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }*/

  //DON'T WORK(НЕТУ ТИПА)
  @GetMapping("/type")
  public List<ProductDto> findAllProductsByTypeAndPage(@RequestBody TypeProduct type,
                                                       @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Product> page = productService.getProductByPage(type, pageNumber - 1, pageSize);
    List<Product> products = page.getContent();
    return converter.toProductDto(products);
  }

  //WORK
  @DeleteMapping("/{productId}")
  public void deleteProduct(@PathVariable Long productId) {
    productService.deleteProduct(productId);
  }
}


