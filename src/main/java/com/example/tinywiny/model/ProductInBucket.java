package com.example.tinywiny.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@DynamicInsert
@Data
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products_in_bucket")
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