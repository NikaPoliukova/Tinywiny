package com.example.tinywiny.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@Table(name = "image")
public class Image {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "image_id")
  private Long imageId;

  @Column(name = "image_name")
  private String imageName;

  @OneToOne(mappedBy = "image")
  private Product products;
}

