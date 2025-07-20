package com.ctoutweb.aet.infra.provider.image;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.image.IStreamImage;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

public interface ImageInstanceProvider {
  IImageData providerImageData(String imagePath, String imageName);
  IStreamImage provideStreamImage(long imageSize, MediaType mediaType, InputStreamResource resourceImage);


}
