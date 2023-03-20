package com.example.tinywiny.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.tinywiny.model.Discount;
import com.example.tinywiny.repository.DiscountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {

  @Mock
  private DiscountRepository discountRepository;
  @InjectMocks
  private DiscountService discountService;

  @Test
  void findDiscount() {
    when(discountRepository.findDiscountByDiscountId(any(Integer.class))).thenReturn(new Discount(1, 20));
    Discount actual = discountService.findDiscount(100);
    assertEquals(new Discount(1, 20), actual);
  }
}