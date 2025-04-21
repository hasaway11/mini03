package com.example.demo.supply;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface SupplyDao {
  @Select("select * from supply")
  List<Supply> findAll();

  @SelectKey(statement="select supply_seq.nextval from dual", keyProperty="sno", resultType=Integer.class, before=true)
  @Insert("insert into supply(sno, name, quantity) values(#{sno}, #{name}, #{quantity})")
  boolean save(Supply supply);

  @Select("select * from supply where sno=#{sno} and rownum=1")
  Optional<Supply> findBySno(int sno);

  @Select("select count(*) from supply where sno=#{sno} and rownum=1")
  boolean existsBySno(int sno);

  @Update("update supply set quantity=quantity+1 where sno=#{sno} and rownum=1")
  int inc(int sno);

  @Update("update supply set quantity=quantity-1 where sno=#{sno} and rownum=1")
  int dec(int sno);

  @Delete("delete from supply where sno=#{sno} and rownum=1")
  int delete(int sno);
}