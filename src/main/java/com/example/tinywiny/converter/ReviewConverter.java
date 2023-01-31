package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.model.Review;
import org.mapstruct.Mapper;

@Mapper
public interface ReviewConverter {
  Review toReview(ReviewDto review);

}
