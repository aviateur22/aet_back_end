package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.infra.model.IImageData;
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

import static com.ctoutweb.aet.infra.provider.InfraFactory.INFRA_MEMORY_CARD_INSTANCE_PROVIDER;
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
  private IImageData imageData1;
  @Mock
  private IImageData imageData2;
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
    when(resourcePatternResolver.getResources("classpath:image/memorygame/frontImage/*"))
            .thenReturn(new Resource[]{resource1, resource2});

    when(resource1.getFilename()).thenReturn("img1.png");
    when(resource2.getFilename()).thenReturn("img2.png");

    when(INFRA_MEMORY_CARD_INSTANCE_PROVIDER.providerImageData("image/memorygame/img1.png", "img1.png"))
            .thenReturn(imageData1);
    when(INFRA_MEMORY_CARD_INSTANCE_PROVIDER.providerImageData("image/memorygame/img2.png", "img2.png"))
            .thenReturn(imageData2);

    /**
     * when
     */
    List<IImageData> result = resourceImageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE);

    /**
     * then
     */
    assertEquals(2, result.size());
    assertTrue(result.contains(imageData1));
    assertTrue(result.contains(imageData2));
  }
}
