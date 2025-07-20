package com.ctoutweb.aet.infra.service.imageLoaderService;

import com.ctoutweb.aet.infra.model.image.IImageData;
import com.ctoutweb.aet.infra.model.image.IStreamImage;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface IImageLoaderService {
  /**
   * Renvoie les données des images qui sont disponible pour le jeu de carte.
   * Les images(Face ou arriere de la carte) se trouvent dans des repertoire different
   * @param imageFace ImageFace - Permets de cibler le type d'image a renvoyer. (Face ou arriere de la carte)
   * @return List<IImageData>
   * @throws IOException
   */
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException;

  /**
   * Renvoie un stream d'une image.
   * @param imagePath String - Le path de l'image qui est a streamer
   */
  public IStreamImage streamOneImage(String imagePath) throws IOException;

}
