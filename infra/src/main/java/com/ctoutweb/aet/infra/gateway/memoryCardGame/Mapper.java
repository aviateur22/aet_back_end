package com.ctoutweb.aet.infra.gateway.memoryCardGame;

import com.ctoutweb.aet.core.provider.CoreFactory;
import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;
import com.ctoutweb.aet.infra.model.IImageData;
import org.springframework.stereotype.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

@Component
public class Mapper {
  private final CoreFactory coreFactory;

  public Mapper(CoreFactory coreFactory) {
    this.coreFactory = coreFactory;
  }

  public<T,U> U map(T data, Function<T, U> mapFunction) {
    return mapFunction.apply(data);
  }

  public<T,U> U map(T data1, T data2, BiFunction<T, T, U> mapFunction) {
    return mapFunction.apply(data1, data2);
  }

  /**
   * Transforme les données des images en données une carte composé d'une face avant et arriere
   * IImageData (1)-> Données de l'image composant la face avant de la carte
   * IImageData (2)-> Données de l'image composant la face arriere de la carte
   * @return ICardImage
   */
  public BiFunction<IImageData, IImageData, ICardImage> mapImageDataToCard() {
    return (image1 , image2) -> this.coreFactory.getCardImageImpl(image1.getImagePath(), image2.getImagePath());
  }
}
