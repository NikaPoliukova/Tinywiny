package com.example.tinywiny.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OrderSumDto {
  private int sum;
  private int sumWithDiscount;
}
