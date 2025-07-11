package com.ctoutweb.aet.infra.gateway.memoryCardGame;

import com.ctoutweb.aet.core.entity.memoryCardGame.CardFace;
import com.ctoutweb.aet.infra.exception.ImageException;
import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageFace;
import com.ctoutweb.aet.infra.service.IImageLoaderService;
import com.ctoutweb.aet.core.usecase.memoryCardGame.port.IPortMemoryCardService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MemoryCardServiceImpl implements IPortMemoryCardService {
  private final IImageLoaderService imageLoaderService;
  private final Mapper mapper;

  public MemoryCardServiceImpl(IImageLoaderService imageLoaderService, Mapper mapper) {
    this.imageLoaderService = imageLoaderService;
    this.mapper = mapper;
  }

  @Override
  public String[] getAllAvailableCardPaths(CardFace cardFace) {
    try {
      return switch (cardFace) {
        case FRONT_FACE -> this.imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE).stream().map(IImageData::getImagePath).toArray(String[]::new);
        case BACK_FACE -> this.imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE).stream().map(IImageData::getImagePath).toArray(String[]::new);
      };
    } catch (IOException exception) {
      throw new ImageException("Il y a eut une erreur de chargement des images");
    }
  }
}
