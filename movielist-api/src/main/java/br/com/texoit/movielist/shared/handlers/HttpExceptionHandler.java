package br.com.texoit.movielist.shared.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

  private ResponseEntity<Object> proxyHandling(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    HttpApplicationErrorResponse error = new HttpApplicationErrorResponse(status, ex.getLocalizedMessage(), null);
    return new ResponseEntity<>(error, error.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    List<String> details = new ArrayList<>();
    for(ObjectError error : ex.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    HttpApplicationErrorResponse error = new HttpApplicationErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed", details);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return this.proxyHandling(ex, headers, status, request);
  }

}
