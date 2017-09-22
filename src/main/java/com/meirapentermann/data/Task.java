package com.meirapentermann.data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Task implements Comparable<Task> {
	private int id;
	
	@Size(min=3, max=30)
	private String item;
	
	@Size(min=3, max=100)
	private String description;
	
	@Min(1)
	private int category_id;
	
	@Min(1)
	@Max(300)
	private int priority;
	
	private String imageLink;
	
	public Task () {
	}
	
	public Task(int id, String item, String description, int category, int priority, String imageLink) {
		this();
		this.id = id;
		this.item = item;
		this.description = description;
		this.category_id = category;
		this.priority = priority;
		this.imageLink = imageLink;
	}

	public Task(String item, String description, int category, int priority, String imageLink) {
		this();
		this.item = item;
		this.description = description;
		this.category_id = category;
		this.priority = priority;
		this.imageLink = imageLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getCategoryID() {
		return category_id;
	}

	public void setCategoryID(int category) {
		this.category_id = category;
	}
	
	public String getCategory() {
		return "lookup";
	}

	public void setCategory(String cat) {
		//lookup in table
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
