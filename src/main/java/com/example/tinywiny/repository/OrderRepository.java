package com.example.tinywiny.repository;

import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Product> {

  String findOrderByOrderId(@Param("orderId") Long orderId);

/*  String getOrderStatus(Long orderNumber);

  Order getAllOrdersByUserId(Long userId);

  DeliveryInformation getInformation();
  //FOR ADMIN
  Page<Order> findAllBy(Pageable page);

  Page<Order> findAllOrdersByPageAndStatus(String status, Pageable page);
  @Modifying
  void updateOrderStatus(Long orderNumber, String status);*/




}
