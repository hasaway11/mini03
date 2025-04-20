package com.example.demo.todo;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Data
public class TodoCreateDto {
  @NotEmpty
  private String title;
  @NotEmpty
  private String memo;
  @NotNull
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate deadline;

  public Todo toEntity() {
    return Todo.builder().title(title).memo(memo).deadline(deadline).regDate(LocalDate.now()).finish(false).build();
  }
}
