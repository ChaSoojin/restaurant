<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>reserve check page</title>
</head>
<%--<c:set var="path" value="${pageContext.request.contextPath}" />--%>

<%--<button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>--%>
<%--<c:set var="size" value="${reserve.size()}"/>--%>
<%--<c:choose>--%>
<%--    <c:when test="${size eq 0}">--%>
<%--        <h1>예약내역 확인</h1>--%>
<%--        <br><br>--%>
<%--        <h3>예약내역이 존재하지 않습니다.</h3>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>



<%--        <h1><c:out value="${reserve.get(0).getRestaurant_name()}"/> 예약내역 확인</h1>--%>


<%--<div class="container" style="margin-top : 30px;">--%>
<%--    <div class="row container-board-padding" style="justify-content: center;">--%>
<%--        <div class="col-md-12 table-responsive wow fadeIn row ">--%>
<%--            <table class="table table-hover table-job-positions text-center">--%>
<%--                <thead class="thead_top">--%>
<%--                <tr>--%>
<%--                    <th>ID</th>--%>
<%--                    <th>name</th>--%>
<%--                    <th>phone</th>--%>
<%--                    <th>time</th>--%>
<%--                    <th>family size</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <c:forEach var="reserve" items="${reserve}">--%>
<%--                    <tr>--%>
<%--                        <td><c:out value="${reserve.getUser_id()}"/> </td>--%>
<%--                        <td><c:out value="${reserve.getUser_name()}"/> </td>--%>
<%--                        <td><c:out value="${reserve.getPhone()}"/> </td>--%>
<%--                        <td><c:out value="${reserve.getReserve_time()}"/> </td>--%>
<%--                        <td><c:out value="${reserve.getCnt()}명"/> </td>--%>
<%--                        <td><input type="button" value="완료" onclick="location.href='deleteReserve?restaurant_id=${reserve.getRestaurant_id()}&no=${reserve.getNo()}'"></td>--%>
<%--                    </tr>--%>
<%--                </c:forEach>--%>
<%--                </tbody>--%>

<%--            </table>--%>
<%--            <input type="button" value="완료" onclick="location.href='deleteReserve?restaurant_id=${reserve.getRestaurant_id()}&no=${reserve.getNo()}'">--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>


<%--    </c:otherwise>--%>
<%--</c:choose>--%>



















<body>
<%--<button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>--%>
<c:set var="size" value="${reserve.size()}"/>
<c:choose>
    <c:when test="${size eq 0}">
        <h1>예약내역 확인</h1>
        <br><br>
        <h3>예약내역이 존재하지 않습니다.</h3>
    </c:when>
    <c:otherwise>
        <h1 style="padding: 20px"><c:out value="${reserve.get(0).getRestaurant_name()}"/> 예약내역 확인</h1>
        <br><br>
        <div style="padding: 20px">
            <table border="1" width="70%" style="padding: 20px">
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
                        <td><input type="button" value="완료" onclick="location.href='deleteReserve?restaurant_id=${reserve.getRestaurant_id()}&no=${reserve.getNo()}'"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:otherwise>
</c:choose>
<br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>