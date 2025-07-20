package com.ctoutweb.aet.infra.controller;

import com.ctoutweb.aet.infra.service.imageLoaderService.IImageLoaderService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("${api.version.path}/images")
public class ImageController {
  private final IImageLoaderService imageLoaderService;

  public ImageController(IImageLoaderService imageLoaderService) {
    this.imageLoaderService = imageLoaderService;
  }

  @GetMapping("/{imageName}")
  public ResponseEntity<InputStreamResource> displayImage(@PathVariable String imageName) throws IOException {
   var imageData =  imageLoaderService.streamOneImage(imageName);

   if(imageData == null)
     return ResponseEntity.notFound().build();

   return ResponseEntity.ok()
           .contentLength(imageData.getImageSize())
           .contentType(imageData.getMediaType())
           .body(imageData.getResourceImage());
  }
}
