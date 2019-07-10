package com.example.todolist.service;

import javax.transaction.Transactional;
import java.util.List;

import com.example.todolist.repositories.ToDoItemRepository;
import com.example.todolist.entities.ToDoItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

@Service
@Transactional
public class ToDoItemService {
  @Autowired
  private ToDoItemRepository repository;

  public List<ToDoItem> findAll() {
    return repository.findAll(new Sort(Sort.Direction.ASC, "id"));
  }

  public ToDoItem save(ToDoItem toDoItem) {
    return repository.save(toDoItem);
  } 

  public void deleteById(int id) {
    repository.deleteById(id);
  }

  public ToDoItem findById(int id) {
    return repository.findById(id).get();
  }
}