package com.example.demo.supply;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SupplyService {
  @Autowired
  private SupplyDao supplyDao;

  public Supply save(SupplyCreateDto dto) {
    Supply supply = dto.toEntity();
    supplyDao.save(supply);
    return supply;
  }

  public List<Supply> findAll() {
    return supplyDao.findAll();
  }

  public Supply findBySno(Integer sno) {
    return supplyDao.findBySno(sno);
  }

  public int inc(Integer sno) {
    supplyDao.inc(sno);
    return supplyDao.findBySno(sno).getQuantity();
  }

  public int dec(Integer sno) {
    // 저장된 비품의 개수가 1이하인 경우 감소 불가
    if(supplyDao.findBySno(sno).getQuantity()<=1)
      return supplyDao.findBySno(sno).getQuantity();
    supplyDao.dec(sno);
    return supplyDao.findBySno(sno).getQuantity();
  }

  public boolean delete(Integer sno) {
    return supplyDao.delete(sno) == 1;
  }
}
