package com.example.tinywiny.service;

import com.example.tinywiny.model.Bucket;
import com.example.tinywiny.repository.BucketRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class BucketService {

  private final BucketRepository bucketRepository;

  public Bucket createBucket(Long userId) {
    return bucketRepository.createBucket(userId);
  }
}
//ПИШЕТ ЧТО ЭТО АПДЕЙТ,А НЕ СОЗДАНИ НОВОГО ПОЛЯ