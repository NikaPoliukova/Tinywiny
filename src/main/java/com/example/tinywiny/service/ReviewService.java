package com.example.tinywiny.service;

import com.example.tinywiny.converter.ReviewConverter;
import com.example.tinywiny.converter.UserConverter;
import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.dto.UserDto;
import com.example.tinywiny.model.Review;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.ReviewRepository;
import com.example.tinywiny.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final ReviewConverter converter;
  private final UserRepository userRepository;
  private final UserConverter userConverter;

  @Transactional
  @Modifying
  public Review save(ReviewDto review) {
    if (review.getTextReview().isBlank()) {
      throw new RuntimeException("You didn't write anything");
    }
    Optional<User> user = userRepository.findUserByUserId(review.getUserId());
    UserDto userDto = userConverter.toDto(user.get());
    return reviewRepository.save(converter.toReview(review, userDto));
  }

  public Page<Review> findReviewsByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return reviewRepository.findAllBy(page);
  }

  public void deleteReviewById(Long reviewId) {
    reviewRepository.deleteReviewById(reviewId);
  }
}
