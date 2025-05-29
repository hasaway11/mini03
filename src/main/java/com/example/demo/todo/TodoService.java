package com.example.demo.todo;

import com.example.demo.exception.*;
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
    return todoDao.findByTno(tno).orElseThrow(()->new EntityNotFoundException("할일을 찾을 수 없습니다 "));
  }

  public Todo toggle(Integer tno) {
    if(!todoDao.existsByTno(tno))
      throw new EntityNotFoundException("할일을 찾을 수 없습니다 ");
    todoDao.toggle(tno);
    return todoDao.findByTno(tno).get();
  }

  public void delete(Integer tno) {
    if(!todoDao.existsByTno(tno))
      throw new EntityNotFoundException("할일을 찾을 수 없습니다 ");
    todoDao.delete(tno);
  }
}
