package com.example.demo.todo;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class TodoController {
  @Autowired
  private TodoService todoService;

  @GetMapping("/todo/add")
  public ModelAndView save() {
    return new ModelAndView("todo/add");
  }

  @PostMapping("/todo/add")
  public ModelAndView save(@ModelAttribute Todo todo) {
    int tno = todoService.save(todo);
    return new ModelAndView("redirect:/todo/read?tno=" + tno);
  }

  @GetMapping("/todo/list")
  public ModelAndView findAll() {
    return new ModelAndView("todo/list").addObject("todos", todoService.findAll());
  }

  @GetMapping("/todo/read")
  public ModelAndView findById(@RequestParam Integer tno) {
    Optional<Todo> result = todoService.findByTno(tno);
    if(result.isEmpty())
      return new ModelAndView("redirect:/todo/list");
    return new ModelAndView("todo/read").addObject("todo", result.get());
  }

  @PostMapping("/todo/finish")
  public ModelAndView finish(@RequestParam Integer tno) {
    todoService.finish(tno);
    return new ModelAndView("redirect:/todo/read?tno=" + tno);
  }

  @PostMapping("/todo/delete")
  public ModelAndView delete(@RequestParam Integer tno) {
    todoService.delete(tno);
    return new ModelAndView("redirect:/todo/list");
  }
}
