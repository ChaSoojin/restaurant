<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="script/review.js"></script>
    <title>Review Write Page</title>
</head>
<body>
<h1>리뷰 작성</h1>
<form method="post" action="/write">
    <span>제목: </span><input type="text" id="title" name="title" required><br><br>
    <textarea name="content" id="content" rows="30" cols="100" placeholder="새로운 글을 작성하세요!" required></textarea><br><br>
    <input type="submit" value="제출하기">
    <input type="button" value="뒤로가기" onclick="location.href='reviewList'">
</form>
</body>
</html>