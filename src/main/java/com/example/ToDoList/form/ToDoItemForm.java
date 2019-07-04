package com.example.ToDoList.form;

import java.util.List;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.example.ToDoList.entities.ToDoItem;

public class ToDoItemForm {
  private boolean isDone;

  private List<ToDoItem> toDoItems;

  public List<ToDoItem> getToDoItems(){
    return toDoItems;
  }

  public void setToDoItems(List<ToDoItem> toDoItems) {
    this.toDoItems = toDoItems;
  } 

  public boolean isDone() {
    return isDone;
  }

  public void setDone(boolean isDone){
    this.isDone = isDone;
  }
}