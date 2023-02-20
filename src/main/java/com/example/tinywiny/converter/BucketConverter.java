package com.example.tinywiny.converter;

import com.example.tinywiny.dto.BucketDto;
import com.example.tinywiny.model.Bucket;
import org.mapstruct.Mapper;

@Mapper
public interface BucketConverter {

  BucketDto toBucket(Bucket bucket);
}
