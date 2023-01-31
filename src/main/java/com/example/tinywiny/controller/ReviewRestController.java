package com.example.tinywiny.controller;

import com.example.tinywiny.dto.ReviewDto;
import com.example.tinywiny.model.Review;
import com.example.tinywiny.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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
  protected List<Review> findAlReviews(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize) {
    Page<Review> page = reviewService.getReviewsByPage(pageNumber - 1, pageSize);
    List<Review> reviews = page.getContent();
    return reviews;
  }
  //+
    @DeleteMapping
  protected void deleteReview(Long id){
    reviewService.deleteReviewById(id);
  }

}
