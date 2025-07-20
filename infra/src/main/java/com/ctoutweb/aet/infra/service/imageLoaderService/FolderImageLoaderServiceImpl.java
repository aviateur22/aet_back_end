package com.ctoutweb.aet.infra.service.imageLoaderService;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.image.IStreamImage;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import com.ctoutweb.aet.infra.repository.IImageRepository;
import com.ctoutweb.aet.infra.repository.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.ctoutweb.aet.infra.provider.InfraFactory.IMAGE_INSTANCE_PROVIDER;

/**
 * IImageLoaderService
 * Implementation pour récupération des image depuis une folder
 */
@Service
@Primary
public class FolderImageLoaderServiceImpl implements IImageLoaderService {

  @Value("${memory.card.front.path}")
  private String frontImagePath;
  @Value("${memory.card.back.path}")
  private String backImagePath;
  private final IImageRepository imageRepository;
  private final ResourcePatternResolver resourceResolver;

  public FolderImageLoaderServiceImpl(IImageRepository imageRepository, ResourcePatternResolver resourceResolver) {
    this.imageRepository = imageRepository;
    this.resourceResolver = resourceResolver;
  }

  @Override
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException {
    String baseDir = imageFace == ImageFace.FRONT_FACE ? frontImagePath : backImagePath;

    Resource[] resources = resourceResolver.getResources("file:" + baseDir + "*");

    return Arrays.stream(resources)
            .filter(Objects::nonNull)
            .map(resource -> {
              String filename = resource.getFilename();

              if (filename == null) return null;
              String path = baseDir + filename;
              return IMAGE_INSTANCE_PROVIDER.providerImageData(path, filename);
            })
            .toList();
  }

  @Override
  public IStreamImage streamOneImage(String imageName) throws IOException {
    ImageEntity image = this.imageRepository.findFirstByRandomName(imageName).orElse(null);

    if (image == null)
      return null;


    File imageFile = new File(image.getImagePath());

    if (!imageFile.exists() || imageFile.isDirectory()) {
      return null;
    }

    String contentType = Files.probeContentType(imageFile.toPath());
    long imageSize = imageFile.length();

    var imageStream=  new InputStreamResource(new FileInputStream(imageFile));
    return IMAGE_INSTANCE_PROVIDER.provideStreamImage(imageSize, MediaType.parseMediaType(contentType), imageStream);
  }
}
