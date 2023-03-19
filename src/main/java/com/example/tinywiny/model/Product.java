package com.example.tinywiny.model;

import com.example.tinywiny.dto.TypeProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id")
  private Long productId;

  @Column(name = "product_name")
  private String productName;

  private int price;

  private String description;

  @Column(name = "count_in_stock")
  private int countInStock;

  @ManyToOne
  @JoinColumn(name = "id_type")
  private TypeProduct typeProduct;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProductInBucket> productInBuckets = new ArrayList<>();

  @OneToMany(mappedBy = "product")
  private List<ProductInOrder> productInOrders = new ArrayList<>();
}
