package com.example.tinywiny.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@DynamicInsert
@Data
@Jacksonized
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

  /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Order> orders = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<DeliveryInformation> deliveryInformation = new ArrayList<>();*/

 /* @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Bucket bucket;*/
}
