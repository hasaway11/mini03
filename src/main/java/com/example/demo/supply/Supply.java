package com.example.demo.supply;

import lombok.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Supply {
  private Integer sno;
  private String name;
  private LocalDate regDate;
  private Integer quantity;
}
