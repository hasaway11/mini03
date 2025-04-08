package com.example.demo.supply;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class SupplyController {
  @Autowired
  private SupplyService supplyService;

  @GetMapping("/supply/add")
  public ModelAndView save() {
    return new ModelAndView("supply/add");
  }

  @PostMapping("/supply/add")
  public ModelAndView save(@ModelAttribute Supply supply) {
    int sno = supplyService.save(supply);
    return new ModelAndView("redirect:/supply/read?sno=" + sno);
  }

  @GetMapping("/supply/list")
  public ModelAndView findAll() {
    return new ModelAndView("supply/list").addObject("supplies", supplyService.findAll());
  }

  @GetMapping("/supply/read")
  public ModelAndView findById(@RequestParam Integer sno) {
    Supply supply = supplyService.findBySno(sno);
    if (supply==null)
      return new ModelAndView("redirect:/supply/list");
    return new ModelAndView("supply/read").addObject("supply", supply);
  }

  @PostMapping("/supply/inc")
  public ModelAndView inc(@RequestParam Integer sno) {
    supplyService.inc(sno);
    return new ModelAndView("redirect:/supply/read?sno="+sno);
  }

  @PostMapping("/supply/dec")
  public ModelAndView dec(@RequestParam Integer sno) {
    supplyService.dec(sno);
    return new ModelAndView("redirect:/supply/read?sno="+sno);
  }

  @PostMapping("/supply/delete")
  public ModelAndView delete(@RequestParam Integer sno) {
    supplyService.delete(sno);
    return new ModelAndView("redirect:/supply/list");
  }
}