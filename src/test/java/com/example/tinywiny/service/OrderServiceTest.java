package com.example.tinywiny.service;

import com.example.tinywiny.converter.DeliveryInformationConverter;
import com.example.tinywiny.converter.OrderConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.DeliveryInformationDto;
import com.example.tinywiny.dto.OrderDto;
import com.example.tinywiny.dto.StatusOrder;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.DeliveryType;
import com.example.tinywiny.model.Order;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.OrderRepository;
import com.example.tinywiny.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.tinywiny.service.BucketServiceTest.prepareBucket;
import static com.example.tinywiny.service.DeliveryInformationServiceTest.prepareDeliveryInformation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

  @Mock
  private OrderRepository orderRepository;
  @Mock
  private OrderConverter orderConverter;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserConverter userConverter;
  @Mock
  private DeliveryInformationConverter deliveryInformationConverter;
  @Mock
  private BucketRepository bucketRepository;
  @Mock
  private UtilClass utilClass;
  @InjectMocks
  OrderService orderService;

  @Test
  void save() {
    when(deliveryInformationConverter.toDeliveryInformation(any(DeliveryInformationDto.class), any(UserDto.class))).thenReturn(prepareDeliveryInformation()); // make it public staic in delivInformServTest
    when(utilClass.getIdCurrentUser()).thenReturn(1L);
    when(orderConverter.toOrder(any(OrderDto.class))).thenReturn(new Order());
    when(userRepository.findUserByUserId(any(Long.class))).thenReturn(Optional.of(prepareUser()));
    when(userConverter.toDto(any(User.class))).thenReturn(new UserDto());
    when(bucketRepository.findBucketByUserUserId(any(Long.class))).thenReturn(Optional.of(prepareBucket()));// make prepareBucket public static in BucketServiceTest
    when(orderRepository.save(any(Order.class))).thenReturn(new Order());
    Order actual = orderService.save(prepareOrderDto());
    assertEquals(new Order(), actual);
  }

  @Test
  void getOrdersByPage() {
    when(orderRepository.findAllBy(any(Pageable.class))).thenReturn(Page.empty());
    Page<Order> actual = orderService.getOrdersByPage(2, 5);
    assertEquals(Page.empty(), actual);
  }

  @Test
  void findOrderByOrderId() {
    when(orderRepository.findOrderByOrderId(any(Long.class))).thenReturn(Optional.of(prepareOrder()));
    Order actual = orderService.findOrderByOrderId(1L);
    assertEquals(prepareOrder(), actual);
  }

  @Test
  void findOrdersByUserId() {
    when(userRepository.findUserByUserId(any(Long.class))).thenReturn(Optional.of(prepareUser()));
    when(orderRepository.findOrdersByUserUserId(any(Long.class))).thenReturn(List.of(prepareOrder()));
    List<Order> actual = orderService.findOrdersByUserId(1L);
    assertEquals(List.of(prepareOrder()), actual);
  }

  @Test
  void getStatusByOrderId() {
    when(orderRepository.findOrderByOrderId(any(Long.class))).thenReturn(Optional.of(prepareOrder()));
    when(orderRepository.getOrderStatus(any(Long.class))).thenReturn("orderStatus");
    String actual = orderService.getStatusByOrderId(1L);
    assertEquals("orderStatus", actual);
  }

  @Test
  void updateOrderStatus() {
    when(orderRepository.findOrderByOrderId(any(Long.class))).thenReturn(Optional.of(prepareOrder()));
    when(orderRepository.save(any(Order.class))).thenReturn(prepareOrder());
    orderService.updateOrderStatus(new StatusOrder(1L, "status"));

    verify(orderRepository, times(2)).findOrderByOrderId(any(Long.class));
    verify(orderRepository).save(any(Order.class));
  }

  private DeliveryInformationDto prepareDeliveryInformationDto() {
    return new DeliveryInformationDto(1L, "customerName", "customerLastName", "customerSurname", "addressDelivery", 1L);
  }

  private OrderDto prepareOrderDto() {
    return new OrderDto(1L, "commentOrder", 1L, prepareDeliveryInformationDto(), 1, "statusOrder", null, "paymentStatus", 2, Collections.emptyList());
  }

  private User prepareUser() {
    return new User(1L, "username", "password", null, "email", "phoneNumber", "role");
  }

  private Order prepareOrder() {
    return new Order(1L, 100, "commentOrder", null, "statusOrder", "paymentStatus", prepareUser(), new DeliveryType(), prepareDeliveryInformation(), Collections.emptyList());
  }

}