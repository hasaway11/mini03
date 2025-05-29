package com.example.demo.todo;

import io.swagger.v3.oas.annotations.*;
import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@Validated
@Controller
public class TodoController {
  @Autowired
  private TodoService todoService;

  @Operation(summary="할일 작성", description="title, memo, deadline으로 할일 추가")
  @PostMapping("/todos/new")
  public ResponseEntity<Todo> save(@RequestBody @Valid TodoCreateDto dto, BindingResult br) {
    Todo todo = todoService.save(dto);
    return ResponseEntity.ok(todo);
  }

  @Operation(summary="할일 목록", description="할일 목록")
  @GetMapping("/todos")
  public ResponseEntity<List<Todo>> findAll() {
    return ResponseEntity.ok(todoService.findAll());
  }

  @Operation(summary="할일 읽기", description="path variable tno로 할일 읽기")
  @GetMapping("/todos/{tno}")
  public ResponseEntity<Todo> findById(@PathVariable int tno) {
    Todo todo = todoService.findByTno(tno);
    return ResponseEntity.ok(todo);
  }

  @Operation(summary="할일 상태 토글", description="할일의 finish를 토글")
  @PutMapping("/todos/toggle/{tno}")
  public ResponseEntity<Todo> toggle(@PathVariable int tno) {
    return ResponseEntity.ok(todoService.toggle(tno));
  }

  @Operation(summary="할일 삭제", description="path variable tno로 할일 삭제")
  @DeleteMapping("/todos/{tno}")
  public ResponseEntity<Void> delete(@PathVariable int tno) {
    todoService.delete(tno);
    return ResponseEntity.ok().build();
  }
}
