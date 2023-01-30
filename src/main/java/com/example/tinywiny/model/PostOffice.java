package com.example.tinywiny.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "post_offices")
public class PostOffice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_post_office")
  private Long id;

  @Column(name = "post_address")
  private String address;

  @ManyToOne
  @JoinColumn(name = "id_type")
  private DeliveryType deliveryType;
}
