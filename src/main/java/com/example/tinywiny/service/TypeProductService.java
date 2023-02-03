package com.example.tinywiny.service;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.repository.TypeProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class TypeProductService {

  private final TypeProductRepository repository;

  public TypeProduct getType(int typeId) {
    return repository.findById(typeId);
  }
}
