package com.example.tinywiny.model;


import com.example.tinywiny.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "discount")
public class Discount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name ="discount_id")
  private int discountId;

  private int size;

  @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
  private List<Order> orders = new ArrayList<>();

}
