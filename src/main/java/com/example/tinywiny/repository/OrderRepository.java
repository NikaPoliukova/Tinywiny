package com.example.tinywiny.repository;

import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Product> {

  String findOrderByOrderId(@Param("orderId") Long orderId);

  String getOrderStatus(Long orderNumber);

  Order getAllOrdersByUserId(Long userId);

  DeliveryInformation getInformation();


  Page<Order> findAllBy(Pageable page);

  Page<Order> findAllOrdersByPageAndStatus(String status, Pageable page);

  @Modifying
  @Query(value ="update orders set status_order =:status where order_id =:orderNumber ", nativeQuery = true)
  void updateOrderStatus(@Param("orderNumber")Long orderNumber, @Param("status") String status);


}
