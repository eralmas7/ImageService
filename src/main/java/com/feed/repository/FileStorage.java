package com.feed.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.feed.datatype.ImageUploadRequest;
import com.feed.datatype.ImageUploadResponse;
import com.feed.exception.FeedIOException;

@Repository
public class FileStorage implements Storage {

  private final String pathPrefix;
  private final Random random = new Random();
  private static final int MAX_RANDOMNESS = 5;

  @Autowired
  public FileStorage(@Value("${com.feed.controller.image.path.prefix}") final String pathPrefix) {
    this.pathPrefix = pathPrefix;
  }

  @Override
  public ImageUploadResponse save(final ImageUploadRequest imageUploadRequest) {
    final File finalPath = new File(pathPrefix + imageUploadRequest.getUserName());
    final String mfileName = finalPath.getPath() + "/" + getRandom() + imageUploadRequest.getFileName();
    try {
      finalPath.mkdirs();
      IOUtils.write(imageUploadRequest.getImage(), new FileOutputStream(mfileName));
      return new ImageUploadResponse(mfileName, imageUploadRequest.getRequestId());
    } catch (Exception exception) {
      throw new FeedIOException("Unable to Store Image for user: " + imageUploadRequest.getUserName() + " file: " + imageUploadRequest.getFileName(),
              exception);
    }
  }

  private String getRandom() {
    char[] randomChars = new char[MAX_RANDOMNESS];
    for (int i = 0; i < randomChars.length; i++) {
      randomChars[i] = (char) (random.nextInt(26) + 'a');
    }
    return new String(randomChars);
  }
}
