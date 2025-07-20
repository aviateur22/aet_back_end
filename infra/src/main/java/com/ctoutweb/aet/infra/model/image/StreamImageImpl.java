package com.ctoutweb.aet.infra.model.image;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

public record StreamImageImpl(long imageSize, MediaType mediaType, InputStreamResource resourceImage) implements IStreamImage {
  @Override
  public long getImageSize() {
    return imageSize;
  }

  @Override
  public MediaType getMediaType() {
    return mediaType;
  }

  @Override
  public InputStreamResource getResourceImage() {
    return resourceImage;
  }
}
