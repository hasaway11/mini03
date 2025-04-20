package com.example.demo.todo;

import lombok.*;
import org.springframework.format.annotation.*;

import java.time.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Todo {
  private Integer tno;
  private String title;
  private String memo;
  private LocalDate regDate;
  private LocalDate deadline;
  private Boolean finish;
}
