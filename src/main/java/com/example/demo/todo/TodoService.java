package com.example.demo.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TodoService {
  @Autowired
  private TodoDao todoDao;

  public Todo save(TodoCreateDto dto) {
    Todo todo = dto.toEntity();
    todoDao.save(todo);
    return todo;
  }

  public List<Todo> findAll() {
    return todoDao.findAll();
  }

  public Todo findByTno(Integer tno) {
    return todoDao.findByTno(tno);
  }

  public boolean toggle(Integer tno) {
    return todoDao.toggle(tno)==1;
  }

  public boolean delete(Integer tno) {
    return todoDao.delete(tno)==1;
  }
}
