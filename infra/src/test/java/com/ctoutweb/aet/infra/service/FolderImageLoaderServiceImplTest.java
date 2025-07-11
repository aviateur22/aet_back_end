package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageFace;
import com.ctoutweb.aet.infra.service.impl.FolderImageLoaderServiceImpl;
import com.ctoutweb.aet.infra.factory.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FolderImageLoaderServiceImplTest {
  @Mock
  private Resource resource1;
  @Mock
  private Resource resource2;
  @Mock
  private IImageData imageData1;
  @Mock
  private IImageData imageData2;
  @MockBean
  private ResourcePatternResolver resourcePatternResolver;
  private Factory factory = new Factory();
  @Autowired
  private FolderImageLoaderServiceImpl folderImageLoaderService;
  @DynamicPropertySource
  static void registerProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", () -> "jdbc:postgresql://localhost:5433/aet");
    registry.add("spring.datasource.username", () -> "aet");
    registry.add("spring.datasource.password", () -> "aet");
    registry.add("memory.card.front.path", () -> "/C:/Programmation/test/images/memoryCardGame/frontImage/");
    registry.add("memory.card.back.path", () -> "/C:/Programmation/test/images/memoryCardGame/backImage/");
  }

  @BeforeEach
  public void init() {
    MockitoAnnotations.openMocks(this);
    resourcePatternResolver = mock(ResourcePatternResolver.class);
  }


  @Test
  void loadAllMemoryCardImages_test_method() throws IOException {

    /**
     * when
     */
    List<IImageData> result = folderImageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE);

    /**
     * then
     */
    assertEquals(1, result.size());
    assertEquals("test.png", result.get(0).getImageName());
  }

}
