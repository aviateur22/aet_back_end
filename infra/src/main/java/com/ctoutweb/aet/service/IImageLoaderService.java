package com.ctoutweb.aet.service;

import com.ctoutweb.aet.model.IImageData;
import com.ctoutweb.aet.model.ImageFace;

import java.io.IOException;
import java.util.List;

public interface IImageLoaderService {
  public List<IImageData> loadAllMemoryCardImages(ImageFace imageFace) throws IOException;

}
