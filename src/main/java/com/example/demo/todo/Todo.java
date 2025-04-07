package com.example.demo.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class Todo {
  private int tno;
  private String title;
  private String memo;
  private LocalDate regDate;
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate deadline;
  private boolean finish;
}
