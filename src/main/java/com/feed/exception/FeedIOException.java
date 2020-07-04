package com.feed.exception;

public class FeedIOException extends RuntimeException {

  private static final long serialVersionUID = -1308165952478466889L;

  public FeedIOException(String string, Exception exception) {
    super(string, exception);
  }
}
