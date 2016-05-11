<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Add User</title>
</head>
<body>
<form action="add" method="post">

  Title:<input id="title" name="title" type="text" />
  <br>
  Resource:<input id="resource" name="resource" type="text" />
  <br>
  Content:<input id="content" name="content" type="text" />
  <br>
  Time:<input id="time" name="time" type="text" />
  <br>
  Url:<input id="url" name="url" type="text" />
  <br>
  <input type="submit" value="提交">
</form>
</body>
</html>
