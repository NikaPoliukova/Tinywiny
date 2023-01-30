package com.example.tinywiny.service;

import com.example.tinywiny.model.Image;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;

  public Product save(Product product) {
    if (productRepository.findProductByProductId(product.getProductId()).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    return productRepository.save(product);
  }

  public Product findProductByProductId(Long productId) {
    Optional<Product> product = productRepository.findProductByProductId(productId);
    if (product.isEmpty()) {
      throw new RuntimeException("no such product");
    }
    return product.get();
  }


  public Page<Product> findAllProductsByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return productRepository.findAllBy(page);
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteProductByProductId(productId);
  }

}
