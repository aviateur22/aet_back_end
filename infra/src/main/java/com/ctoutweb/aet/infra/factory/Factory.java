package com.ctoutweb.aet.infra.factory;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageDataImpl;
import org.springframework.stereotype.Component;

@Component
public class Factory {
  public IImageData getImageDataImpl(String imagePath, String imageName) {
    return new ImageDataImpl(imagePath, imageName);
  }
}
