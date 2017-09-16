package com.meirapentermann.data;

import java.util.List;
import java.util.Set;

public interface TaskDAO {
	public void addNewTask(Task t);
	public void removeTask(Task t);
	public void reOrderTasks();
	public Task editTaskPriority(Task t, int p);
	public Task editTaskName(Task t, String n);
	public Task editTaskDescritpion(Task t, String d);
	public Task editTaskCategory(Task t, String c);
	public Task getTaskByName(String n);
	public Task getTaskByDescription(String d);
	public List<Task> getTasksByCategory(String c);
	public List<Task> getTasks();
	public Set<String> getCategories();
}
