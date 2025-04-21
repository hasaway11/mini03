package com.example.demo.exception;

import lombok.*;

@AllArgsConstructor
@Getter
public class EntityNotFoundException extends RuntimeException {
  private String message;
}
