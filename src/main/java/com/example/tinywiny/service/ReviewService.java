package com.example.tinywiny.service;

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
  private final UserRepository userRepository;
  private final UtilClass utilClass;

  @Transactional
  @Modifying
  public Review save(String text) {
    if (text.isBlank()) {
      throw new RuntimeException("You didn't write anything");
    }
    Optional<User> user = userRepository.findUserByUserId(utilClass.getIdCurrentUser());
    Review review = new Review();
    review.setUser(user.get());
    review.setTextReview(text);
    return reviewRepository.save(review);
  }

  public Page<Review> findReviewsByPage(int pageNumber, int pageSize) {
    Pageable page = PageRequest.of(pageNumber, pageSize);
    return reviewRepository.findAllBy(page);
  }

  public void deleteReviewById(Long reviewId) {
    reviewRepository.deleteReviewById(reviewId);
  }
}
