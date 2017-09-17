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
       <h1 class="myTitle">Categories</h1>
       <div id="navbar">
           <ul>
               <li class="colorButton"><a href="new.do">New Task</a></li>
               <li class="colorButton"><a href="cat.do">Categories</a></li>
               <li class="colorButton"><a href="home.do">Task List</a></li>
           </ul>
       </div>
   </div>
   <div id="hidden_header">
       <h1 class="myTitle">Categories</h1>
       <div id="navbar">
           <ul>
               <li class="colorButton"><a href="#">Choice 3</a></li>
               <li class="colorButton"><a href="#">Categories</a></li>
               <li class="colorButton"><a href="#">Home</a></li>
           </ul>
       </div>
   </div>
   <div class="main">
   	<h2>Categories</h2>
   	 <div class="cats">
	   <form:form action="newcat.do" method="post" modelAttribute="task">
			<c:forEach var="cat" items="${cats}">
				${cat}<br>
			</c:forEach>
			<input type="text" name="newcat"></input>
			<input type="submit" name="submit" value="Submit New Category"></input>
		</form:form>
	</div>
  </div>
</body>
</html>