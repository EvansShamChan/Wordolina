<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 10.03.2018
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Wordolina</title>
    <style>
        <%@include file="../css/mainPageStyle.css"%>
    </style>
</head>
<body>
<p align="center"><a class="logo" href="<s:url value="/main"/>">Wordolina</a></p>
<div class="mainDiv">
    <p align="center"><span class="true">Правильно:</span> ${score} <span class="false">:Неправильно</span></p>
    <p align="center">${result}</p>
    <p align="center">${realAnswer}</p>
    <p align="center"><a href="<s:url value="/main"/>">Наступний</a></p><br>
    <div align="center">
        <form action="<s:url value="/"/>">
            <input type="submit" value="Обнулити рахунок">
        </form>
    </div>
</div>
</body>
</html>
