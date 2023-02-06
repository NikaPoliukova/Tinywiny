package com.example.tinywiny.service;

import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.model.Discount;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.DiscountRepository;
import com.example.tinywiny.repository.OrderRepository;
import com.example.tinywiny.repository.ProductRepository;
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
  private final UserService userService;
  private final UserConverter userConverter;
  private final DeliveryInformationService deliveryInformationService;
  private final DiscountService discountService;
  private final ProductService productService;


  @Transactional
  @Modifying
  public Order save(OrderDto orderDto) {
    UserDto userDto = userConverter.toDto(userService.findUserByUserId(orderDto.getUserId()));
    DeliveryType deliveryType = deliveryInformationService.findDeliveryType(orderDto.getDeliveryTypeId());
    List<ProductInOrderDto> productsInOrder = orderDto.getProductsInOrder();
    Discount discount = null;
    int sum = orderAmountCalculation(productsInOrder);
    if (sum > 0) {
      discount = discountService.findDiscount(sum);
    }
    Order order = orderConverter.toOrder(orderDto, userDto, orderDto.getDeliveryInformationDto(),
        deliveryType, productsInOrder, discount);
    return orderRepository.save(order);
  }

  private int orderAmountCalculation(List<ProductInOrderDto> productsInOrder) {
    int sum = 0;
    for (ProductInOrderDto product : productsInOrder) {
      int count = product.getCount();
      sum = count * (productService.findProductPrice(product.getProductId()));
    }
    return sum;
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
    }
  }

  public Page<Order> findOrdersByStatus(String status, int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return orderRepository.findAllByStatusOrder(status, page);

  }

}
