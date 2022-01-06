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
    <title>Review View</title>
</head>
<body>
<%
    session.setAttribute("log","apple");
%>
<c:set var="user_id" value="${sessionScope.log}"/>
<c:set var="review_id" value="${review.getUser_id()}"/>
<%--    <c:out value="user_id: ${user_id}"/><br>--%>
<%--    <c:out value="review_id: ${review_id}"/><br>--%>

<%--<c:if test="${sessionScope.log eq review_id}">--%>
<%--    <input type="button" value="수정하기" onclick="location.href='reviewUpdatePage?no=<c:out value="${review.getNo()}"/>'">--%>
<%--</c:if>--%>
<button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>
<%--<input type="button" value="뒤로가기" onclick="location.href='list'">--%>

<div class="wrap">
    <table border="1" width="100%">
        <thead>
        <tr>
            <th  colspan="3"  height="30px">제목: <strong> <c:out value="${review.getTitle()}"/></strong></th>
        </tr>
        <tr>
            <th>ID: <c:out value="${review.getUser_id()}"/></th>
            <th>작성날짜: <c:out value="${review.getModified_at()}"/></th>
            <th>좋아요: <c:out value="${review.getLikes()}"/>개</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td  colspan="3" height="100px"><c:out value="${review.getContent()}"/></td>
        </tr>
        </tbody>
    </table>
<%--    <label><input type="button" value="좋아요" onlick=""><c:out value="${review.getLikes()}개"/></label>--%>
    <br><br>
    <h3>사장님, 댓글을 달아주세요!</h3>
    <form method="post" action="ownerReviewUpdate">
        <input type="hidden" name="no" value="${review.getNo()}">
        <textarea name="comment" id="comment" rows="10" cols="60">${review.getComment()}</textarea><br><br>
        <input type="submit" value="수정하기">
    </form>
</div>
</body>
</html>