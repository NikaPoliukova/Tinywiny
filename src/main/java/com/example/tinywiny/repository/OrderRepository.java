package com.example.tinywiny.repository;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Product> {

  Optional<Order> findOrderByOrderId(@Param("orderId") Long orderId);

  List<Order> findOrdersByUserUserId(@Param("userId") Long userId);

  @Query(value = " select status_order from orders where order_id =:orderNumber ", nativeQuery = true)
  String getOrderStatus(@Param("orderNumber") Long orderNumber);

  Page<Order> findAllBy(Pageable page);

  Page<Order> findAllByStatusOrder(@Param("status")String status, Pageable page);


/*@Query(value = " select * from orders where user_id =:userId ", nativeQuery = true)
  Order findAllByUserId(@Param("userId") Long userId);
  /*@Transactional
  @Modifying
  @Query(value = "update orders set status_order =:status where order_id =:orderNumber ", nativeQuery = true)
  void updateOrderStatus(@Param("orderNumber") Long orderNumber, @Param("status") String status);
*/
}

