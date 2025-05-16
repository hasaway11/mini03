package com.example.demo.todo;

import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface TodoDao {
  @Select("select * from todo order by tno asc")
  List<Todo> findAll();

  @SelectKey(statement="select todo_seq.nextval from dual", keyProperty="tno", resultType=Integer.class, before=true)
  @Insert("insert into todo(tno, title, memo, deadline) values(#{tno}, #{title}, #{memo}, #{deadline})")
  int save(Todo todo);

  @Select("select * from todo where tno=#{tno} and rownum=1")
  Optional<Todo> findByTno(int tno);

  @Select("select count(*) from todo where tno=#{tno} and rownum=1")
  boolean existsByTno(int tno);

  @Update("update todo set finish=1-finish where tno=#{tno} and rownum=1")
  int toggle(int tno);

  @Delete("delete from todo where tno=#{tno} and rownum=1")
  int delete(int tno);
}
