<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/stylesheet.css">
<title>Edit Task</title>
</head>
<body>
<h2>Edit Task</h2>
<form:form>
	<form:input path="name" name="name" value="${task.name}"></form:input>
	<form:input path="description" name="description" value="${task.description}"></form:input>
	<form:input path="category" name="category" value="${task.category}"></form:input>
	<form:input path="priority" name="priority" value="${task.priority}"></form:input>
	<form:input path="imageLink" name="imageLink" value="${task.imageLink}"></form:input>
</form:form>
</body>
</html>