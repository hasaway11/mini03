package com.example.demo.advice;

import jakarta.validation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.*;

@ControllerAdvice
public class CustomControllerAdvice {
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> methodArgumentTypeMismatchException() {
    return ResponseEntity.badRequest().body("올바르지 않은 접근경로입니다");
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> constraintViolationException(ConstraintViolationException e) {
    String fieldName = e.getConstraintViolations().stream().findFirst().get().getPropertyPath().toString();
    String message = e.getConstraintViolations().stream().findFirst().get().getMessage();
    return ResponseEntity.badRequest().body(fieldName + ": " + message);
  }
}
