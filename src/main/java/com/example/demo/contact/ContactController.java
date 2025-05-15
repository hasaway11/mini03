package com.example.demo.contact;

import io.swagger.v3.oas.annotations.*;
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
public class ContactController {
  @Autowired
  private ContactService contactService;

//  @PostConstruct
//  public void init() {
//    File uploadDir = new File(ContactConstants.UPLOAD_DIR);
//    if(!uploadDir.exists())
//      uploadDir.mkdirs();
//  }

  @Operation(summary="연락처 추가", description="name, address, tel로 연락처 추가")
  @PostMapping("/contacts/new")
  public ResponseEntity<Contact> save(@RequestBody @Valid ContactCreateDto dto, BindingResult br) {
    Contact contact = contactService.save(dto);
    return ResponseEntity.ok(contact);
  }

  @Operation(summary="연락처 목록", description="연락처 목록")
  @GetMapping("/contacts")
  public ResponseEntity<List<Contact>> findAll() {
    return ResponseEntity.ok(contactService.findAll());
  }

  @Operation(summary="연락처 읽기", description="path variable cno로 연락처 읽기")
  @GetMapping("/contacts/{cno}")
  public ResponseEntity<Contact> findById(@PathVariable Integer cno) {
    Contact contact = contactService.findByCno(cno);
    if (contact==null)
      return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    return ResponseEntity.ok(contact);
  }

  @Operation(summary="연락처 변경", description="cno로 찾아 address, tel 변경")
  @PutMapping("/contacts")
  public ResponseEntity<Void> update(@RequestBody @Valid ContactUpdateDto dto, BindingResult br) {
    contactService.update(dto);
    return ResponseEntity.ok().build();
  }

  @Operation(summary="연락처 삭제", description="path variable cno로 연락처 삭제")
  @DeleteMapping("/contacts/{cno}")
  public ResponseEntity<Void> delete(@PathVariable Integer cno) {
    contactService.delete(cno);
    return ResponseEntity.ok().build();
  }
}
