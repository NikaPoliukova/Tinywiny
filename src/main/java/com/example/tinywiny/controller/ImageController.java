package com.example.tinywiny.controller;


import com.example.tinywiny.model.Image;
import com.example.tinywiny.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ImageController {
  private final ImageService imageService;

  @GetMapping("/file")
  public ResponseEntity download() {
    final InputStream in = getClass().getResourceAsStream("/download.png");
    return ResponseEntity.ok(new InputStreamResource(in));
  }
  @GetMapping("/file/{productId}")
  public URI getProductImage(@PathVariable Long productId) throws URISyntaxException {
     Image image = imageService.findImageByProductId(productId);
    if (image != null) {
     return  imageService.getImagePath(image.getImageName());
    }return null;
  }

  @PostMapping(value = "/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
      MediaType.APPLICATION_OCTET_STREAM_VALUE},
      produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public void uploadFile(@RequestPart("file") final MultipartFile file,
                         @RequestPart("productId") final String productId) throws IOException {
    imageService.addProductImage(Long.parseLong(productId), file);
  }

  @DeleteMapping("/admin/image/{productId}")
  public void deleteImage(@PathVariable Long productId) {
    imageService.deleteImage(productId);
  }
}


