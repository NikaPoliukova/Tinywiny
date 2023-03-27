package com.example.tinywiny.service;

import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.repository.ProductInOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductInOrderService {
  private final ProductInOrderRepository productInOrderRepository;
  private final OrderService orderService;

  public List<ProductInOrder> findAllProductsByOrder(Long orderId) {
    Order order = orderService.findOrderByOrderId(orderId);
    return productInOrderRepository.findAllByOrder(order);
  }
}
