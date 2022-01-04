<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="css/style.css">

    <title>MAINㅍㅇㅈ</title>
</head>
<body>
<h1>MAIN페이지</h1>
<%
    String logValue=(String)session.getAttribute("log");
    if(logValue != null){
%>
<%=logValue%>님 ${message}
<form action="logoutPro" method="get">
    <input type="submit" value="logout">
</form>

<%
    }else{
%>
<div id="btn">
    <input type="button" id="join" value="join" onclick="location.href='/join'">
    <input type="button" id="login" value="login" onclick="location.href='/login'">
    <input type="button" id="userMyPage" value="userMyPage" onclick="location.href='/userMyPage'">
</div>

<%
    }
%>
</body>
</html>