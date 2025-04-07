package com.example.demo.contact;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface ContactDao {
  @Select(value = "select * from contact")
  List<Contact> findAll();

  @SelectKey(statement="select contact_seq.nextval from dual", keyProperty="tno", resultType=Integer.class, before=true)
  @Insert("insert into contact(cno, name, address, tel) values(#{tno}, #{name}, #{address}, #{tel})")
  boolean save(Contact contact);

  @Select("select * from contact where cno=#{cno} and rownum=1")
  Optional<Contact> findByCno(int cno);

  @Update("update contact set address=#{address}, tel=#{tel} where cno=#{cno} and rownum=1")
  int update(Contact contact);

  @Delete("delete from contact where cno=#{cno} and rownum=1")
  int delete(int cno);
}