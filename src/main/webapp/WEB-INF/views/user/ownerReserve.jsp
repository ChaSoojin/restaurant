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
<c:set var="size" value="${reserve.size()}"/>
<c:choose>
    <c:when test="${size eq 0}">
        <h1>예약내역 확인</h1>
        <br><br>
        <h3>예약내역이 존재하지 않습니다.</h3>
    </c:when>
    <c:otherwise>
        <h1><c:out value="${reserve.get(0).getRestaurant_name()}"/> 예약내역 확인</h1>

        <table border="1" width="90%">
            <thead>
            <tr>
                <th>ID</th>
                <th>name</th>
                <th>phone</th>
                <th>time</th>
                <th>family size</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reserve" items="${reserve}">
                <tr>
                    <td><c:out value="${reserve.getUser_id()}"/> </td>
                    <td><c:out value="${reserve.getUser_name()}"/> </td>
                    <td><c:out value="${reserve.getPhone()}"/> </td>
                    <td><c:out value="${reserve.getReserve_time()}"/> </td>
                    <td><c:out value="${reserve.getCnt()}명"/> </td>
                    <td><input type="button" value="완료" onclick=""></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>