package com.meirapentermann.data;

import java.util.List;

public interface TaskDAO {
	public void addNewTask();
	public void removeTask();
	public void reOrderTasks();
	public Task getTaskByName(String n);
	public Task getTaskByDescription(String d);
	public List<Task> getTasksByCategory(String c);

}
