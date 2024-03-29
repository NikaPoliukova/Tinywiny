package com.example.tinywiny.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "discount")
public class Discount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "discount_id")
  private int discountId;

  private int size;

 /* @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
  private List<ProductInBucket> productInBuckets = new ArrayList<>();*/

}
