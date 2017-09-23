package com.meirapentermann.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Component;

@Component
public class TaskDAODBImpl implements TaskDAO {
	private static String url = "jdbc:mysql://localhost:3306/taskdb";
	private String user = "user";
	private String pass = "taskuser";

	public TaskDAODBImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
			System.err.println("In new DAO DB IMPL");
		}
	}

	@Override
	public List<Task> getTasks() {
		List<Task> answer = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT t.id, t.item, t.description, c.name, " + 
			" t.priority, t.image_link " +
			" FROM task t join category c on t.category_id = c.id " + 
			" ORDER BY priority;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String item = rs.getString(2);
				String desc = rs.getString(3);
				String cat = rs.getString(4);
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
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name FROM category ORDER BY name;";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				answer.add(name);
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
	public void addNewTask(Task t) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO task(item, description, " + 
			" category_id, priority, image_link) " +
			" VALUES(?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getItem());
			stmt.setString(2, t.getDescription());
			System.out.println("Cat Name "+ t.getCategory());
			String catName = t.getCategory();
			int catNum = getCategoryNumber(catName);
			System.out.println("Cat Num " + catNum);
			stmt.setInt(3, catNum);
			stmt.setInt(4, t.getPriority());
			stmt.setString(5, t.getImageLink());
			System.out.println("JustBefore executeUpdate()");
			int updateCount = stmt.executeUpdate();
			System.out.println("updateCount " + updateCount);
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newId = keys.getInt(1);
					t.setId(newId);
				}
			}
			else {
				System.out.println("Update Count Didn't Work");
			}
			conn.commit(); // COMMIT TRANSACTION
			conn.close();
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting task " + t);
		}
	}

	@Override
	public void removeTask(Task t) {
		Connection conn = null;
		  try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false); // START TRANSACTION
		    String sql = "DELETE FROM task WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, t.getId());
		    int updateCount = stmt.executeUpdate();
		    //System.out.println("updateCount = " + updateCount);
		    conn.commit();             // COMMIT TRANSACTION
		    conn.close();
		    stmt.close();
		  }
		  catch (SQLException sqle) {
		    sqle.printStackTrace();
		    if (conn != null) {
		      try { conn.rollback(); }
		      catch (SQLException sqle2) {
		        System.err.println("Error trying to rollback");
		      }
		    }
		  }
	}

	@Override
	public void editTaskPriority(Task t, int p) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set priority=? "
		    		+ " WHERE id = ?";
		    //System.out.println(t.getItem() + " w/ ID: " + t.getId() + " Priority: " + p);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    if(t.getPriority() <= p) {
		    		stmt.setInt(1, (p));
			}
			else {
				stmt.setInt(1, (p-1));
			}
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}
	
	public void editTaskForRenumber(Task t, int p) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set priority=? "
		    		+ " WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, p);
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}

	@Override
	public void editTaskItemName(Task t, String n) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set item=? "
		    		+ " WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, n);
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}

	@Override
	public void editTaskDescritpion(Task t, String d) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set description=? "
		    		+ " WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, d);
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}

	@Override
	public void editTaskCategory(Task t, String c) {
		Connection conn = null;
		int catNum = getCategoryNumber(c);
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set category_id=? "
		    		+ " WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, catNum);
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}

	@Override
	public void editTaskLink(Task t, String l) {
		Connection conn = null;
		try {
		    conn = DriverManager.getConnection(url, user, pass);
		    conn.setAutoCommit(false);
		    String sql = "UPDATE task set image_link=? "
		    		+ " WHERE id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setString(1, l);
		    stmt.setInt(2, t.getId());
		    int updateCount = stmt.executeUpdate();
            if (updateCount == 1) {
                conn.commit();
            }
            stmt.close();
            conn.close();
		  } catch (SQLException e) {
			    e.printStackTrace();
	            if (conn != null) {
	                try {
	                    conn.rollback();
	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	            }
		  }
	}

	@Override
	public void reOrderTasks() {
		List<Task> tasks = getTasks();
		for (int i = 0; i < tasks.size(); i++) {
			editTaskForRenumber(tasks.get(i), (i+1));
		}
	}

	@Override
	public Task getTaskByItemName(String n) {
		Task task = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    	String sql = "SELECT t.id, t.item, t.description, c.name, " + 
		    				" t.priority, t.image_link " +
		    				" FROM task t join category c on t.category_id = c.id " +
		    				" WHERE t.item LIKE ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    String searchString = "%" + n + "%";
		    stmt.setString(1, searchString);
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      String cat = rs.getString(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      task = new Task(id, item, desc, cat, priority, link);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return task;
	}
	
	public Task getTaskByID(int n) {
		Task task = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    	String sql = "SELECT t.id, t.item, t.description, c.name, " + 
		    				" t.priority, t.image_link " +
		    				" FROM task t join category c on t.category_id = c.id " +
		    				" WHERE t.id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, n);
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      String cat = rs.getString(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      task = new Task(id, item, desc, cat, priority, link);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return task;
	}

	@Override
	public Task getTaskByDescription(String d) {
		Task task = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    	String sql = "SELECT t.id, t.item, t.description, c.name, " + 
		    				" t.priority, t.image_link " +
		    				" FROM task t join category c on t.category_id = c.id " +
		    				" WHERE t.description LIKE ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    String searchString = "%" + d + "%";
		    stmt.setString(1, searchString);
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      String cat = rs.getString(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      task = new Task(id, item, desc, cat, priority, link);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return task;
	}

	@Override
	public List<Task> getTasksByCategory(String c) {
		List<Task> tasks = new ArrayList<>();
		Task task = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    	String sql = "SELECT t.id, t.item, t.description, c.name, " + 
		    				" t.priority, t.image_link " +
		    				" FROM task t join category c on t.category_id = c.id " +
		    				" WHERE c.name LIKE ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    String searchString = "%" + c + "%";
		    stmt.setString(1, searchString);
		    ResultSet rs = stmt.executeQuery();
		    while (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      String cat = rs.getString(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      task = new Task(id, item, desc, cat, priority, link);
		      tasks.add(task);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return tasks;
	}
	
	@Override
	public Task getTaskByPriority(int p) {
		Task task = null;
		  try {
		    Connection conn = DriverManager.getConnection(url, user, pass);
		    	String sql = "SELECT t.id, t.item, t.description, c.name, " + 
		    				" t.priority, t.image_link " +
		    				" FROM task t join category c on t.category_id = c.id " +
		    				" WHERE t.id = ?";
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    stmt.setInt(1, p);
		    ResultSet rs = stmt.executeQuery();
		    if (rs.next()) {
		      int id = rs.getInt(1);
		      String item = rs.getString(2);
		      String desc = rs.getString(3);
		      String cat = rs.getString(4);
		      int priority = rs.getInt(5);
		      String link = rs.getString(6);
		      task = new Task(id, item, desc, cat, priority, link);
		    }
		    rs.close();
		    stmt.close();
		    conn.close();
		  } catch (SQLException e) {
		    e.printStackTrace();
		  }
		return task;
	}

	@Override
	public boolean deleteCategory(String c) {
		boolean canDelete=true;
		int id = 0;
		List<Task> test = getTasksByCategory(c);
		System.out.println("Delete category test task list size " + test.size());
		if(test.size()==0) {
			Connection conn = null;
			try {
				conn = DriverManager.getConnection(url, user, pass);
				conn.setAutoCommit(false); // START TRANSACTION
			    	String sql = "SELECT id from category " +
			    				" WHERE name = ?";
			    PreparedStatement stmt = conn.prepareStatement(sql);
			    stmt.setString(1, c);
			    ResultSet rs = stmt.executeQuery();
			    if (rs.next()) {
			      id = rs.getInt(1);
			    }
			    System.out.println("ID is " + id);
			    sql = "Delete from category where id = ?";
			    stmt = conn.prepareStatement(sql);
			    stmt.setInt(1, id);
			    int updateCount = stmt.executeUpdate();
			    conn.commit();             // COMMIT TRANSACTION
			    rs.close();
			    stmt.close();
			    conn.close();
			    if(updateCount==0) {
			    	   canDelete=false;
			    }
			}
			catch (SQLException sqle) {
				    sqle.printStackTrace();
				    if (conn != null) {
				      try { conn.rollback(); }
				      catch (SQLException sqle2) {
				        System.err.println("Error trying to rollback");
				      }
				    }
				  }
		}
		else {
			canDelete=false;
		}
		return canDelete;  
	}
	
	@Override
	public void addCategory(String c) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO category(name) " +
			" VALUES(?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, c);
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newId = keys.getInt(1);
				}
			}
			else {
				System.out.println("Add Category Didn't Work");
			}
			conn.commit(); // COMMIT TRANSACTION
			conn.close();
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting category " + c);
		}
		
	}

	public int getCategoryNumber(String c) {
		int answer = 1;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "Select id from category where name=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, c);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				answer = rs.getInt(1);
			}
			conn.close();
			stmt.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return answer;
	}

}
