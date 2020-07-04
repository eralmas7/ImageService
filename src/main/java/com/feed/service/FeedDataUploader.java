package com.feed.service;

import org.springframework.stereotype.Service;
import com.feed.datatype.FeedUploadRequest;
import com.feed.datatype.FeedUploadResponse;
import com.feed.repository.DataStorage;

@Service
public class FeedDataUploader implements FeedUploader {

  private final DataStorage dataStorage;

  public FeedDataUploader(final DataStorage dataStorage) {
    this.dataStorage = dataStorage;
  }

  @Override
  public FeedUploadResponse upload(final FeedUploadRequest feedUploadRequest) {
    return this.dataStorage.save(feedUploadRequest);
  }
}
