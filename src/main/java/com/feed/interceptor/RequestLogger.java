package com.feed.interceptor;

import java.util.Enumeration;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestLogger extends HandlerInterceptorAdapter {
  private static final Logger logger = LoggerFactory.getLogger(RequestLogger.class);

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String requestId = UUID.randomUUID().toString();
    log(request, requestId);
    long startTime = System.currentTimeMillis();
    request.setAttribute("startTime", startTime);
    request.setAttribute("requestId", requestId);
    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    super.afterCompletion(request, response, handler, ex);
    long startTime = (Long) request.getAttribute("startTime");
    long endTime = System.currentTimeMillis();
    long executeTime = endTime - startTime;
    logger.info("requestId {}, request took: {} ms", request.getAttribute("requestId"), executeTime);
  }

  private void log(HttpServletRequest request, String requestId) {
    logger.info("requestId {}, host {}  HttpMethod: {}, URI : {}", requestId, request.getHeader("host"), request.getMethod(),
            request.getRequestURI());
    final Enumeration<String> headers = request.getHeaderNames();
    while (headers.hasMoreElements()) {
      String header = headers.nextElement();
      logger.info("{} {} : {}", requestId, header, request.getHeader(header));
    }
  }
}
