package com.example.tinywiny.service;

import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.model.User;
import com.example.tinywiny.repository.BucketRepository;
import com.example.tinywiny.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class BucketService {

  private final BucketRepository bucketRepository;
  private final UserRepository userRepository;
  @PersistenceContext
  private EntityManager entityManager;

  @Transactional
  @Modifying
  public Bucket createBucket(Long userId) {
    Optional<User> user = userRepository.findUserByUserId(userId);
    return bucketRepository.createBucket(user.get().getUserId());
   }
}
