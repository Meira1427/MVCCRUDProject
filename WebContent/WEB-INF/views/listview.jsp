<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/stylesheet.css">
<title>My Tasks</title>
</head>
<body>
   <div class="header">
       <h1 class="myTitle">My Task List</h1>
       <div id="navbar">
           <ul>
               <li class="colorButton"><a href="new.do">New Task</a></li>
               <li class="colorButton"><a href="cat.do">Categories</a></li>
               <li class="colorButton"><a href="home.do">Task List</a></li>
           </ul>
       </div>
   </div>
   <div id="hidden_header">
       <h1 class="myTitle">My Task List</h1>
       <div id="navbar">
           <ul>
               <li class="colorButton"><a href="#">Choice 3</a></li>
               <li class="colorButton"><a href="#">Categories</a></li>
               <li class="colorButton"><a href="#">Home</a></li>
           </ul>
       </div>
   </div>
   <div class="main">
	<c:set var="count" value="1"></c:set>
		<table>
		<tr>
			<th></th>
			<th>Priority</th>
			<th>Task</th>
			<th>Description</th>
			<th>Category<th>
			<th></th>
		</tr>
		<c:forEach var="task" items="${list}">
		<tr>
			<td><a href="select.do?item=${task.item}"><img src="${task.imageLink}" alt="task" width="50" height="50"></a></td>
			<td>${task.priority}</td>
			<td>${task.item}</td>
			<td>${task.description}</td>
			<td>${task.category}</td>
			<td><a href="select.do?item=${task.item}"><input type="submit" value="Select"/></a></td>
		</tr>
		<c:set var="count" value="${count+1}"/>
		</c:forEach>
		</table>
		<form action="new.do" method="get">
			<input type="submit" name="new" value="New Task">
		</form>
	</div>
	</body>
</html>