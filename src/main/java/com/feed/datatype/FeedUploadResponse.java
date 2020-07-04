package com.feed.datatype;

public class FeedUploadResponse {
  private ImageUploadResponse imageUploadResponse;
  private String requestId;
  private String error;

  public FeedUploadResponse() {}

  public FeedUploadResponse(ImageUploadResponse imageUploadResponse, String requestId) {
    this.imageUploadResponse = imageUploadResponse;
    this.requestId = requestId;
  }

  public FeedUploadResponse(ImageUploadResponse imageUploadResponse, String requestId, String error) {
    this.imageUploadResponse = imageUploadResponse;
    this.requestId = requestId;
    this.error = error;
  }

  public ImageUploadResponse getImageUploadResponse() {
    return imageUploadResponse;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getError() {
    return error;
  }

}
