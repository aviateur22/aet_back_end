//package com.ctoutweb.aet.infra.gateway.memoryCardGame;
//
//import com.ctoutweb.aet.core.provider.ICoreInstanceProvider;
//import com.ctoutweb.aet.core.usecase.memoryCardGame.boundary.IGenerateNewGameRequest;
//import com.ctoutweb.aet.core.usecase.memoryCardGame.provider.IDomainInstanceProvider;
//import com.ctoutweb.aet.core.entity.memoryCardGame.ICardImage;
//import com.ctoutweb.aet.infra.dto.GenerateNewMemoryCardGameRequestDto;
//import com.ctoutweb.aet.infra.mapper.InfraMapper;
//import com.ctoutweb.aet.infra.model.IImageData;
//import org.springframework.stereotype.Component;
//
//import java.util.function.BiFunction;
//import java.util.function.Function;
//
//@Component
//public class MemoryCardGameMapper extends InfraMapper {
//  private final IDomainInstanceProvider instanceProvider;
//  public MemoryCardGameMapper(ICoreInstanceProvider instanceProvider) {
//    this.instanceProvider = instanceProvider.getMemoryCardInstanceProvider().getDomainInstanceProvider();
//  }
//
//  /**
//   * Transforme les données des images en données une carte composé d'une face avant et arriere
//   * IImageData (1)-> Données de l'image composant la face avant de la carte
//   * IImageData (2)-> Données de l'image composant la face arriere de la carte
//   * @return ICardImage
//   */
//  public BiFunction<IImageData, IImageData, ICardImage> mapImageDataToCard() {
//    return (image1 , image2) -> this.instanceProvider.provideCardImageImpl(image1.getImagePath(), image2.getImagePath());
//  }
//
//  public Function<GenerateNewMemoryCardGameRequestDto, IGenerateNewGameRequest> mapInput() {
//    return dto -> this.instanceProvider.provideGenerateNewGameRequest(dto.gameLevel())
//  }
//}
