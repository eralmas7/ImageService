package com.feed.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.feed.datatype.FeedUploadRequest;
import com.feed.datatype.FeedUploadResponse;
import com.feed.datatype.ImageUploadRequest;
import com.feed.datatype.ImageUploadResponse;
import com.feed.service.FeedUploader;
import com.feed.service.ImageUploader;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
@RequestMapping("/api/v1/")
public class FeedLoadService {

  private ImageUploader imageUploader;
  private FeedUploader feedUploader;
  private static final Logger logger = LoggerFactory.getLogger(FeedLoadService.class);

  @Autowired
  public FeedLoadService(final ImageUploader imageUploader, final FeedUploader feedUploader) {
    this.imageUploader = imageUploader;
    this.feedUploader = feedUploader;
  }

  @PostMapping("/image")
  @HystrixCommand(fallbackMethod = "uploadImageFallback",
          commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
  @ResponseStatus(HttpStatus.CREATED)
  public ImageUploadResponse uploadImage(@RequestBody final ImageUploadRequest imageUploadRequest) {
    return this.imageUploader.upload(imageUploadRequest);
  }

  @PostMapping("/feed")
  @ResponseStatus(HttpStatus.CREATED)
  @HystrixCommand(fallbackMethod = "uploadFeedFallback",
          commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")})
  public FeedUploadResponse uploadFeed(@RequestBody final FeedUploadRequest feedUploadRequest) {
    return this.feedUploader.upload(feedUploadRequest);
  }

  public FeedUploadResponse uploadFeedFallback(final FeedUploadRequest feedUploadRequest) {
    return new FeedUploadResponse(feedUploadRequest.getImageUploadResponse(), feedUploadRequest.getRequestId(), "Timeout! please retry");
  }

  public ImageUploadResponse uploadImageFallback(final ImageUploadRequest imageUploadRequest) {
    return new ImageUploadResponse("Timeout! please retry", imageUploadRequest.getRequestId(), true);
  }

  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_GATEWAY)
  public void handleException(final Exception ex) {
    logger.error("Handling error with message: {}", ex.getMessage());
  }
}
