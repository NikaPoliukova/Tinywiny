package com.example.tinywiny.repository;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

  Optional<Product> findProductByProductId(@Param("productId") Long productId);

  @Query(value = "select price from products where product_id=:productId", nativeQuery = true)
  int findProductPrice(@Param("productId") Long productId);

  Optional<Product> findProductByProductName(@Param("name") String name);

  @Transactional
  @Modifying
  void deleteProductByProductId(@Param("productId") Long productId);

  Page<Product> findAllByTypeProduct(TypeProduct type, Pageable page);

  Page<Product> findAllBy(Pageable page);

}
