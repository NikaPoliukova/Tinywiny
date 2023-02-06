package com.example.tinywiny.controller;

import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  //work
  @GetMapping("status")
  public List<OrderDto> findAllOrdersByStatus(@RequestBody OrderDto orderDto,
                                              @RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                              @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {

    Page<Order> page = orderService.findOrdersByStatus(orderDto.getStatus(), pageNumber - 1, pageSize);
    List<Order> orders = page.getContent();
    return orderConverter.toOrderDto(orders);
  }

  //WORK
  @GetMapping
  public List<OrderDto> findAllOrdersByPage(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Order> page = orderService.getOrdersByPage(pageNumber - 1, pageSize);
    List<Order> orders = page.getContent();
    return orderConverter.toOrderDto(orders);
  }

  //WORK
  @GetMapping("/{orderId}")
  public OrderDto findOrderByOrderId(@PathVariable Long orderId) {
    return orderConverter.toOrderDto(orderService.findOrderByOrderId(orderId));
  }

  //WORK
  @GetMapping("/orders-by/{userId}")
  public List<OrderDto> findOrdersByUserId(@PathVariable Long userId) {
    return orderConverter.toOrderDto(orderService.findOrdersByUserId(userId));
  }

  //WORK
  @PutMapping("/status/update")
  public void updateOrderStatus(@RequestBody OrderDto orderDto) {
    orderService.updateOrderStatus(orderDto.getOrderId(), orderDto.getStatus());
  }

  //WORK
  @GetMapping("/status/{orderId}")
  public String findStatusOrder(@PathVariable Long orderId) {
    return orderService.getStatusByOrderId(orderId);
  }
}
