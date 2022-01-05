<%@ page import="org.springframework.web.context.request.SessionScope" %>
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
    <title>Review List</title>
</head>
<body>
<input type="button" value="리뷰작성" onclick="location.href='writePage'">
<h1>리뷰 게시판</h1>

<div class="wrap">
    <table border="1" width="100%">
        <thead>
        <tr>
            <th>No</th>
            <th>Title</th>
            <th>ID</th>
            <th>Like</th>
            <th>View</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="review" items="${list}">

            <%--                <tr method="post" onclick="location.href='/v1/review/{<c:out value="${review.getNo()}"/>}'">--%>
            <tr method="post" onclick="location.href='reviewView?no=<c:out value="${review.getNo()}"/>'">
                <td><c:out value="${review.getNo()}"/></td>
                <td><c:out value="${review.getTitle()}"/></td>
                <td><c:out value="${review.getUser_id()}"/></td>
                <td><c:out value="${review.getLikes()}"/></td>
                <td><c:out value="${review.getView()}"/></td>
                <td><c:out value="${review.getModified_at()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>