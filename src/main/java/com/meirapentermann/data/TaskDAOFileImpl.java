package com.meirapentermann.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class TaskDAOFileImpl implements TaskDAO {
	private String fileName = "WEB-INF/tasks.txt";
	private List<Task> tasks;
	private Set<String> categories;
	
	@Autowired
	private WebApplicationContext wc;
	
	@PostConstruct
	public void init() { 
		try (InputStream is = wc.getServletContext().getResourceAsStream(fileName);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line;
			tasks = new ArrayList<>();
			categories = new TreeSet<>();
			while ((line = buf.readLine()) != null) {
				String[] splitList = line.split(" : ");
				String item = splitList[0];
				String descrip = splitList[1];
				String cat = splitList[2];
				int cat_id = 4;
				int prior = Integer.parseInt(splitList[3]);
				String image = splitList[4];
				tasks.add(new Task(item, descrip, cat_id, prior, image));
				categories.add(cat);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public TaskDAOFileImpl() {
		this.tasks = new ArrayList<>();
	}
	
	public TaskDAOFileImpl(List<Task> tasks) {
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
		//t.setCategory(t.getCategory().toLowerCase());
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getPriority() == t.getPriority()) {
				for(int j = i; j < tasks.size(); j++) {
					tasks.get(j).setPriority((tasks.get(j).getPriority()+1));
				}
			}
		}
		tasks.add(t);
		//categories.add(t.getCategory());
		this.reOrderTasks();
	}

	@Override
	public void removeTask(Task t) {
		boolean sharedcat = false;
		for (int i = 0; i < tasks.size(); i++) {
			if (t.getItem().equals(tasks.get(i).getItem())) {
				tasks.remove(i);
			}
			else if (t.getCategory().equals(tasks.get(i).getCategory())) {
				sharedcat = true;
			}
		}
		if(!sharedcat) {
			Iterator<String> i = categories.iterator();
			while(i.hasNext()){
				String cat = i.next();
				if(t.getCategory().equals(cat)) {
					categories.remove(cat);
					break;
				}
			}
		}
		this.reOrderTasks();
	}
	

	@Override
	public void editTaskPriority(Task t, int p) {
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getPriority() == p) {
				for(int j = i; j < tasks.size(); j++) {
					tasks.get(j).setPriority((tasks.get(j).getPriority()+1));
					//System.out.println(tasks.get(j).getItem() + " new priority " + tasks.get(j).getPriority());
				}
	
			}
			if (tasks.get(i).getItem().equals(t.getItem())) {
				tasks.get(i).setPriority(p);
				//System.out.println(tasks.get(i).getItem() + " new priority " + tasks.get(i).getPriority());
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
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getItem().equals(t.getItem())) {
				tasks.get(i).setCategory(c);
			}
		}
		categories.add(c);
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
		for (int i = 0; i < tasks.size(); i++) {
			tasks.get(i).setPriority(i+1);
		}
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
		c = c.toLowerCase();
		List<Task> answer = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			String current = tasks.get(i).getCategory().toLowerCase();
			if(current.contains(c)) {
				answer.add(tasks.get(i));
			}
		}
		return answer;
	}

	@Override
	public boolean deleteCategory(String c) {
		for (int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getCategory().equals(c)) {
				return false;
			}
		}
		Iterator<String> i = categories.iterator();
		while(i.hasNext()){
			String cat = i.next();
			if(cat.equals(c)) {
				categories.remove(cat);
				break;
			}
		}
		return true;
	}
}
