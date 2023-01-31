package com.example.tinywiny.dto;

import com.example.tinywiny.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
  Long id;
  String textReview;
  Date createdDate;
  Long user_id;
}
