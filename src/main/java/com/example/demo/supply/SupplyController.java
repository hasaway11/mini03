package com.example.demo.supply;

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
public class SupplyController {
  @Autowired
  private SupplyService supplyService;

  @Operation(summary="비품 추가", description="name, quantity로 비품 추가")
  @PostMapping("/supplies/new")
  public ResponseEntity<Supply> save(@RequestBody @Valid SupplyCreateDto dto, BindingResult br) {
    Supply supply = supplyService.save(dto);
    return ResponseEntity.ok(supply);
  }

  @Operation(summary="비품 목록", description="비품 목록")
  @GetMapping("/supplies")
  public ResponseEntity<List<Supply>> findAll() {
    return ResponseEntity.ok(supplyService.findAll());
  }

  @Operation(summary="비품 읽기", description="path variable sno로 비품 읽기 ")
  @GetMapping("/supplies/{sno}")
  public ResponseEntity<Supply> findById(@PathVariable Integer sno) {
    Supply supply = supplyService.findBySno(sno);
    return ResponseEntity.ok(supply);
  }

  @Operation(summary="개수 증가", description="path variable sno로 비품 개수 증가")
  @PutMapping("/supplies/inc/{sno}")
  public ResponseEntity<Integer> inc(@PathVariable Integer sno) {
    int quantity = supplyService.inc(sno);
    return ResponseEntity.ok(quantity);
  }

  @Operation(summary="개수 감소", description="path variable sno로 비품 개수 감소")
  @PutMapping("/supplies/dec/{sno}")
  public ResponseEntity<Integer> dec(@PathVariable Integer sno) {
    int quantity = supplyService.dec(sno);
    return ResponseEntity.ok(quantity);
  }

  @Operation(summary="비품 삭제", description="path variable sno로 비품 삭제")
  @DeleteMapping("/supplies/{sno}")
  public ResponseEntity<Void> delete(@PathVariable Integer sno) {
    supplyService.delete(sno);
    return ResponseEntity.ok().build();
  }
}