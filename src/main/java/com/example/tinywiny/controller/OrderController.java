package com.example.tinywiny.controller;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {
  private final OrderService orderService;
  //КОГДА ДЕЛАЕТСЯ ЗАКАЗ ЧИСЛО НА СКЛАДЕ ДОЛЖНО УМЕНЬШАТЬСЯ

  @GetMapping
  public Page<Order> findAll(Pageable page) {
    return orderService.findAll(page);
  }

  @PostMapping("/update-status")
  public void updateOrderStatus(long orderNumber, String status) {
    orderService.updateOrderStatus(orderNumber, status);
  }

  @PostMapping
  public void addOrder(Order order) {
    orderService.save(order);
  }

  @GetMapping("/{orderId}")
  public Order findOrderByOrderId(@PathVariable Long orderId) {
    return orderService.findOrderByOrderId(orderId);
  }

  @GetMapping("/status")
  public String findStatusOrder( Long orderId) {
    return orderService.getStatusByOrderId(orderId);
  }
}
