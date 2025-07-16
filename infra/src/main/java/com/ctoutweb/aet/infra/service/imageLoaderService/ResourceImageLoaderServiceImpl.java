package com.ctoutweb.aet.infra.service.imageLoaderService;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import com.ctoutweb.aet.infra.provider.InfraFactory;
import com.ctoutweb.aet.infra.provider.memoryCardGame.IMemoryCardInstanceProvider;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * IImageLoaderService
 * Implementation pour les images se trouvant dans le classpath / Resources
 */
@Service
public class ResourceImageLoaderServiceImpl implements IImageLoaderService {
  private final ResourcePatternResolver resourceResolver;
  private final IMemoryCardInstanceProvider memoryCardInstanceProvider = InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;
  public ResourceImageLoaderServiceImpl(ResourcePatternResolver resourceResolver) {
    this.resourceResolver = resourceResolver;
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
          return memoryCardInstanceProvider.providerImageData(path, filename);
        }).toList();
  }
}
