package com.example.ToDoList.model;

import java.io.Serializable;

/*
  toDo名、期限データ
*/
public class TopPageForm implements Serializable {

  /*
   * toDoList名
   */
  private String toDoName;

  /*
   * 期限
   */
  private String deadline;

  public String getToDoName(){
    return toDoName;
  }

  public void setToDoName(String toDoName){
    this.toDoName = toDoName;
  }

  public String getDeadline(){
    return deadline;
  }

  public void setDeadline(String deadline){
    this.deadline = deadline;
  }
}