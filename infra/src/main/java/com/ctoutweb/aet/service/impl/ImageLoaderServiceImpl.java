package com.ctoutweb.aet.service.impl;

import com.ctoutweb.aet.factory.Factory;
import com.ctoutweb.aet.model.IImageData;
import com.ctoutweb.aet.model.ImageFace;
import com.ctoutweb.aet.service.IImageLoaderService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Implementation pour les images se trouvant dans le classpath
 */
@Service
public class ImageLoaderServiceImpl implements IImageLoaderService {
  private final ResourcePatternResolver resourceResolver;
  private final Factory factory;
  public ImageLoaderServiceImpl(ResourcePatternResolver resourceResolver, Factory factory) {
    this.resourceResolver = resourceResolver;
    this.factory = factory;
  }

  @Override
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException {
    Resource[] resources = imageFace == ImageFace.FRONT_FACE ?
            resourceResolver.getResources("classpath:image/memorygame/frontImage/*")
            : resourceResolver.getResources("classpath:image/memorygame/backImage/*");

    return Arrays.stream(resources)
        .filter(Objects::nonNull)
        .map(resource -> {
          String filename = resource.getFilename();
          String path = "image/memorygame/" + filename;
          return factory.getImageDataImpl(path, filename);
        }).toList();
  }
}
