package com.example.demo.supply;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.*;

@Data
public class SupplyCreateDto {
  @NotEmpty
  private String name;
  @NotNull
  private Integer quantity;

  public Supply toEntity() {
    return Supply.builder().name(name).quantity(quantity).regDate(LocalDate.now()).build();
  }
}
