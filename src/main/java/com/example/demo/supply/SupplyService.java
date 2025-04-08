package com.example.demo.supply;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class SupplyService {
  @Autowired
  private SupplyDao supplyDao;

  public int save(Supply supply) {
    supplyDao.save(supply);
    return supply.getSno();
  }

  public List<Supply> findAll() {
    return supplyDao.findAll();
  }

  public Supply findBySno(Integer sno) {
    return supplyDao.findBySno(sno);
  }

  public boolean inc(Integer sno) {
    return supplyDao.inc(sno) == 1;
  }

  public boolean dec(Integer sno) {
    // 저장된 비품의 개수가 1이하인 경우 감소 불가
    if(supplyDao.findBySno(sno).getQuantity()<=1)
      return false;
    return supplyDao.dec(sno) == 1;
  }

  public boolean delete(Integer sno) {
    return supplyDao.delete(sno) == 1;
  }
}
