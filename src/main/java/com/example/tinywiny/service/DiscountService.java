package com.example.tinywiny.service;

import com.example.tinywiny.model.Discount;
import com.example.tinywiny.repository.DiscountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class DiscountService {
  private final DiscountRepository discountRepository;

  public Discount findDiscount(int sum) {
    if (sum >= 50 && sum < 100) {
      return discountRepository.findById(1);
    }
    if (sum >= 100 && sum < 150) {
      return discountRepository.findById(2);
    }
    if (sum >= 150 && sum < 200) {
      return discountRepository.findById(3);
    } else
      return discountRepository.findById(4);
  }
}
