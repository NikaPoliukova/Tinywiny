package com.example.tinywiny.repository;

import com.example.tinywiny.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

  Discount findById(@Param("id") int id);
}
