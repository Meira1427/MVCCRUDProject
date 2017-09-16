package com.meirapentermann.controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.meirapentermann.data.Task;
import com.meirapentermann.data.TaskDAO;

public class MockTaskDAO implements TaskDAO {

	List<Task> mockTasks;
	Set<String> mockCategories = new HashSet<>();
	
	public MockTaskDAO() {
		mockTasks = new ArrayList<>();
		loadTasks();
	}

	public void loadTasks(){
		mockTasks.clear();
		mockTasks.add(new Task("Test 1", "My Test Class 1", "household", 2, "http://www.getzcope.com/blog/wp-content/uploads/2009/10/to-do-list.jpg"));
		mockTasks.add(new Task("Test 2", "My Test Class 2", "school", 1, "http://www.getzcope.com/blog/wp-content/uploads/2009/10/to-do-list.jpg"));
		mockTasks.add(new Task("Test 3", "My Test Class 3", "household", 2, "http://www.getzcope.com/blog/wp-content/uploads/2009/10/to-do-list.jpg"));
	}

	@Override
	public void addNewTask(Task t) {
		mockTasks.add(t);
		this.reOrderTasks();
	}

	@Override
	public void removeTask(Task t) {
		for (int i = 0; i < mockTasks.size(); i++) {
			if (t.getName().equals(mockTasks.get(i).getName())) {
				mockTasks.remove(i);
			}
		}
		this.reOrderTasks();
	}

	@Override
	public void reOrderTasks() {
		Collections.sort(mockTasks);
	}

	@Override
	public Task getTaskByName(String n) {
		Task answer = null;
		for (int i = 0; i < mockTasks.size(); i++) {
			if (mockTasks.get(i).getName().equals(n)) {
				answer = mockTasks.get(i);
				break;
			}
		}
		return answer;
	}

	@Override
	public Task getTaskByDescription(String d) {
		Task answer = null;
		for (int i = 0; i < mockTasks.size(); i++) {
			if (mockTasks.get(i).getDescription().contains(d)) {
				answer = mockTasks.get(i);
				break;
			}
		}
		return answer;
	}

	@Override
	public List<Task> getTasksByCategory(String c) {
		List<Task> answer = new ArrayList<>();
		for (int i = 0; i < mockTasks.size(); i++) {
			if(mockTasks.get(i).getCategory().contains(c)) {
				answer.add(mockTasks.get(i));
			}
		}
		return answer;
	}

	public List<Task> getTasks() {
		return mockTasks;
	}

	public void setTasks(List<Task> mockTasks) {
		this.mockTasks = mockTasks;
	}

	public Set<String> getCategories() {
		return mockCategories;
	}

	public void setCategories(Set<String> mockCategories) {
		this.mockCategories = mockCategories;
	}

	@Override
	public Task editTaskPriority(Task t, int p) {
		t.setPriority(p);
		for (int i = 0; i < mockTasks.size(); i++) {
			if (mockTasks.get(i).getPriority() == p) {
				mockTasks.get(i).setPriority(mockTasks.get(i).getPriority() + 1);
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


}
