package com.example.demo.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TodoService {
  @Autowired
  private TodoDao todoDao;

  public int save(Todo todo) {
    todoDao.save(todo);
    return todo.getTno();
  }

  public List<Todo> findAll() {
    return todoDao.findAll();
  }

  public Optional<Todo> findByTno(Integer tno) {
    return todoDao.findByTno(tno);
  }

  public boolean finish(Integer tno) {
    return todoDao.finish(tno)==1;
  }

  public boolean delete(Integer tno) {
    return todoDao.delete(tno)==1;
  }
}
