package com.example.ToDoList.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ToDoList.entities.ToDoItem;

/**
 * JpaRepositoryを継承する事で、fineOne、findAll、save、deleteの4メソッドが使用
 * ジェネリックスはエンティティのクラス名と、主キーの型を指定する.
 */
@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {

  // 検索条件
  @Query("FROM ToDoItem WHERE todo_name LIKE %?2% AND status = ?1 ORDER BY created_at ASC")
  public List<ToDoItem> findSearchList(boolean status, String name);

  @Query("FROM ToDoItem ORDER BY created_at DESC")
  public List<ToDoItem> findAllOrderByDateAsc();
}