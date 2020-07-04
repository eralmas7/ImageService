package com.feed.repository;

import com.feed.datatype.FeedUploadRequest;
import com.feed.datatype.FeedUploadResponse;

public interface DataStorage {
  public FeedUploadResponse save(final FeedUploadRequest feedUploadRequest);
}
