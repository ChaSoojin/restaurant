<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>reserve check page</title>
</head>
<body>
<button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>
<c:set var="size" value="${review.size()}"/>
<c:choose>
    <c:when test="${size eq 0}">
        <h1>리뷰내역 확인</h1>
        <br><br>
        <h3>리뷰내역이 존재하지 않습니다.</h3>
    </c:when>
    <c:otherwise>
        <h1>리뷰내역 확인</h1>

        <table border="1" width="90%">
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
            <c:forEach var="review" items="${review}">
                <tr method="post" onclick="location.href='ownerReviewView?no=<c:out value="${review.getNo()}"/>'">
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
    </c:otherwise>
</c:choose>

</body>
</html>