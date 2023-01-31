package com.example.tinywiny.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@DynamicInsert
@Data
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "username")
  private String userName;

  private String password;

  @Column(name = "created_at")
  private Date createdDate;

  private String email;

  @Column(name = "phone_number")
  private String phoneNumber;

  private String role;

  @OneToMany(mappedBy = "user")
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<DeliveryInformation> deliveryInformation = new ArrayList<>();

  @OneToOne(mappedBy = "user")
  private Bucket bucket;
}
