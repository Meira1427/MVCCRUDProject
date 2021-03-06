package com.meirapentermann.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.meirapentermann.data.Task;
import com.meirapentermann.data.TaskDAO;

@Controller
public class TaskController {
	@Autowired
	TaskDAO dao;
	
	public TaskController() {
	}

	public TaskDAO getDao() {
		return dao;
	}

	public void setDao(TaskDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping(path = "home.do",
			method=RequestMethod.GET) 
	public ModelAndView displayList() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("listview");
		dao.reOrderTasks();
		mv.addObject("list", dao.getTasks());
		return mv;
	}
	
	@RequestMapping(path = "select.do",
			method=RequestMethod.GET) 
	public ModelAndView goToEditTask(@RequestParam("item") String item) {
		ModelAndView mv = new ModelAndView();
		Task t = dao.getTaskByItemName(item);
		mv.addObject("curcat", t.getCategory());
		mv.addObject("cats", dao.getCategories());
		mv.addObject("task", t);
		mv.setViewName("taskview");
		return mv;
	}
	
	@RequestMapping(path = "save.do",
			method=RequestMethod.POST) 
	public ModelAndView saveChangesTask(@Valid Task t, Errors errors, String item, String description,
			String category, int priority, String imageLink) {
		ModelAndView mv = new ModelAndView();
		if (errors.getErrorCount() == 0) {
			if(t.getId() == 0) {
				dao.addNewTask(t);
			}
			else {
				dao.editTask(t, priority, item, description, category, imageLink);
			}
			if(imageLink == "") {
				dao.editTaskLink(t, "https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png");
			}
			dao.reOrderTasks();
			mv.addObject("list", dao.getTasks());
			mv.setViewName("listview");
		}
		else {
			mv.addObject("cats", dao.getCategories());
			mv.setViewName("taskview");
		}
		return mv;
	}
	
	@RequestMapping(path = "new.do",
			method=RequestMethod.GET) 
	public ModelAndView goToNewTask() {
		ModelAndView mv = new ModelAndView();
		Task t = new Task();
		mv.setViewName("taskview");
		mv.addObject("cats", dao.getCategories());
		mv.addObject("task", t);
		return mv;
	}
	
	@RequestMapping(path = "cat.do",
			method=RequestMethod.GET) 
	public ModelAndView goToCategories() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("catview");
		mv.addObject("cats", dao.getCategories());
		return mv;
	}
	
	
	@RequestMapping(path = "newcat.do",
			params="newcat",
			method=RequestMethod.POST) 
	public ModelAndView AddNewCategories(@RequestParam("newcat") String n) {
		ModelAndView mv = new ModelAndView();
		if(n.length()>1) {
			n = n.toLowerCase();
			dao.addCategory(n);
		}
		mv.addObject("cats", dao.getCategories());
		mv.setViewName("catview");
		return mv;
	}
	
	@RequestMapping(path = "newcat.do",
			method=RequestMethod.GET) 
	public ModelAndView AddNewCategoryLinking() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("catview");
		mv.addObject("cats", dao.getCategories());
		return mv;
	}
	
	@RequestMapping(path = "delete.do",
			params="item",
			method=RequestMethod.GET) 
	public ModelAndView DeleteATask(@RequestParam("item") String item) {
		ModelAndView mv = new ModelAndView();
		Task t = dao.getTaskByItemName(item);
		dao.removeTask(t);
		dao.reOrderTasks();
		mv.addObject("list", dao.getTasks());
		mv.setViewName("listview");
		return mv;
	}
	
	@RequestMapping(path = "deletecat.do",
			method=RequestMethod.POST) 
	public ModelAndView DeleteACat(@RequestParam("delcat") String cat) {
		ModelAndView mv = new ModelAndView();
		cat = cat.toLowerCase();
		boolean deleteOkay = dao.deleteCategory(cat);
		if(deleteOkay) {
			mv.addObject("cats", dao.getCategories());
			mv.setViewName("catview");
		}
		else {
			mv.setViewName("error");
		}
		return mv;
	}
	
}

