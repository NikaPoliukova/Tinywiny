package com.example.tinywiny.dto;


import com.example.tinywiny.model.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "type_product")
public class TypeProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_type")
  private int id;

  @Column(name = "type_name")
  private String name;

  @OneToMany(mappedBy = "typeProduct")
  private List<Product> products = new ArrayList<>();
}
