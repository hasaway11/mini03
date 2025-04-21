package com.example.demo.todo;

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

  @PostMapping("/todos/new")
  public ResponseEntity<Todo> save(@RequestBody @Valid TodoCreateDto dto, BindingResult br) {
    Todo todo = todoService.save(dto);
    return ResponseEntity.ok(todo);
  }

  @GetMapping("/todos")
  public ResponseEntity<List<Todo>> findAll() {
    return ResponseEntity.ok(todoService.findAll());
  }

  @GetMapping("/todos/{tno}")
  public ResponseEntity<Todo> findById(@PathVariable int tno) {
    Todo todo = todoService.findByTno(tno);
    return ResponseEntity.ok(todo);
  }

  @PutMapping("/todos/toggle/{tno}")
  public ResponseEntity<Boolean> toggle(@PathVariable int tno) {
    boolean result = todoService.toggle(tno);
    return ResponseEntity.ok(result);
  }

  @DeleteMapping("/todos/{tno}")
  public ResponseEntity<Void> delete(@PathVariable int tno) {
    todoService.delete(tno);
    return ResponseEntity.ok().build();
  }
}
