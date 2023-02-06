package com.example.tinywiny.repository;

import com.example.tinywiny.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {

}
