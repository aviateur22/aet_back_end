package com.ctoutweb.aet.infra.service.imageLoaderService;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.image.IStreamImage;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import com.ctoutweb.aet.infra.provider.InfraFactory;
import com.ctoutweb.aet.infra.provider.memoryCardGame.IMemoryCardInstanceProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.ctoutweb.aet.infra.provider.InfraFactory.IMAGE_INSTANCE_PROVIDER;

/**
 * IImageLoaderService
 * Implementation pour les images se trouvant dans le classpath / Resources
 */
@Service
public class ResourceImageLoaderServiceImpl implements IImageLoaderService {
  private final ResourcePatternResolver resourceResolver;
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
          return IMAGE_INSTANCE_PROVIDER.providerImageData(path, filename);
        }).toList();
  }

  @Override
  public IStreamImage streamOneImage(String imagePath) {
    return null;
  }
}
