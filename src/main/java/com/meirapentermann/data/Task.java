package com.meirapentermann.data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Task implements Comparable<Task> {
	@Size(min=3, max=30)
	private String item;
	
	@Size(min=3, max=100)
	private String description;
	
	@Size(min=3, max=30)
	private String category;
	
	@Min(1)
	@Max(300)
	private int priority;
	
	private String imageLink;
	
	public Task () {
	}
	
	public Task(String item, String description) {
		this();
		this.item = item;
		this.description = description;
		this.category = "Personal";
		this.priority = 1;
		this.imageLink = "http://www.getzcope.com/blog/wp-content/uploads/2009/10/to-do-list.jpg";
	}

	public Task(String item, String description, String category, int priority, String imageLink) {
		this();
		this.item = item;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.imageLink = imageLink;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
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
	public int compareTo(Task t) {
		if (this.getPriority() > t.getPriority()) {
			return 1;
		}
		else if (t.getPriority() > this.getPriority()) {
			return -1;
		}
		else {
			return 0;
		}
	}


}
