package com.example.ToDoList.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.ToDoList.entities.ToDoItem;

/*
 * toDoList情報
 */
@Controller
public class TopPageController {

  private List<ToDoItem> todos;

  public TopPageController() {
    todos = new ArrayList<>();
  }

  private void addTodo(ToDoItem todo) {
    todos.add(todo);
    //このへんでDB操作
  }

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String displayList(Model model){
    // 入力フォームで取り扱うオブジェクトを指定
    model.addAttribute("ToDoItem", new ToDoItem());
    model.addAttribute("todoList", todos);
    return "topPage";
  }

  @RequestMapping(value="/", method=RequestMethod.POST)
  public String createToDoList(@ModelAttribute ToDoItem todoitem, Model model) {
    //topPageFormに入力フォームの内容が格納されている
    this.addTodo(todoitem);
    model.addAttribute("ToDoItem", todoitem);
    model.addAttribute("todoList", todos);
    return "topPage";
  }

  /**
   * 時刻の設定
   * @return yyyy年MM月dd日
   */
  public String settingTime() {
    Date date = new Date();
    String str = String.valueOf(date);
    return str;
  }
}