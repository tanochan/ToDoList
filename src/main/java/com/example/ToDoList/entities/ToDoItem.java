package com.example.ToDoList.entities;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * リレーショナルデータベースで管理されているレコードを表現するJavaクラス
 */
@Entity
@Table(name="todoitems")
public class ToDoItem {
  @Id
  @Column(name="id")
  @GeneratedValue(strategy=GenerationType.IDENTITY) // primary key生成
  private int id;
  @Column(name="todo_name")
  private String todo_name; 
  @Column(name="deadline")
  private String deadline;
  @Column(name="status")
  private boolean status; // true:完了 false:未完了 
  @Column(name="created_at")
  private String created_at;
  @Column(name="updated_at")
  private Date ipdated_at;

  public int getId(){
    return id;
  }

  public void setId(int id){
    this.id = id;
  }

  public String getToDoName(){
    return todo_name;
  }

  public void setToDoName(String todo_name){
    this.todo_name = todo_name;
  }

  public String getDeadline(){
    return deadline;
  }

  public void setDeadline(String deadline){
    this.deadline = deadline;
  }

  public Boolean getStatus(){
    return status;
  }

  public void setStatus(boolean status){
    this.status = status;
  }

  public String getCreated_at(){
    return created_at;
  }

  public void setCreated_at(String created_at){
    this.created_at = created_at;
  }
} 