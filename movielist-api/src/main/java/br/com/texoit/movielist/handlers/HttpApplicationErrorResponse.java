package br.com.texoit.movielist.handlers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class HttpApplicationErrorResponse {
  
  private HttpStatus status;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDateTime timestamp;
  private String message;
  private List<String> details;

  HttpApplicationErrorResponse() {
    this.timestamp = LocalDateTime.now();
  }

  public HttpApplicationErrorResponse(HttpStatus status, String message, List<String> details) {
    this();
    this.status = status;
    this.message = message;
    this.details = details;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public String getMessage() {
    return message;
  }

  public List<String> getDetails() {
    return details;
  }

}
