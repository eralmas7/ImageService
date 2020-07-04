package com.feed.service;

import com.feed.datatype.FeedUploadRequest;
import com.feed.datatype.FeedUploadResponse;

public interface FeedUploader {
  public FeedUploadResponse upload(final FeedUploadRequest feedUploadRequest);
}
