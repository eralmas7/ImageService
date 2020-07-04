package com.feed.repository;

import java.io.File;
import java.io.FileWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.feed.datatype.FeedUploadRequest;
import com.feed.datatype.FeedUploadResponse;
import com.feed.exception.FeedIOException;
import com.opencsv.CSVWriter;

@Repository
public class FeedDataStorage implements DataStorage {

  private final String pathPrefix;

  @Autowired
  public FeedDataStorage(@Value("${com.feed.controller.feed.path.prefix}") final String pathPrefix) {
    this.pathPrefix = pathPrefix;
  }

  @Override
  public FeedUploadResponse save(FeedUploadRequest feedUploadRequest) {
    final File finalPath = new File(pathPrefix);
    final String mfileName = finalPath.getPath() + "/" + feedUploadRequest.getUserName() + ".txt";
    finalPath.mkdirs();
    try (CSVWriter writer = new CSVWriter(new FileWriter(mfileName))) {
      writer.writeNext(new String[] {feedUploadRequest.getDescription(), feedUploadRequest.getRequestId()});
      return new FeedUploadResponse(feedUploadRequest.getImageUploadResponse(), feedUploadRequest.getRequestId());
    } catch (Exception exception) {
      throw new FeedIOException(
              "Unable to Store Feed for user: " + feedUploadRequest.getUserName() + " requestId: " + feedUploadRequest.getRequestId(), exception);
    }
  }
}
