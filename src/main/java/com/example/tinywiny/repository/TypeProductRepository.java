package com.example.tinywiny.repository;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface TypeProductRepository extends JpaRepository<TypeProduct, String> {

  TypeProduct findById(@Param("typeId") int typeId);
}
