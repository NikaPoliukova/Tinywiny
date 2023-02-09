package com.example.tinywiny.controller;

import com.example.tinywiny.converter.ReviewConverter;
import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.model.Review;
import com.example.tinywiny.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  private final ReviewConverter converter;


  @PostMapping
  protected ReviewDto createReview(@RequestBody ReviewDto review) {
    return converter.toReviewDto(reviewService.save(review));
  }

  @GetMapping
  protected List<ReviewDto> findAllReviews(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                          @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Review> page = reviewService.findReviewsByPage(pageNumber - 1, pageSize);
    List<Review> reviews = page.getContent();
    return converter.toReviewDto(reviews);
  }

  @DeleteMapping("/{id}")
  protected void deleteReview(@PathVariable Long id) {
    reviewService.deleteReviewById(id);
  }
}
