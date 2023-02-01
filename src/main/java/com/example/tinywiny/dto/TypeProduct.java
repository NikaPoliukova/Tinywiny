package com.example.tinywiny.dto;



import com.example.tinywiny.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
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
  private Long id;

  @Column(name = "type_name")
  private String name;

  @OneToMany(mappedBy = "typeProduct",cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();
}
