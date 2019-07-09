package com.example.ToDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import com.example.ToDoList.entities.ToDoItem;
import com.example.ToDoList.repositories.ToDoItemRepository;
import com.example.ToDoList.service.ToDoItemService;
//import com.example.ToDoList.common.LogUtils;

/*
 * toDoList情報
 */
@Controller
public class TopPageController {

  @Autowired
  ToDoItemService toDoItemService;

  @Autowired
  ToDoItemRepository repository;

  // @Autowired
  // protected ResourceLoader resourceLoader;

  @RequestMapping(value="/", method=RequestMethod.GET)
  public String displayList(Model model) {
    //List<ToDoItem> toDoItems = this.toDoItemService.findAll();
    List<ToDoItem> toDoItems = this.repository.findAllOrderByDateAsc();
    model.addAttribute("toDoItems", toDoItems);
    return "topPage";
  }

  // 検索画面
  @RequestMapping(value="/search", method=RequestMethod.GET)
  public String searchView(Model model) {
    return "searchPage";
  }

  // 検索ボタンクリック時
  @RequestMapping(value="/searched", method=RequestMethod.POST)
  public String searchResult(@RequestParam("search_name") String name, Model model){
    List<ToDoItem> toDoItem = this.repository.findSearchList(false, name);
    //LogUtils.info(toDoItem.getToDoName());
    model.addAttribute("toDoItem", toDoItem);
    return "searchPage";
  }

  // todoの追加ボタンクリック時
  @RequestMapping(value="/new", method=RequestMethod.POST)
  public String newItem(ToDoItem item) {
      item.setStatus(false);
      this.toDoItemService.save(item);
      return "redirect:/";
  }

  // 編集ボタンクリック時
  @RequestMapping(value="/edit", method=RequestMethod.POST)
  public String editCard(@RequestParam("id") Integer id, Model model) {
    ToDoItem item = this.toDoItemService.findById(id);
    model.addAttribute("toDoItem", item);
    return "editPage";
  }

  // 更新ボタンクリック時
  @RequestMapping(value="/update", method=RequestMethod.POST)
  public String updateCard(@RequestParam("id") Integer id, @ModelAttribute ToDoItem toDoItem) {
    toDoItem.setId(id);
    //toDoItem.setCreated_at(settingCreated_at());
    toDoItemService.save(toDoItem);
    return "redirect:/";
  }

  // 未完了のボタンクリック時
  @RequestMapping(value="/switch/complete", method=RequestMethod.POST)
  public String switchComplete(@RequestParam("id") Integer id, @ModelAttribute ToDoItem toDoItem) {
    toDoItem = this.toDoItemService.findById(id);
    toDoItem.setStatus(true);
    toDoItemService.save(toDoItem);
    return "redirect:/";
  }

  // 完了のボタンクリック時
  @RequestMapping(value="/switch/incomplete", method=RequestMethod.POST)
  public String switchIncomplete(@RequestParam("id") Integer id, @ModelAttribute ToDoItem toDoItem) {
    toDoItem = this.toDoItemService.findById(id);
    toDoItem.setStatus(false);
    toDoItemService.save(toDoItem);
    return "redirect:/";
  }

  // 文字数チェック
  public void check(ToDoItem toDoItem){
    int word_count = toDoItem.getToDoName().length();
    if(word_count == 0){

    }else if(31 <= word_count){

    }
  }
}