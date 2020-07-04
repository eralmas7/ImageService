package com.feed.service;

import org.springframework.stereotype.Service;
import com.feed.datatype.ImageUploadRequest;
import com.feed.datatype.ImageUploadResponse;
import com.feed.repository.Storage;

@Service
public class ImageFeedUploader implements ImageUploader {

  private final Storage storage;

  public ImageFeedUploader(final Storage storage) {
    this.storage = storage;
  }

  @Override
  public ImageUploadResponse upload(final ImageUploadRequest imageUploadRequest) {
    return this.storage.save(imageUploadRequest);
  }
}
