package cda.greta94.planexam.controller;

import cda.greta94.planexam.dto.StandardErrorDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<StandardErrorDto> exception(Exception ex, HttpServletRequest request) {
    return ResponseEntity.badRequest().body(
        new StandardErrorDto(HttpStatus.BAD_REQUEST, ex, request));
  }
}
