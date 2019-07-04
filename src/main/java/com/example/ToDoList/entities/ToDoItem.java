package com.example.ToDoList.entities;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

/*
  toDo名、期限データ
*/
public class ToDoItem {

  /*
   * toDoList名
   */
  private String toDoName;

  /*
   * 期限
   */
  private String deadline;

  // 作成日
  private String date;

  // listID
  private int id;

  // 完了:true 未完了:false
  private boolean status;

  public ToDoItem(String toDoName, String deadline, String date){
    this.toDoName = toDoName;
    this.deadline = deadline;
    this.date = date;
  }

  public ToDoItem(){
    
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

  public int getID(){
    return id;
  }

  public void setID(int id) {
    this.id = id;
  }

  public String getDate(){
    //カレンダークラスのオブジェクトを生成する
    Calendar cl = Calendar.getInstance();
    //フォーマットを指定する
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
    //フォーマットをフォーマットを変更する
    //sdf.applyPattern("yyy年MM月dd日");
    date = sdf.format(cl.getTime()).toString();
    return date;
  }

  public void setDate(String date){
    this.date = date;
  }
} 