<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>INFO CRAWLER</h1>
	<a href="/startCrawler">开始爬取</a>
	<a href="/item/list">查看结果</a>

	<%--<c:if test="${finished }">--%>
		<a href="/redis2DB">将结果存入数据库</a>
	<%--</c:if>--%>

	<h2>${total }</h2>
	<h2>${saved }</h2>
</body>

<script>

</script>
</html>