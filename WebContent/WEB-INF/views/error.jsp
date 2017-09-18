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
       <a href="new.do" class="colorButton"><input type="submit" value="New Task"></input></a>
       <a href="cat.do" class="colorButton"><input type="submit" value="Categories"></input></a>
       <a href="home.do" class="colorButton"><input type="submit" value="Task List"></input></a>
       </div>
   </div>
   <div id="hidden_header">
       <h1 class="myTitle">Categories</h1>
      <div id="navbar">
       <a href="new.do" class="colorButton"><input type="submit" value="New Task"></input></a>
       <a href="cat.do" class="colorButton"><input type="submit" value="Categories"></input></a>
       <a href="home.do" class="colorButton"><input type="submit" value="Task List"></input></a>
       </div>
   </div>
   <div class="main">
   	<h2>Error</h2>
   	<h5>You cannot delete a category that is in use.</h5>
  </div>
</body>
</html>