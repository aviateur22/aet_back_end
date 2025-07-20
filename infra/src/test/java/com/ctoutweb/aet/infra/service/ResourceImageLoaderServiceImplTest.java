package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import com.ctoutweb.aet.infra.service.imageLoaderService.ResourceImageLoaderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResourceImageLoaderServiceImplTest {
  @Mock
  private Resource resource1;
  @Mock
  private Resource resource2;
  @Mock
  private ResourcePatternResolver resourcePatternResolver;
  ResourceImageLoaderServiceImpl resourceImageLoaderService;


  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    resourcePatternResolver = mock(ResourcePatternResolver.class);
    resourceImageLoaderService = new ResourceImageLoaderServiceImpl(resourcePatternResolver);
  }

  @Test
  void loadAllMemoryCardImages_test_method() throws IOException {
    /**
     * given
     */
    String fileName1 = "img1.png";
    String fileName2 = "img2.png";
    when(resourcePatternResolver.getResources("classpath:image/memorygame/frontImage/*"))
            .thenReturn(new Resource[]{resource1, resource2});

    when(resource1.getFilename()).thenReturn(fileName1);
    when(resource2.getFilename()).thenReturn(fileName2);

    /**
     * when
     */
    List<IImageData> result = resourceImageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE);

    /**
     * then
     */
    assertEquals(2, result.size());
    assertEquals(fileName1,  result.stream()
            .filter(imageData -> fileName1.equalsIgnoreCase(imageData.getImageName()))
            .findFirst()
            .orElse(null)
            .getImageName()
    );
    assertEquals(fileName2,  result.stream()
            .filter(imageData -> fileName2.equalsIgnoreCase(imageData.getImageName()))
            .findFirst()
            .orElse(null)
            .getImageName()
    );
  }
}
