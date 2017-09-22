package com.meirapentermann.data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.skilldistillery.film.models.Film;

@Component
public class TaskDAODBImpl implements TaskDAO {
	private static String url = "jdbc:mysql://localhost:3306/sdvid";
	private String user = "user";
	private String pass = "taskuser";
	
	@Autowired
	private WebApplicationContext wc;
	
	public TaskDAODBImpl() {
		  try {
			    Class.forName("com.mysql.jdbc.Driver");
			  } catch (ClassNotFoundException e) {
			    e.printStackTrace();
			    System.err.println("Error loading MySQL Driver!!!");
			  }
	}
	
	@Override
	public List<Task> getTasks() {
		List<Task> answer = new ArrayList<>();
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    String sql = "SELECT id, item, description, category_id, "
		    		+ " priority, image_link "
		    		+ " FROM task ";
		    PreparedStatement stmt = conn.prepareStatement(sql);
//		    String searchString = "";
//		    stmt.setString(1, searchString);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      int cat = rs.getInt(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      Task t = new Task(id, item, desc, cat, priority, link);
		      answer.add(t);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		  return answer;
	}


	@Override
	public Set<String> getCategories() {
		Set<String> answer = new TreeSet<>();
		
		return answer;
	}

	@Override
	public void addNewTask(Task t) {
		
	}

	@Override
	public void removeTask(Task t) {
		
	}
	

	@Override
	public void editTaskPriority(Task t, int p) {

	}

	@Override
	public void editTaskItemName(Task t, String n) {
	
	}

	@Override
	public void editTaskDescritpion(Task t, String d) {
	
	}

	@Override
	public void editTaskCategory(Task t, String c) {
	
	}
	
	@Override
	public void editTaskLink(Task t, String l) {

	}

	@Override
	public void reOrderTasks() {
		//this is not necessary for this implementation
	}

	@Override
	public Task getTaskByItemName(String n) {
		n = n.toLowerCase();
		Task answer = null;
	
		return answer;
	}

	@Override
	public Task getTaskByDescription(String d) {
		d = d.toLowerCase();
		Task answer = null;
	
		return answer;
	}

	@Override
	public List<Task> getTasksByCategory(String c) {
		c = c.toLowerCase();
		List<Task> answer = new ArrayList<>();
	
		return answer;
	}

	@Override
	public boolean deleteCategory(String c) {
	
		return true;
	}
}
