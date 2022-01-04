<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
  <body>
  <h1>내 예약 상세페이지</h1>
  <table border="solid 1px" style="border-collapse: collapse; margin : 40px 0px;">
    <c:forEach var="item" items="reserveList">

    <tr>
      <td>NO</td>
      <td>${item.getNo()}</td>
      <td>식당ID</td>
      <td>${item.getRestaurant_id()}</td>
      <td>식당명</td>
      <td>${item.getRestaurant_name() }<td>
    </tr>
    <tr>
      <td>예약자성명</td>
      <td colspan="4">${item.getUser_name()}</td>
    </tr>
    <tr>
      <td>예약자번호</td>
      <td colspan="4">${item.getPhone()}</td>
    </tr>
    <tr>
      <td>예약시간</td>
      <td colspan="2">${item.getReserve_time()}</td>
      <td>예약인원</td>
      <td colspan="2">${item.getCnt()}</td>
    </tr>

    </c:forEach>
  </table>
  </body>
</html>
