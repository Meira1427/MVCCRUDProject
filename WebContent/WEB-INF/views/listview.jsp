<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/stylesheet.css">
<title>My Tasks</title>
</head>
<body>
<h1>My Task List</h1>
<c:set var="count" value="1"></c:set>
<table>
<tr>
	<th></th>
	<th>Priority</th>
	<th>User Prior</th>
	<th>Task</th>
	<th>Description</th>
	<th>Category<th>
	<th></th>
</tr>
<c:forEach var="task" items="${list}">
<tr>
	<th><img src="${task.imageLink}" alt="task" width="50" height="50"></th>
	<th>${count}</th>
	<th>${task.priority}</th>
	<th>${task.name}</th>
	<th>${task.description}</th>
	<th>${task.category}</th>
	<th><form action="edit.do" method="get">
		<input type="submit" name="${task.name}" value="Edit"></input>
		</form></th>
</tr>
<c:set var="count" value="${count+1}"/>
<%-- <c:set var="numberOfRows" value="${numberOfRows+1}"/> --%>
</c:forEach>
</table>
</body>
</html>