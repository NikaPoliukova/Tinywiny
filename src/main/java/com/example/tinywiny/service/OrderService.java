package com.example.tinywiny.service;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  public Order save(Order order) {
    return orderRepository.save(order);
  }

  public Order findOrderByOrderId(Long orderId) {
    return orderRepository.findOrderByOrderId(orderId);
  }
  public String getStatusByOrderId(Long orderId) {
    return orderRepository.getStatusByOrderId(orderId);
  }


  public Page<Order> findAll(Pageable page) {
    return orderRepository.findAll(page);
  }

  public void updateOrderStatus(long orderNumber, String status) {
    orderRepository.updateOrderStatus(orderNumber, status);
  }

  public Page<Order> filterOrderByStatus(String status, Pageable page) {
    if (status != null) {
      return orderRepository.findAllOrdersByStatusOrder(status, page);
    } else {
      return orderRepository.findAllBy(page);
    }
  }


}
