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
<%--  <script src="script/validate.js"></script>--%>
  <title>Owner MyPage</title>
</head>
<body>
<%--<h1>사장님 페이지</h1>--%>
<%
  //String log = (String)session.getAttribute("id");
//  String id = "banana";
  String id = "brown";
  session.setAttribute("log", id);
%>
<c:set var="user_id" value="${sessionScope.log}"/>

<h1>${user_id}사장님 안녕하세요 :)</h1>

<c:set var="size" value="${list.size()}"/>
<c:choose>
<c:when test="${size eq 0}">
  <h3>등록된 가게가 없습니다. <br>추가해주세요! -> <input type="button" value="내 가게 등록하기" onclick="location.href='searchPage'" ></h3>
</c:when>
<c:otherwise>
  <input type="button" value="내 가게 등록하기" onclick="location.href='searchPage'" ><br><br>
  <c:forEach var="list" items="${list}">
    <%--  레스토랑ID: <c:out value="${list.getRestaurant_id()}"/><br><br>--%>
    <%--  레스토랑명: <c:out value="${list.getRestaurant_name()}"/><br><br>--%>

    <table border="1" width="30%">
      <tr>
        <td>ID: <strong>${list.getRestaurant_id()}</strong></td>
      </tr>
      <tr>
        <td>name: <strong>${list.getRestaurant_name()}</strong></td>
      </tr>
      <tr>
        <td>phone: <strong>${list.getPhone()}</strong></td>
      </tr>
      <tr>
        <td>adress: <strong>${list.getAddress()}</strong></td>
      </tr>
      <tr>
        <td>// map 출력</td>
      </tr>
      <tr>
        <td>
          <input type="button" onclick="location.href='ownerReserveCheck?restaurant_id=${list.getRestaurant_id()}'" value="예약내역 보러가기">
          <input type="button" value="리뷰 보기">
          <input type="button" value="가게 삭제">
        </td>
      </tr>
    </table>
    <br><br>
  </c:forEach>
  <br><br>
</c:otherwise>
</c:choose>
<input type="button" value="내 가게 리스트" onclick=""> -> 가게 보기(여러개일 수 있음, table 처리, 누르면 예약내역, 수정가능) <br><br>
<input type="button" value="내 가게 등록하기" onclick="location.href='searchPage'" > -> 지도로 검색해서 등록 <br><br>
<input type="button" value="회원정보 수정하기"> -> userMyPage와 연결됨<br><br>


</body>
</html>