package com.example.tinywiny.service;

import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.OrderRepository;
import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;
  private final UserRepository userRepository;

  @Transactional
  @Modifying
  public Order save(OrderDto orderDto) {
    Order order = orderConverter.toOrder(orderDto);
    return orderRepository.save(order);
  }

  public Page<Order> getOrdersByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return orderRepository.findAllBy(page);
  }

  public Order findOrderByOrderId(Long orderId) {
    Optional<Order> order = orderRepository.findOrderByOrderId(orderId);
    if (order.isEmpty()) {
      throw new RuntimeException("such order does not exist");
    }
    return orderRepository.findOrderByOrderId(orderId).get();
  }

  public List<Order> findOrdersByUserId(Long userId) {
    if (userRepository.findUserByUserId(userId).isEmpty()) {
      throw new RuntimeException("such user does not exist");
    }
    return orderRepository.findOrdersByUserUserId(userId);
  }

  public String getStatusByOrderId(Long orderId) {
    Order order = findOrderByOrderId(orderId);
    if (order == null) {
      throw new RuntimeException("order does not exist");
    }
    return orderRepository.getOrderStatus(orderId);
  }

  public void updateOrderStatus(long orderNumber, String status) {
    Order order = findOrderByOrderId(orderNumber);
    if (order != null && !(status.equals(order.getStatusOrder()))) {
      order.setStatusOrder(status);
      orderRepository.save(order);
    }}
/*
  public Page<Order> filterOrderByStatus(String status, Pageable page) {
    if (status != null) {
      return orderRepository.findAllOrdersByStatusOrder(status, page);
    } else {
      return orderRepository.findAllBy(page);
    }

  public Page<Order> findAll(Pageable page) {
    return orderRepository.findAll(page);
  }
  }*/

}
