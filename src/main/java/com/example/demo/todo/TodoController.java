package com.example.demo.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin("*")
@Controller
public class TodoController {
  @Autowired
  private TodoService todoService;

  @PostMapping("/todos/new")
  public ResponseEntity<Todo> save(@RequestBody TodoCreateDto dto) {
    Todo todo = todoService.save(dto);
    return ResponseEntity.ok(todo);
  }

  @GetMapping("/todos")
  public ResponseEntity<List<Todo>> findAll() {
    return ResponseEntity.ok(todoService.findAll());
  }

  @GetMapping("/todos/{tno}")
  public ResponseEntity<Todo> findById(@PathVariable Integer tno) {
    Todo todo = todoService.findByTno(tno);
    if(todo==null)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok(todo);
  }

  @PutMapping("/todos/toggle/{tno}")
  public ResponseEntity<Void> finish(@PathVariable int tno) {
    boolean result = todoService.toggle(tno);
    if(!result)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/todos/{tno}")
  public ResponseEntity<Void> delete(@PathVariable Integer tno) {
    boolean result = todoService.delete(tno);
    if(!result)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok().build();
  }
}
