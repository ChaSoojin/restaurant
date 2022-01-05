<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>owner Setting Page</title>
</head>
<body>
<%--<form method="post" action="/setMyRestaurant">--%>
<!-- reserve_seq no id cnt days r_day use_navi use_seat -->
<table border="solid 1px" style="border-collapse: collapse; width: 800px; height: 400px; margin:auto; text-align:center;">
    <%
        //String log = (String)session.getAttribute("id");
        String id = "banana";
        session.setAttribute("log", id);
    %>
    <c:set var="user_id" value="${sessionScope.log}"/>
    <c:set var="restaurant_id" value="${restaurantList.get(0).getRestaurant_id()}"/>
    <c:out value="user_id: ${user_id}"/>
    <c:out value="restaurant_id: ${restaurant_id}"/>

    <tr>
        <td>식당명</td>
        <td colspan="5">${restaurantList.get(0).getRestaurant_name() }</td>
    </tr>
    <tr>
        <td>식당전화번호</td>
        <td colspan="5">${restaurantList.get(0).getPhone()}</td>
    </tr>
    <tr>
        <td>식당주소</td>
        <td colspan="5">${restaurantList.get(0).getAddress()}</td>
    </tr>
</table>
<%--    <input type="hidden" name="restaurant_id" value="${restaurantList.get(0).getRestaurant_id()}">--%>

<br>
<h3>이 정보가 맞습니까?</h3><br><br>
Yes -> <input type="button" id="btn1" onclick="location.href='/setMyRestaurant?user_id=${user_id}&restaurant_id=${restaurant_id}'" value="내 가게 설정완료"><br><br>
N0  -> <button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button><br><br>
<%--    <script src="script/reserveValidation.js"></script>--%>
<%--</form>--%>
</body>
</html>