package com.example.tinywiny.repository;

import com.example.tinywiny.dto.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TypeProductRepository extends JpaRepository<TypeProduct, String> {

  TypeProduct findByName(@Param("typeName") String typeName);

  TypeProduct findById(@Param("id") int id);
}
