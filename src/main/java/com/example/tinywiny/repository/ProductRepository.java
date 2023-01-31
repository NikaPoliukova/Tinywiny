package com.example.tinywiny.repository;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

  Optional<Product> findProductByProductId(@Param("productId")Long productId);

  Page<Product> getAllByTypeProduct(TypeProduct type, Pageable page);

  Optional<Product> findProductByProductName(@Param("name") String name);

  @Modifying
  void deleteProductByProductId(@Param("productId")Long productId);



   /* Page<Product> findAllBy(Pageable page);
    }*/
}
