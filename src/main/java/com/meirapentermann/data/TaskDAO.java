package com.meirapentermann.data;

import java.util.List;
import java.util.Set;

public interface TaskDAO {
	public void addNewTask(Task t);
	public void removeTask(Task t);
	public void reOrderTasks();
	public void editTask(Task t, int p, String n, String d, String c, String l);
	public void editTaskLink(Task t, String l);
	public Task getTaskByID(int n);
	public Task getTaskByItemName(String n);
	public Task getTaskByDescription(String d);
	public Task getTaskByPriority(int p);
	public List<Task> getTasksByCategory(String c);
	public List<Task> getTasks();
	public Set<String> getCategories();
	public boolean deleteCategory(String c);
	public void addCategory(String c);
}
