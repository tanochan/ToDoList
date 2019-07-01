package com.example.ToDoList.controller;

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

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String displayList(Model model){
    // 入力フォームで取り扱うオブジェクトを指定
    model.addAttribute("TopPageForm", new TopPageForm());
    return "topPage";
  }

  @RequestMapping(value="/", method=RequestMethod.POST)
  public String createToDoList(@ModelAttribute TopPageForm form, Model model) {
    //topPageFormに入力フォームの内容が格納されている
    model.addAttribute("TopPageForm", form);
    return "topPage";
  }
}