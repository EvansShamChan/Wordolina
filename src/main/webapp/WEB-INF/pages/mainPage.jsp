<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Вова
  Date: 08.03.2018
  Time: 16:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Wordolina</title>
    <style>
        <%@include file="../css/mainPageStyle.css"%>
    </style>
</head>
<body>
    <p align="center"><a class="logo" href="<s:url value="/main"/>">Wordolina</a></p>
    <div class="mainDiv">
        <p align="center"><span class="true">Правильно:</span> ${score} <span class="false">:Неправильно</span></p>
        <form method="post" onsubmit="answerNotEntered(this)">
            <p>Перекласти: <input type="text" name="word" value="${word}"><br>
            <input type="radio" name="answerTranslate" id="answer0" value="${translate0}" checked>
                <label for="answer0">${translate0}</label><br>
            <input type="radio" name="answerTranslate" id="answer1" value="${translate1}">
                <label for="answer1">${translate1}</label><br>
            <input type="radio" name="answerTranslate" id="answer2" value="${translate2}">
                <label for="answer2">${translate2}</label><br>
            <input type="radio" name="answerTranslate" id="answer3" value="${translate3}">
                <label for="answer3">${translate3}</label><br>
            <input type="submit" value="OK">
        </form><br>
        <p class="helpText">//Натисніть на логотип Wordolina щоб змінити слово</p>
        <div align="center">
            <form action="<s:url value="/"/>">
                <input type="submit" value="Обнулити рахунок">
            </form>
        </div>
    </div>
</body>
</html>
