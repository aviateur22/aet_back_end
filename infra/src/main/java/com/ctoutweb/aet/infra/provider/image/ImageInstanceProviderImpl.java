package com.ctoutweb.aet.infra.provider.image;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.image.IStreamImage;
import com.ctoutweb.aet.infra.model.image.ImageDataImpl;
import com.ctoutweb.aet.infra.model.image.StreamImageImpl;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

public class ImageInstanceProviderImpl implements ImageInstanceProvider {
  @Override
  public IImageData providerImageData(String imagePath, String imageName) {
    return new ImageDataImpl(imagePath, imageName);
  }

  @Override
  public IStreamImage provideStreamImage(long imageSize, MediaType mediaType, InputStreamResource resourceImage) {
    return new StreamImageImpl(imageSize, mediaType, resourceImage);
  }
}
