package com.example.tinywiny.service;

import com.example.tinywiny.dto.ProductDto;
import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.repository.ProductInBucketRepository;
import com.example.tinywiny.repository.ProductInOrderRepository;
import com.example.tinywiny.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductService {
  private final ProductRepository productRepository;
  private final ProductInBucketRepository productInBucketRepository;
  private final ProductInOrderRepository productInOrderRepository;
  private final TypeProductService typeProductService;


  @Transactional
  @Modifying
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Transactional
  @Modifying
  public void updateProduct(ProductDto productDto, Long productId) {
    Optional<Product> product = productRepository.findProductByProductId(productId);
    if (product.isEmpty()) {
      throw new RuntimeException("no product");
    }
    prepareProductForUpdate(productDto, product.get());
    productRepository.save(product.get());

  }

  private Product prepareProductForUpdate(ProductDto productDto, Product product) {
    if (productDto.getPrice() != 0) {
      product.setPrice(productDto.getPrice());
    }
    if (productDto.getDescription() != null) {
      product.setDescription(productDto.getDescription());
    }
    if (productDto.getCountInStock() != 0) {
      product.setCountInStock(productDto.getCountInStock());
    }
    if (productDto.getIdType() != 0) {
      product.setTypeProduct(typeProductService.getType(productDto.getIdType()));
    }
    return product;
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

  public Page<Product> findAllProductsByTypeAndPage(String typeName, int pageNumber, int pageSize) {
    TypeProduct typeProduct = typeProductService.getType(typeName);
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

  public Product findProductByProductName(String productName) {
    Optional<Product> product = productRepository.findProductByProductName(productName);
    if (product.isEmpty()) {
      throw new RuntimeException("no such product");
    }
    return product.get();
  }

  public void deleteProduct(Long productId) {
    ProductInBucket productInBucket = productInBucketRepository.findProductInBucketById(productId);
    ProductInOrder productInOrder = productInOrderRepository.
        findProductInOrderByProduct(findProductByProductId(productId));
    if (productInBucket == null && productInOrder == null)
      productRepository.deleteProductByProductId(productId);
  }

  public List<Product> findAllProducts() {
    return productRepository.findAll();
  }
}