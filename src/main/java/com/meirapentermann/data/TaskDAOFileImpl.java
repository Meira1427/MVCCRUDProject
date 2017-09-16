package com.meirapentermann.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			categories = new HashSet<>();
			while ((line = buf.readLine()) != null) {
				String[] splitList = line.split(" : ");
				String name = splitList[0];
				String descrip = splitList[1];
				String cat = splitList[2];
				int prior = Integer.parseInt(splitList[3]);
				String image = splitList[4];
				tasks.add(new Task(name, descrip, cat, prior, image));
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
		tasks.add(t);
		this.reOrderTasks();
	}

	@Override
	public void removeTask(Task t) {
		for (int i = 0; i < tasks.size(); i++) {
			if (t.getName().equals(tasks.get(i).getName())) {
				tasks.remove(i);
			}
		}
		this.reOrderTasks();
	}
	

	@Override
	public Task editTaskPriority(Task t, int p) {
		t.setPriority(p);
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getPriority() == p) {
				tasks.get(i).setPriority(tasks.get(i).getPriority() + 1);
			}
		}
		this.reOrderTasks();
		return t;
	}

	@Override
	public Task editTaskName(Task t, String n) {
		t.setName(n);
		return t;
	}

	@Override
	public Task editTaskDescritpion(Task t, String d) {
		t.setDescription(d);
		return t;
	}

	@Override
	public Task editTaskCategory(Task t, String c) {
		t.setCategory(c);
		return t;
	}

	@Override
	public void reOrderTasks() {
		System.out.println("entering reorder tasks");
		Collections.sort(tasks);
		System.out.println("exiting reorder tasks");
	}

	@Override
	public Task getTaskByName(String n) {
		Task answer = null;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getName().equals(n)) {
				answer = tasks.get(i);
				break;
			}
		}
		return answer;
	}

	@Override
	public Task getTaskByDescription(String d) {
		Task answer = null;
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getDescription().contains(d)) {
				answer = tasks.get(i);
				break;
			}
		}
		return answer;
	}

	@Override
	public List<Task> getTasksByCategory(String c) {
		List<Task> answer = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			if(tasks.get(i).getCategory().contains(c)) {
				answer.add(tasks.get(i));
			}
		}
		return answer;
	}
}
