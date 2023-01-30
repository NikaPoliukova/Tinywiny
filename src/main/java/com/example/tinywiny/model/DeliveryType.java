package com.example.tinywiny.dto;

import com.example.tinywiny.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delivery_type")
public class DeliveryType {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idType;

  @Column(name = "post_name")
  private String postName;

  @OneToMany(mappedBy = "deliveryType", cascade = CascadeType.ALL)
  private List<PostOffice> postOffices = new ArrayList<>();

  @OneToMany(mappedBy = "deliveryType", cascade = CascadeType.ALL)
  private List<Order> orders = new ArrayList<>();
}
