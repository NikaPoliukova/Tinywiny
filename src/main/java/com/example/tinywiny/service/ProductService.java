package com.example.tinywiny.service;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductConverter converter;

  public Product save(ProductDto productDto) {
    if (productRepository.findProductByProductName(productDto.getProductName()).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    return productRepository.save(converter.toProduct(productDto));
  }

  public void updateProduct(ProductDto productDto) {
    productRepository.save(converter.toProduct(productDto));
  }

  public Product findProductByProductId(Long productId) {
    Optional<Product> product = productRepository.findProductByProductId(productId);
    if (product.isEmpty()) {
      throw new RuntimeException("no such product");
    }
    return product.get();
  }

  public Page<Product> findAllByType(TypeProduct type, Pageable page) {
    return productRepository.getAllByTypeProduct(type, page);
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteProductByProductId(productId);
  }
}


   /* public Page<Product> findAllProductsByPage ( int pageNumber, int pageSize){
      Pageable page = PageRequest.of(pageNumber, pageSize);
      return productRepository.findAllBy(page);

  public Page<Product> findAllByPage(Pageable page) {
    return productRepository.findAllBy(page);
  }
    }*/