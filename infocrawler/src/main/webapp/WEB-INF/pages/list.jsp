<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List</title>

    <script type="application/javascript" src="/static/jquery-1.12.3.min.js"></script>
    <script type="application/javascript" src="/static/bootstrap-3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/static/bootstrap-3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="/static/css/main.css"/>
    <script type="application/javascript">
        $(function () {

            // 文档就绪
            $("#selectTime").val("${time }");
            $("#selectResource").val("${resource }");
            $("#queryWhich").val("${queryWhich }");
            $("#keyword").val("${keyword }");

            $("#selectTime").change(function () {
                $("#queryForm").submit();
                //alert($("#select").val());
            });
            $("#selectResource").change(function () {
                $("#queryForm").submit();
                //alert($("#select").val());
            });

        });
    </script>
</head>
<body>
<div style="margin-top:20px;"></div>
<div class="container">
    <div class="form-group">
        <form id="queryForm" action="/item/list" method="get">
            <a href="/" alt="返回首页"><span class="glyphicon glyphicon-home" aria-hidden="true"></span></a>
            <label>爬取时间（yyyy-mm-dd-HH）</label>
            <select class="my-form-default" id="selectTime" name="time">
                <option value="">请选择</option>
                <c:forEach var="t" items="${times }">
                    <option value="${t }">${t }</option>
                </c:forEach>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <label>爬取来源</label>
            <select class="my-form-default" id="selectResource" name="resource">
                <option value="">请选择</option>
                <c:forEach var="r" items="${resources }">
                    <option value="${r }">${r }</option>
                </c:forEach>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <select class="my-form-default" id="queryWhich" name="queryWhich">
                <option value="title">标题</option>
                <option value="content">全文</option>
            </select>
            <input class="my-form-default" id="keyword" type="text" name="keyword"/>
            <input class="btn btn-default" type="submit" value="搜索"/>
            <a class="btn btn-default pull-right" href="list?curPage=1">显示全部</a>
        </form>
    </div>
    <table class="table table-hover">

        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Resource</th>
            <th>URL</th>
            <th>Time</th>
            <th></th>
        </tr>
        <c:forEach var="item" items="${itemList }">
            <tr>
                <td>${item.id }</td>
                <td>${item.title }</td>
                <td>${item.resource }</td>
                <td><a target="_blank" href="${item.url}">${item.url}</a></td>
                <td>${item.time}</td>
                <td><a target="_blank" href="detail/${item.id }">查看</a></td>
            </tr>
        </c:forEach>

    </table>
    <ul class="pager">
        <li><a href="list?curPage=1&keyword=${keyword }&time=${time }&resource=${resource }">首页</a></li>
        <li><a href="list?curPage=${page.currentPage-1 }&keyword=${keyword }&time=${time }&resource=${resource }"><span
                class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></a>
        <li>
        <li><a href="list?curPage=${page.currentPage+1 }&keyword=${keyword }&time=${time }&resource=${resource }"><span
                class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span></a>
        <li>
        <li><a href="list?curPage=${page.totalPage }&keyword=${keyword }&time=${time }&resource=${resource }">尾页</a>
        <li>
    </ul>
    <label class="pull-right">第${page.currentPage}页/共${page.totalPage }页</label>
    <br/>

    <form action="/item/export" method="post">
        <input type="hidden" value="${keyword }" name="exportKeyword"/>
        <input type="hidden" value="${time }" name="exportTime"/>
        <input type="hidden" value="${resource }" name="exportResource"/>
        <input type="hidden" value="${queryWhich }" name="exportWhich"/>
        <input class="btn btn-info" type="submit" value="export"/>
    </form>
</div>
</body>
</html>