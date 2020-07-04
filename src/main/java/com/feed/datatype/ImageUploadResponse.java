package com.feed.datatype;

public class ImageUploadResponse {
  private String path;
  private String requestId;
  private String error;

  public ImageUploadResponse() {}

  public ImageUploadResponse(String path, String requestId) {
    this.path = path;
    this.requestId = requestId;
  }

  public ImageUploadResponse(String error, String requestId, boolean isError) {
    this.error = error;
    this.requestId = requestId;
  }

  public String getPath() {
    return path;
  }

  public String getRequestId() {
    return requestId;
  }

  public String getError() {
    return error;
  }
}
