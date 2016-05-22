<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ITEM${item.id}</title>
    <script type="application/javascript" src="/static/jquery-1.12.3.min.js"></script>
    <script type="application/javascript" src="/static/bootstrap-3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
</head>
<body>
<div class="container">
    <h2>${item.title}</h2>

    <div>
        <p>
            ${item.content}
        </p>

        <p>来源：${item.resource}</p>

        <p>爬取时间：${item.time}</p>

        <p>链接：<a target="_blank" href="${item.url}">点击进入</a></p>
    </div>
</div>
</body>
</html>