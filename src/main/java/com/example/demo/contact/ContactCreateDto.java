package com.example.demo.contact;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.*;

@Data
public class ContactCreateDto {
  @NotEmpty
  private String name;
  @NotEmpty
  private String address;
  @NotEmpty
  private String tel;

  public Contact toEntity() {
    return Contact.builder().name(name).address(address).tel(tel).build();
  }
}
