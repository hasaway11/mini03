package com.example.demo.contact;

import jakarta.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@Controller
public class ContactController {
  @Autowired
  private ContactService contactService;

  @PostConstruct
  public void init() {
    File uploadDir = new File(ContactConstants.UPLOAD_DIR);
    if(!uploadDir.exists())
      uploadDir.mkdirs();
  }
  @PostMapping("/contacts/new")
  public ResponseEntity<Contact> save(@ModelAttribute ContactCreateDto dto) {
    Contact contact = contactService.save(dto);
    return ResponseEntity.ok(contact);
  }

  @GetMapping("/contacts")
  public ResponseEntity<List<Contact>> findAll() {
    return ResponseEntity.ok(contactService.findAll());
  }

  @GetMapping("/contacts/{cno}")
  public ResponseEntity<Contact> findById(@PathVariable Integer cno) {
    Contact contact = contactService.findById(cno);
    if (contact==null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    return ResponseEntity.ok(contact);
  }

  @PutMapping("/contacts")
  public ResponseEntity<Contact> update(@ModelAttribute ContactUpdateDto dto) {
    Contact contact = contactService.update(dto);
    return ResponseEntity.ok(contact);
  }

  @DeleteMapping("/contacts")
  public ResponseEntity<Void> delete(@RequestParam Integer cno) {
    boolean result = contactService.delete(cno);
    if(result)
      return ResponseEntity.ok(null);
    return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
  }
}
