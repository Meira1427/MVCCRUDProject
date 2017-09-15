package com.meirapentermann.data;

import java.util.Comparator;

public class Task implements Comparator<Task> {
	private String name;
	private String description;
	private String category;
	private int priority;
	private String imageLink;
	
	public Task () {
	}
	
	public Task(String name, String description) {
		this();
		this.name = name;
		this.description = description;
		this.category = "Personal";
		this.priority = 1;
		this.imageLink = "http://www.getzcope.com/blog/wp-content/uploads/2009/10/to-do-list.jpg";
	}

	public Task(String name, String description, String category, int priority, String imageLink) {
		this();
		this.name = name;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.imageLink = imageLink;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	@Override
	public int compare(Task o1, Task o2) {
		if (o1.getPriority() > o2.getPriority()) {
			return 1;
		}
		else if (o2.getPriority() > o1.getPriority()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
