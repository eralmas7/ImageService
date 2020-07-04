package com.feed.datatype;

public class ImageUploadRequest {
  private byte[] image;
  private String fileName;
  private String userName;
  private String requestId;

  public ImageUploadRequest() {}

  public byte[] getImage() {
    return image;
  }

  public String getFileName() {
    return fileName;
  }

  public String getUserName() {
    return userName;
  }

  public String getRequestId() {
    return requestId;
  }
}
