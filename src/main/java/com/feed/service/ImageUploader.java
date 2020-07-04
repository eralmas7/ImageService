package com.feed.service;

import com.feed.datatype.ImageUploadRequest;
import com.feed.datatype.ImageUploadResponse;

public interface ImageUploader {
  public ImageUploadResponse upload(final ImageUploadRequest imageUploadRequest);
}
