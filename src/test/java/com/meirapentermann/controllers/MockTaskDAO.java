package com.meirapentermann.controllers;

	import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.meirapentermann.data.Task;
import com.meirapentermann.data.TaskDAO;

	public class MockTaskDAO implements TaskDAO {
		private String fileName = "WEB-INF/tasks.txt";
		private List<Task> tasks;
		private Set<String> categories;
		
		@Autowired
		private WebApplicationContext wc;
		
		public MockTaskDAO() {
			this.tasks = new ArrayList<>();
		}
		
		public void loadTasks() {
			tasks.clear();
//			tasks.add(new Task("Task 1", "Description 1", "", 1, "https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png"));
//			tasks.add(new Task("Task 2", "Description 2", "", 2, "https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png"));
//			tasks.add(new Task("Task 3", "Description 3", "", 3, "https://cdn2.iconfinder.com/data/icons/business-office-icons/256/To-do_List-256.png"));
		}

		public MockTaskDAO (List<Task> tasks) {
			this.tasks = tasks;
		}

		@Override
		public List<Task> getTasks() {
			return tasks;
		}

		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
			this.reOrderTasks();
		}

		@Override
		public Set<String> getCategories() {
			return categories;
		}

		public void setCategories(Set<String> categories) {
			this.categories = categories;
		}

		@Override
		public void addNewTask(Task t) {
			tasks.add(t);
			this.reOrderTasks();
		}

		@Override
		public void removeTask(Task t) {
			for (int i = 0; i < tasks.size(); i++) {
				if (t.getItem().equals(tasks.get(i).getItem())) {
					tasks.remove(i);
				}
			}
			this.reOrderTasks();
		}
		

		@Override
		public void editTaskPriority(Task t, int p) {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getPriority() == p) {
					tasks.get(i).setPriority(p + 1);
					for(int j = i; j < tasks.size(); j++) {
						tasks.get(j).setPriority(tasks.get(j).getPriority() +1);
					}
				}
				if (tasks.get(i).getItem().equals(t.getItem())) {
					tasks.get(i).setPriority(p);
				}
			}
			this.reOrderTasks();
		}

		@Override
		public void editTaskItemName(Task t, String n) {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getItem().equals(t.getItem())) {
					tasks.get(i).setItem(n);
				}
			}
		}

		@Override
		public void editTaskDescritpion(Task t, String d) {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getItem().equals(t.getItem())) {
					tasks.get(i).setDescription(d);
				}
			}
		}

		@Override
		public void editTaskCategory(Task t, String c) {
	
		}
		
		@Override
		public void editTaskLink(Task t, String l) {
			for (int i = 0; i < tasks.size(); i++) {
				if (tasks.get(i).getItem().equals(t.getItem())) {
					tasks.get(i).setImageLink(l);
				}
			}
		}

		@Override
		public void reOrderTasks() {
			Collections.sort(tasks);
		}

		@Override
		public Task getTaskByItemName(String n) {
			n = n.toLowerCase();
			Task answer = null;
			for (int i = 0; i < tasks.size(); i++) {
				String current = tasks.get(i).getItem().toLowerCase();
				if (current.equals(n)) {
					answer = tasks.get(i);
					break;
				}
			}
			return answer;
		}

		@Override
		public Task getTaskByDescription(String d) {
			d = d.toLowerCase();
			Task answer = null;
			for (int i = 0; i < tasks.size(); i++) {
				String current = tasks.get(i).getDescription().toLowerCase();
				if (current.contains(d)) {
					answer = tasks.get(i);
					break;
				}
			}
			return answer;
		}

		@Override
		public List<Task> getTasksByCategory(String c) {
			return null;
		}

		@Override
		public boolean deleteCategory(String c) {
			return false;
		}

		@Override
		public Task getTaskByPriority(int p) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void addCategory(String c) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Task getTaskByID(int n) {
			// TODO Auto-generated method stub
			return null;
		}
	}


