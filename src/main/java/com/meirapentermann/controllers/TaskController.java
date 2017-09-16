package com.meirapentermann.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	@RequestMapping(path = "edit.do",
			method=RequestMethod.GET) 
	public ModelAndView goToEditTask(@RequestParam(value="Edit") String e, String name) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("taskview");
		Task t = new Task();
		mv.addObject("task", t);
		return mv;
	}

}
