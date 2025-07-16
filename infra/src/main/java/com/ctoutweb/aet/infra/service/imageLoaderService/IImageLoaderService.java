package com.ctoutweb.aet.infra.service.imageLoaderService;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.memoryCardGame.ImageFace;

import java.io.IOException;
import java.util.List;

public interface IImageLoaderService {
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException;

}
