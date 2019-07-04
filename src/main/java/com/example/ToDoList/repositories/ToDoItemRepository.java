package com.example.ToDoList.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.ToDoList.entities.ToDoItem;
import java.util.List;

/**
 * JpaRepositoryを継承する事で、fineOne、findAll、save、deleteの4メソッドが使用
 * ジェネリックスはエンティティのクラス名と、主キーの型を指定する.
 */
@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {
  //public List<ToDoItem> findByDoneOrderByTitleAsc(boolean status);
  // 作成時間を記録する
}