package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ProductInOrderConverter;
import com.example.tinywiny.dto.ProductInOrderDto;
import com.example.tinywiny.model.ProductInOrder;
import com.example.tinywiny.service.ProductInOrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class ProductInOrderRestController {
  private final ProductInOrderService productInOrderService;
  private final ProductInOrderConverter converter;
//ОТКУДА ВЗЯТЬ ОРДЕР АЙДИ
  @GetMapping("/products-for-order")
  public List<ProductInOrderDto> findProductInOrder(@RequestBody Long orderId) {
    List<ProductInOrder> products = productInOrderService.findAllProductByOrder(orderId);
    return converter.toProductInOrderDto(products);
  }
}
