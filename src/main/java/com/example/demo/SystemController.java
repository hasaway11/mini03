package com.example.demo;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller
public class SystemController {
  @GetMapping("/")
  public ModelAndView index() {
    return new ModelAndView("index");
  }
}
