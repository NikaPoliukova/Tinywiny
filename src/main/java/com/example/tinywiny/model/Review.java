package com.example.tinywiny.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Entity
@DynamicInsert
@Data
@Table(name = "review")
public class Review {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "text_review")
  private String textReview;

  @Column(name = "created_at")
  private Date createdDate;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
