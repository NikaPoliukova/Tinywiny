package com.example.tinywiny.dto;

import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Data
@NoArgsConstructor
@Table(name = "bucket_has_order")
public class ProductInBucket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int count;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "bucket_id")
  private Bucket bucket;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

}