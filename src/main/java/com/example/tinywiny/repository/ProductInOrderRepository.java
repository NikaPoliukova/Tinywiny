package com.example.tinywiny.repository;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {

  List<ProductInOrder> findAllByOrder(@Param("order") Order order);
}
