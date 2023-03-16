package com.example.tinywiny.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long orderId;

  private int sum;

  @Column(name = "comment_order")
  private String commentOrder;

  @Column(name = "order_date")
  private Date createdAt;

  @Column(name = "status_order")
  private String statusOrder;

  @Column(name = "payment_status")
  private String paymentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_type")
  private DeliveryType deliveryType;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "delivery_information_id", referencedColumnName = "delivery_information_id")
  private DeliveryInformation deliveryInformation;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<ProductInOrder> productsInOrder = new ArrayList<>();
}
