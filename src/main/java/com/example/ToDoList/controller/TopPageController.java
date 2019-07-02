package com.example.ToDoList.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.ToDoList.model.TopPageForm;

/*
 * toDoList情報
 */
@Controller
public class TopPageController {

  private List<TopPageForm> todos;

  public TopPageController() {
    todos = new ArrayList<>();
  }

  private void addTodo(TopPageForm todo) {
    todos.add(todo);
    //このへんでDB操作
  }

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String displayList(Model model){
    // 入力フォームで取り扱うオブジェクトを指定
    model.addAttribute("TopPageForm", new TopPageForm());
    model.addAttribute("todoList", todos);
    return "topPage";
  }

  @RequestMapping(value="/", method=RequestMethod.POST)
  public String createToDoList(@ModelAttribute TopPageForm form, Model model) {
    //topPageFormに入力フォームの内容が格納されている
    this.addTodo(form);
    model.addAttribute("TopPageForm", form);
    model.addAttribute("todoList", todos);
    return "topPage";
  }
}