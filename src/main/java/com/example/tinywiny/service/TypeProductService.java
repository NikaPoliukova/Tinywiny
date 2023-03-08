package com.example.tinywiny.service;

import com.example.tinywiny.dto.TypeProduct;
import com.example.tinywiny.repository.TypeProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class TypeProductService {

  private final TypeProductRepository repository;

  public TypeProduct getType(String typeName) {
    return repository.findByName(typeName);
  }

  public TypeProduct getType(int typeId) {
    return repository.findById(typeId);
  }

  public Page<TypeProduct> findAllType(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return repository.findAll(page);
  }
}
