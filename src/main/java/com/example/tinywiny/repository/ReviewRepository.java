package com.example.tinywiny.repository;

import com.example.tinywiny.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

   @Modifying
   void deleteReviewById(@Param("reviewId") Long reviewId);

   Page<Review> findAllBy(Pageable page);

   @Modifying
   @Query(value = "insert into review (text_review, user_id) values (:text, :userId)", nativeQuery = true)
   Review createReview(@Param("text") String text, @Param("userId") Long userId);
}
