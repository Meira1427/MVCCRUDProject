package com.meirapentermann.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class TaskDAOFileImpl implements TaskDAO {
	private String fileName = "WEB-INF/tasks.txt";
	private List<Task> tasks;
	private Set<String> categories;
	
	@Autowired
	private WebApplicationContext wc;
	
	@PostConstruct
	public void init() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (InputStream is = wc.getServletContext().getResourceAsStream(fileName);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line;
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
	
	public TaskDAOFileImpl(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void setCategories(Set<String> categories) {
		this.categories = categories;
	}

	@Override
	public void addNewTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reOrderTasks() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Task getTaskByName(String n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Task getTaskByDescription(String d) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasksByCategory(String c) {
		return this.tasks;
	}

}
