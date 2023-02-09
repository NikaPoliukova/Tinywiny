package com.example.tinywiny.dto;

import com.example.tinywiny.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  Long productId;
  String productName;
  int price;
  String description;
  int countInStock;
  int idType;
  ImageDto imageDto;


 }
