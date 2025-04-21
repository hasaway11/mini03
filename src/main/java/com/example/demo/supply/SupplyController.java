package com.example.demo.supply;

import jakarta.validation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@CrossOrigin("*")
@Validated
@Controller
public class SupplyController {
  @Autowired
  private SupplyService supplyService;

  @PostMapping("/supplies/new")
  public ResponseEntity<Supply> save(@RequestBody @Valid SupplyCreateDto dto, BindingResult br) {
    Supply supply = supplyService.save(dto);
    return ResponseEntity.ok(supply);
  }

  @GetMapping("/supplies")
  public ResponseEntity<List<Supply>> findAll() {
    return ResponseEntity.ok(supplyService.findAll());
  }

  @GetMapping("/supplies/{sno}")
  public ResponseEntity<Supply> findById(@PathVariable Integer sno) {
    Supply supply = supplyService.findBySno(sno);
    if (supply==null)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok(supply);
  }

  @PutMapping("/supplies/inc/{sno}")
  public ResponseEntity<Integer> inc(@PathVariable Integer sno) {
    int quantity = supplyService.inc(sno);
    return ResponseEntity.ok(quantity);
  }

  @PutMapping("/supplies/dec/{sno}")
  public ResponseEntity<Integer> dec(@PathVariable Integer sno) {
    int quantity = supplyService.dec(sno);
    return ResponseEntity.ok(quantity);
  }

  @DeleteMapping("/supplies/{sno}")
  public ResponseEntity<Void> delete(@PathVariable Integer sno) {
    boolean result = supplyService.delete(sno);
    if(!result)
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    return ResponseEntity.ok().build();
  }
}