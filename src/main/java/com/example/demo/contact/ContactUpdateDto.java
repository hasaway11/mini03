package com.example.demo.contact;

import lombok.*;
import org.springframework.web.multipart.*;

@Data
public class ContactUpdateDto {
  private Integer cno;
  private String address;
  private String tel;
  private MultipartFile photo;

  public Contact toEntity(Contact contact) {
    return Contact.builder().cno(cno).address(address).tel(tel).name(contact.getName()).photo(contact.getPhoto()).build();
  }
}
