package com.ctoutweb.aet.gateway.memoryCardGame;

import com.ctoutweb.aet.entity.memoryCardGame.ICardImage;
import com.ctoutweb.aet.entity.memoryCardGame.impl.CardImageImpl;
import com.ctoutweb.aet.exception.GatewayException;
import com.ctoutweb.aet.model.IImageData;
import com.ctoutweb.aet.model.ImageFace;
import com.ctoutweb.aet.service.IImageLoaderService;
import com.ctoutweb.aet.usecase.memoryCardGame.port.IMemoryCardService;
import com.ctoutweb.aet.util.ListUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class MemoryCardServiceImpl implements IMemoryCardService {
  private final IImageLoaderService imageLoaderService;
  private final Mapper mapper;

  public MemoryCardServiceImpl(IImageLoaderService imageLoaderService, Mapper mapper) {
    this.imageLoaderService = imageLoaderService;
    this.mapper = mapper;
  }

  @Override
  public ICardImage[] generateRandomCards() {
    try {
      List<IImageData> frontImages = this.imageLoaderService.loadAllMemoryCardImages(ImageFace.FRONT_FACE);
      List<IImageData> backImages = this.imageLoaderService.loadAllMemoryCardImages(ImageFace.BACK_FACE);

      List<ICardImage> cards = frontImages.stream().map(frontImage -> mapper.map(frontImage, selectBackImage(backImages), mapper.mapImageDataToCard())).toList();
      return cards.toArray(new ICardImage[0]);
    } catch (IOException exception) {
      throw new GatewayException("Il y a eut une erreur de chargement des images");
    }
  }

  /**
   * Selectionne l'arriere d'une carte
   * @param backImages - Liste des image disponible poour la face arriere d'une carte
   * @return IImageData
   */
  private IImageData selectBackImage(List<IImageData> backImages) {
    return ListUtil.selectRandomItem(backImages);
  }
}
