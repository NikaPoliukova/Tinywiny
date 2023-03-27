package com.example.tinywiny.service;

import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.Product;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.ProductInOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductInOrderServiceTest {

  @Mock
  private ProductInOrderRepository productInOrderRepository;
  @Mock
  private OrderService orderService;
  @InjectMocks
  private ProductInOrderService productInOrderService;

  @Test
  void findAllProductsByOrder() {
    Order order = new Order(1L, 125, "dsxjbdc", new Date(), "5488415", "2566785121", new User(),
        new DeliveryType(), new DeliveryInformation(), List.of(prepareProductInOrder()));

    when(orderService.findOrderByOrderId(any(Long.class))).thenReturn(order);
    when(productInOrderRepository.findAllByOrder(any(Order.class))).thenReturn(List.of(prepareProductInOrder()));
    List<ProductInOrder> actual = productInOrderService.findAllProductsByOrder(1L);
    assertEquals(List.of(prepareProductInOrder()),actual);
  }

  public ProductInOrder prepareProductInOrder() {
    return new ProductInOrder(1L, 1, new Order(1L, 125, "dsxjbdc", new Date(), "5488415", "2566785121", new User(),
        new DeliveryType(), new DeliveryInformation(),new ArrayList<ProductInOrder>()), new Product());
  }
}