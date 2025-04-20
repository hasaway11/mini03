package com.example.demo.contact;

import lombok.*;
import org.springframework.web.multipart.*;

@Data
public class ContactCreateDto {
  private String name;
  private String address;
  private String tel;

  public Contact toEntity() {
    return Contact.builder().name(name).address(address).tel(tel).build();
  }
}
