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
       <h1 class="myTitle">My Task View</h1>
       <div id="navbar">
           <ul>
               <li class="colorButton"><a href="#">Choice 3</a></li>
               <li class="colorButton"><a href="cat.do">Categories</a></li>
               <li class="colorButton"><a href="home.do">Home</a></li>
           </ul>
       </div>
   </div>
   <div id="hidden_header">
       <h1 class="myTitle">My Task View</h1>
       <div id="navbar">
           <ul>
          	   <li class="colorButton"><a href="#">Choice 3</a></li>
               <li class="colorButton"><a href="#">Categories</a></li>
               <li class="colorButton"><a href="#">Home</a></li>
           </ul>
       </div>
   </div>
   <div class="main">
   <form:form action="save.do" method="post" modelAttribute="task">
		Item: <form:input path="item" name="item" value="${task.item}"></form:input><br>
		Description: <form:input path="description" name="description" value="${task.description}"></form:input><br>
		Category: <form:input path="category" name="category" value="${task.category}"></form:input><br>
		Priority: <form:input path="priority" name="priority" value="${task.priority}"></form:input><br>
		Image Link: <form:input path="imageLink" name="imageLink" value="${task.imageLink}"></form:input><br>
		<input type="submit" name="submit" value="Save"></input>
	</form:form>
  </div>
</body>
</html>