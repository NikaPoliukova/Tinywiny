package com.example.tinywiny.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "delivery_information")
public class DeliveryInformation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_information_id")
  private Long deliveryInformationId;

  @Column(name = "customer_name")
  private String customerName;

  @Column(name = "customer_last_name")
  private String customerLastName;

  @Column(name = "customer_surname")
  private String customerSurname;

  @Column(name = "address_delivery")
  private String addressDelivery;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "deliveryInformation", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private List<Order> orders = new ArrayList<>();

}
