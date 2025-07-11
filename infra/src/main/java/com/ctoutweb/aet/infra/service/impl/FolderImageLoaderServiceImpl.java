package com.ctoutweb.aet.infra.service.impl;

import com.ctoutweb.aet.infra.factory.Factory;
import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageFace;
import com.ctoutweb.aet.infra.service.IImageLoaderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Primary
public class FolderImageLoaderServiceImpl implements IImageLoaderService {

  @Value("${memory.card.front.path}")
  private String frontImagePath;
  @Value("${memory.card.back.path}")
  private String backImagePath;
  private final ResourcePatternResolver resourceResolver;
  private final Factory factory;

  public FolderImageLoaderServiceImpl(ResourcePatternResolver resourceResolver, Factory factory) {
    this.resourceResolver = resourceResolver;
    this.factory = factory;
  }

  @Override
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException {
    String baseDir = imageFace == ImageFace.FRONT_FACE ? frontImagePath : backImagePath;

    Resource[] resources = resourceResolver.getResources("file:" + baseDir + "*");

    return Arrays.stream(resources)
            .filter(Objects::nonNull)
            .map(resource -> {
              String filename = resource.getFilename();
              System.out.println(filename);
              if (filename == null) return null;
              String path = baseDir + filename;
              System.out.println(path);
              return factory.getImageDataImpl(path, filename);
            })
            .toList();
  }
}
