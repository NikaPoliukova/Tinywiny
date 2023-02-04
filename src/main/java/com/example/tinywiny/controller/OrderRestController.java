package com.example.tinywiny.controller;

import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.User;
import com.example.tinywiny.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderRestController {
  private final OrderService orderService;
  private final OrderConverter orderConverter;

  //КОГДА ДЕЛАЕТСЯ ЗАКАЗ ЧИСЛО НА СКЛАДЕ ДОЛЖНО УМЕНЬШАТЬСЯ

  @PostMapping
  public OrderDto createOrder(@RequestBody OrderDto order) {
    return orderConverter.toOrderDto(orderService.save(order));
  }

  @GetMapping
  public List<OrderDto> findAllOrdersByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                      @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Order> page = orderService.getOrdersByPage(pageNumber - 1, pageSize);
    List<Order> orders = page.getContent();
    return orderConverter.toOrderDto(orders);
  }

  @GetMapping("/order")
  public OrderDto findOrderByOrderId(@RequestBody Long orderId) {
    return orderConverter.toOrderDto(orderService.findOrderByOrderId(orderId));
  }

  @GetMapping("/orders-by/{userId}")
  public List<OrderDto> findOrdersByUserId(@PathVariable Long userId) {
    return orderConverter.toOrderDto(orderService.findOrdersByUserId(userId));
  }

  @PostMapping("/update-status")
  public void updateOrderStatus(long orderNumber, String status) {
    orderService.updateOrderStatus(orderNumber, status);
  }

  @GetMapping("/currently-status")
  public String findStatusOrder(Long orderId) {
    return orderService.getStatusByOrderId(orderId);
  }
}
