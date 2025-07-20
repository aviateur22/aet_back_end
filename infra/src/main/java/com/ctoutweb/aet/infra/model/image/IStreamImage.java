package com.ctoutweb.aet.infra.model.image;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

/**
 * Données renvoyé pour streamer une image
 */
public interface IStreamImage {
  long getImageSize();
  MediaType getMediaType();
  InputStreamResource getResourceImage();
}
