package com.feed.repository;

import com.feed.datatype.ImageUploadRequest;
import com.feed.datatype.ImageUploadResponse;

public interface Storage {
  public ImageUploadResponse save(final ImageUploadRequest imageUploadRequest);
}
