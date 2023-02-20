package com.example.tinywiny.controller;

import com.example.tinywiny.converter.BucketConverter;
import com.example.tinywiny.converter.ProductInBucketConverter;
import com.example.tinywiny.dto.BucketDto;
import com.example.tinywiny.dto.ProductInBucketDto;
import com.example.tinywiny.service.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/bucket")
@RestController
@RequiredArgsConstructor
public class BucketRestController {

  private final BucketService bucketService;
  private final ProductInBucketConverter converter;
  private final BucketConverter bucketConverter;

  @GetMapping("/{bucketId}")
  public List<ProductInBucketDto> findAllProductsInBucket(@PathVariable Long bucketId) {
    return converter.toProductInBucketDto(bucketService.findAllProductInBucket(bucketId));
     }
   /* @GetMapping("/{bucketId}")
    public BucketDto findBucketByBucketId(@PathVariable Long bucketId){
    return bucketConverter.toBucket(bucketService.findBucketByBucketId(bucketId));
    }*/

  @PostMapping
  protected void addProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.addProductInBucket(productInBucketDto);
  }
  @PutMapping
  public ProductInBucketDto updateCountProduct(@RequestBody ProductInBucketDto productInBucketDto) {
    return converter.toProductInBucketDto(bucketService.updateCountProduct(productInBucketDto));
  }

  @DeleteMapping
  public void deleteProductInBucket(@RequestBody ProductInBucketDto productInBucketDto) {
    bucketService.deleteProductInBucket(productInBucketDto.getId());
  }
}
