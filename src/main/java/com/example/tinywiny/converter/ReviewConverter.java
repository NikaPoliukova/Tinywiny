package com.example.tinywiny.converter;

import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ReviewConverter {
  @Mapping(target = "user", source = "userDto")
  Review toReview(ReviewDto review, UserDto userDto);

  List<ReviewDto> toReviewDto(List<Review> review);

 @Mapping(target = "userId", source = "review.user.userId")
  ReviewDto toReviewDto(Review review);
}
