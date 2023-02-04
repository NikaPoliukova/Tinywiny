package com.example.tinywiny.service;

import com.example.tinywiny.converter.ProductConverter;
import com.example.tinywiny.converter.TypeProductConverter;
import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.dto.TypeProductDto;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductConverter converter;
  private final TypeProductService typeProductService;
  private final TypeProductConverter typeProductConverter;

  @Transactional
  @Modifying
  public Product save(ProductDto productDto) {
    if (productRepository.findProductByProductName(productDto.getProductName()).isPresent()) {
      throw new RuntimeException("User already exists");
    }
    TypeProduct typeProduct = typeProductService.getType(productDto.getIdType());
    TypeProductDto typeProductDto = typeProductConverter.toDto(typeProduct);
    Product product = converter.toProduct(productDto, typeProductDto);

    return productRepository.save(product);
  }

  @Transactional
  @Modifying
  public void updateProduct(ProductDto productDto) {
    //productRepository.save(converter.toProduct(productDto));
  }

  @Transactional
  @Modifying
  public void updateCountInStock(int count, Long productId) {
    Optional<Product> product = productRepository.findProductByProductId(productId);
    if (product.isPresent() && count >= 0) {
      product.get().setCountInStock(count);
      productRepository.save(product.get());
    }
  }

  public Page<Product> findAllProductsByTypeAndPage(TypeProductDto type, int pageNumber, int pageSize) {
    TypeProduct typeProduct = typeProductService.getType(type.getId());
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return productRepository.findAllByTypeProduct(typeProduct, page);
  }

  public Product findProductByProductId(Long productId) {
    Optional<Product> product = productRepository.findProductByProductId(productId);
    if (product.isEmpty()) {
      throw new RuntimeException("no such product");
    }
    return product.get();
  }

  public void deleteProduct(Long productId) {
    productRepository.deleteProductByProductId(productId);
  }
}