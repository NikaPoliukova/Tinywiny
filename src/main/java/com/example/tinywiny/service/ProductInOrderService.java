package com.example.tinywiny.service;

import com.example.tinywiny.converter.ProductInBucketConverter;
import com.example.tinywiny.converter.ProductInOrderConverter;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.dto.ProductInOrderDto;
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
  private final ProductInBucketConverter converter;
  private final ProductInOrderConverter productInOrderConverter;

  public List<ProductInOrder> addProductsInOrder(List<ProductInBucketDto> productInBucket) {
    List<ProductInOrderDto> productsInOrderDto = converter.toProductInOrderDto(productInBucket);
    List<ProductInOrder> productsInOrder = productInOrderConverter.toProductInOrder(productsInOrderDto);
    for (ProductInOrder product : productsInOrder) {
      productInOrderRepository.save(product);
    }
    return productsInOrder;
  }
}
