package com.example.tinywiny.service;

import com.example.tinywiny.dto.ReviewDto;
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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class ReviewService {

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;

  @Modifying
  public Review save(ReviewDto review) {
        if (review.getTextReview().isEmpty()) {
      throw new RuntimeException("You didn't write anything");
    }
    //review.setUserId(userRepository.findUserByUserId(userId).get());
    //return reviewRepository.createReview(text, userId);
    return save(review);
  }

  public List<Review> findAll() {
    return reviewRepository.findAll();
  }

  @Modifying
  public void deleteReviewById(Long reviewId) {
    reviewRepository.deleteReviewById(reviewId);
  }

  public Page<Review> getReviewsByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return reviewRepository.findAllBy(page);
  }
}
