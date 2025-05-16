package com.example.demo.supply;

import com.example.demo.exception.*;
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
    return supplyDao.findBySno(sno).orElseThrow(()->new EntityNotFoundException("비품를 찾을 수 없습니다"));
  }

  public int inc(Integer sno) {
    if(!supplyDao.existsBySno(sno))
      throw new EntityNotFoundException("비품을 찾을 수 없습니다");
    supplyDao.inc(sno);
    return supplyDao.findBySno(sno).get().getQuantity();
  }

  public int dec(Integer sno) {
    Supply supply = supplyDao.findBySno(sno).orElseThrow(()->new EntityNotFoundException("비품를 찾을 수 없습니다"));
    if(supply.getQuantity()<=1)
      throw new JobFailException("더이상 감소할 수 없습니다");
    return supplyDao.findBySno(sno).get().getQuantity();
  }

  public void delete(Integer sno) {
    if(!supplyDao.existsBySno(sno))
      throw new EntityNotFoundException("비품을 찾을 수 없습니다");
    supplyDao.delete(sno);
  }
}
