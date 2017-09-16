package com.meirapentermann.controllers;

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
		mv.setViewName("taskview");
		Task t = dao.getTaskByItemName(item);
		mv.addObject("task", t);
		return mv;
	}
	
	@RequestMapping(path = "save.do",
			method=RequestMethod.POST) 
	public ModelAndView saveChangesTask(@Valid Task t, Errors errors, String item, String description,
			String category, int priority, String imageLink) {
		ModelAndView mv = new ModelAndView();
//		System.out.println("Errors = " + errors.getErrorCount());
//		System.out.println(errors.getAllErrors());
		if (errors.getErrorCount() == 0) {
			if(dao.getTaskByItemName(t.getItem()) == null) {
				dao.addNewTask(t);
			}
			else {
				dao.editTaskItemName(t, item);
				dao.editTaskDescritpion(t, description);
				dao.editTaskCategory(t, category);
				dao.editTaskPriority(t, priority);
				dao.editTaskLink(t, imageLink);
			}
			if(imageLink == "") {
				dao.editTaskLink(t, "https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png");
			}
			dao.reOrderTasks();
			mv.addObject("list", dao.getTasks());
			mv.setViewName("listview");
		}
		else {
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
	
}

