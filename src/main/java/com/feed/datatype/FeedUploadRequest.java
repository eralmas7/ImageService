package com.feed.datatype;

public class FeedUploadRequest {
  private ImageUploadResponse imageUploadResponse;
  private String userName;
  private String requestId;
  private String description;

  public FeedUploadRequest() {}

  public ImageUploadResponse getImageUploadResponse() {
    return imageUploadResponse;
  }

  public String getUserName() {
    return userName;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getDescription() {
    return description;
  }
}
