package com.example.tinywiny.service;

import com.example.tinywiny.converter.DeliveryInformationConverter;
import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryInformation;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.ProductInBucket;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.OrderRepository;
import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final OrderConverter orderConverter;
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final DeliveryInformationConverter deliveryInformationConverter;
  private final BucketRepository bucketRepository;
  private final UtilClass utilClass;
  @Transactional
  @Modifying
  public Order save(OrderDto orderDto) {
    Long userId = utilClass.getIdCurrentUser();
    Order order = orderConverter.toOrder(orderDto);
    User user = userRepository.findUserByUserId(userId).get();
    UserDto userDto = userConverter.toDto(user);
    DeliveryInformation deliveryInformation = deliveryInformationConverter
        .toDeliveryInformation(orderDto.getDeliveryInformationDto(), userDto);
    order.setDeliveryInformation(deliveryInformation);
    List<ProductInBucket> productsInBucket =
        bucketRepository.findBucketByUserUserId(user.getUserId()).get().getProductsInBucket();
    List<ProductInOrder> productsInOrder = addProductsInOrder(productsInBucket);
    order.setProductsInOrder(productsInOrder);
    return orderRepository.save(order);
  }

  private List<ProductInOrder> addProductsInOrder(List<ProductInBucket> productInBucket) {
    List<ProductInOrder> productsInOrder = new ArrayList<>();
    for (ProductInBucket products : productInBucket) {
      ProductInOrder product = new ProductInOrder();
      product.setCount(products.getCount());
      product.setProduct(product.getProduct());
      productsInOrder.add(product);
    }
    return productsInOrder;
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

  public void updateOrderStatus(String status, Long orderId) {
    Order order = findOrderByOrderId(orderId);
    if (order != null && !status.equals(order.getStatusOrder())) {
      order.setStatusOrder(status);
      orderRepository.save(order);
    }
  }

  public Page<Order> findOrdersByStatus(String status, int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return orderRepository.findAllByStatusOrder(status, page);
  }
}
