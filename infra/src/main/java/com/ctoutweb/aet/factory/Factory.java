package com.ctoutweb.aet.factory;

import com.ctoutweb.aet.model.IImageData;
import com.ctoutweb.aet.model.ImageDataImpl;
import org.springframework.stereotype.Component;

@Component
public class Factory {
  public IImageData getImageDataImpl(String imagePath, String imageName) {
    return new ImageDataImpl(imagePath, imageName);
  }
}
