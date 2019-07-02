package com.example.ToDoList.model;

/*
  toDo名、期限データ
*/
public class TopPageForm {

  /*
   * toDoList名
   */
  private String toDoName;

  /*
   * 期限
   */
  private String deadline;

  public TopPageForm(String toDoName, String deadline){
    this.toDoName = toDoName;
    this.deadline = deadline;
  }

  public TopPageForm(){
    
  }

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