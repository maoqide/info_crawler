<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <script type="application/javascript" src="/static/jquery-1.12.3.min.js"></script>
    <script type="application/javascript" src="/static/bootstrap-3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
</head>
<body>
<div class="container" style="padding-top:13%;">
    <h1 class="text-center"><strong>Tech crawler</strong></h1>

    <div class="text-center" style="margin-top:40px;">
        <c:if test="${!finished }">
        <a class= "btn btn-default btn-lg" href="/startCrawler">开始爬取</a>
        </c:if>
        <a class= "btn btn-default btn-lg" href="/item/list">查看结果</a>

        <c:if test="${finished }">
        <a class= "btn btn-default btn-lg" href="/redis2DB">将结果存入数据库</a>
        </c:if>

        <h2>${total }</h2>

        <h2>${saved }</h2>
    </div>
</div>

</body>

</html>