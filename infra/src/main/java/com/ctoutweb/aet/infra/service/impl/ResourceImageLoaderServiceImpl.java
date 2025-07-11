package com.ctoutweb.aet.infra.service.impl;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageFace;
import com.ctoutweb.aet.infra.service.IImageLoaderService;
import com.ctoutweb.aet.infra.factory.Factory;
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
public class ResourceImageLoaderServiceImpl implements IImageLoaderService {
  private final ResourcePatternResolver resourceResolver;
  private final Factory factory;
  public ResourceImageLoaderServiceImpl(ResourcePatternResolver resourceResolver, Factory factory) {
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
