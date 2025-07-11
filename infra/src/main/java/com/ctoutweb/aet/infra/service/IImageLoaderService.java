package com.ctoutweb.aet.infra.service;

import com.ctoutweb.aet.infra.model.IImageData;
import com.ctoutweb.aet.infra.model.ImageFace;

import java.io.IOException;
import java.util.List;

public interface IImageLoaderService {
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException;

}
