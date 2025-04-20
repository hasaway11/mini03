package com.example.demo.contact;

import lombok.*;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class Contact {
  private Integer cno;
  private String name;
  private String address;
  private String tel;
}
