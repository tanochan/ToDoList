package com.example.ToDoList.repositories;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.example.ToDoList.entities.ToDoItem;

/**
 * JpaRepositoryを継承する事で、fineOne、findAll、save、deleteの4メソッドが使用
 * ジェネリックスはエンティティのクラス名と、主キーの型を指定する.
 */
@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {

  // public List<ToDoItem> findByStatusAndTodoNameContainsOrderByCreatedAtAsc(String name);
  //@Query("from ToDoItem where todo_name lie %?1% and status = ?2 order by created_at asc")
  @Query("from ToDoItem where todo_name like %?1% and status = ?2 order by created_at asc")
  public List<ToDoItem> findSearchList(boolean status, String name);
}