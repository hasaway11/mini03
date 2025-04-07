package com.example.demo.contact;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Contact {
  private int cno;
  private String name;
  private String address;
  private String tel;
}
