package com.example.tinywiny.controller;

import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.model.Review;
import com.example.tinywiny.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/reviews")
@RestController
@RequiredArgsConstructor
public class ReviewRestController {

  private final ReviewService reviewService;

  @PostMapping("/create")
  protected Review createReview(@RequestBody ReviewDto review){
    return reviewService.save(review);
  }

  @GetMapping
  protected Page<Review> findAlReviews(Pageable page) {
    return reviewService.findReviewsByPage(page);
   }

    @DeleteMapping
  protected void deleteReview(Long id){
    reviewService.deleteReviewById(id);
  }
}
