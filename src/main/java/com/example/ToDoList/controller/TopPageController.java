package com.example.ToDoList.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.ToDoList.entities.ToDoItem;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ToDoList.repositories.ToDoItemRepository;
import com.example.ToDoList.form.ToDoItemForm;;

/*
 * toDoList情報
 */
@Controller
public class TopPageController {

  @Autowired
  ToDoItemRepository repository;

  //private List<ToDoItem> todos;

  // public TopPageController() {
  //   todos = new ArrayList<>();
  // }

  // private void addTodo(ToDoItem todo) {
  //   todos.add(todo);
  //   //このへんでDB操作
  // }

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String displayList(Model model){
    List<ToDoItem> toDoItems =repository.findAll();
    model.addAttribute("toDoItems", toDoItems);
    return "topPage";
  }

  @RequestMapping(value="/new", method=RequestMethod.POST)
  public String newItem(ToDoItem item) {
      //item.setDone(false);
      //item = new ToDoItem();
      //item.setCreated_at(settingCreated_at());
      item.setCreated_at(settingCreated_at());
      this.repository.save(item);
      return "redirect:/";
  }

  public String settingCreated_at(){
    //カレンダークラスのオブジェクトを生成する
    Calendar cl = Calendar.getInstance();
    //フォーマットを指定する
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    //フォーマットをフォーマットを変更する
    //sdf.applyPattern("yyyy年MM月dd日");
    return sdf.format(cl.getTime()).toString(); 
  }
}