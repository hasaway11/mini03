package com.example.demo.contact;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@Controller
public class ContactController {
  @Autowired
  private ContactService contactService;

  @GetMapping("/contact/add")
  public ModelAndView save() {
    return new ModelAndView("contact/add");
  }

  @PostMapping("/contact/add")
  public ModelAndView save(@ModelAttribute Contact contact) {
    int cno = contactService.save(contact);
    return new ModelAndView("redirect:/contact/read?cno=" + cno);
  }

  @GetMapping("/contact/list")
  public ModelAndView findAll() {
    return new ModelAndView("contact/list").addObject("contacts", contactService.findAll());
  }

  @GetMapping("/contact/read")
  public ModelAndView findById(@RequestParam Integer cno) {
    Optional<Contact> result = contactService.findById(cno);
    if (result.isEmpty())
      return new ModelAndView("redirect:/contact/list");
    return new ModelAndView("contact/read").addObject("contact", result.get());
  }

  @PostMapping("/contact/update")
  public ModelAndView update(@ModelAttribute Contact contact) {
    contactService.update(contact);
    return new ModelAndView("redirect:/contact/read?cno=" + contact.getCno());
  }

  @PostMapping("/contact/delete")
  public ModelAndView delete(@RequestParam Integer cno) {
    contactService.delete(cno);
    return new ModelAndView("redirect:/contact/list");
  }
}
