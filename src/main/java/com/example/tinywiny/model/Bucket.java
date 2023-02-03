package com.example.tinywiny.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bucket")
public class Bucket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "bucket_id")
  private Long bucketId;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "bucket", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private List<ProductInBucket> productInBuckets = new ArrayList<>();
}
