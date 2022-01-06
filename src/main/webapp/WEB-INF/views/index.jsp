<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>INDEX</title>
</head>
<body>
<%--<%--%>
<%--    session.setAttribute("log","apple");--%>
<%--%>--%>

<%
    //String log = (String)session.getAttribute("id");
    String id = "banana";
//    String id = "brown";
    session.setAttribute("log", id);
%>

// list 주려면 밑에 이거 꼭 있어야 함
<c:set var="user_id" value="${sessionScope.log}"/>
Index Page
<input type="button" onclick="location.href='list'" value="리뷰보러가기">
<%--<input type="button" onclick="location.href='ownerShopList?user_id=${user_id}'" value="사장님페이지">--%>

<div class="wrap">
    <header>
        <h1>INDEX</h1>

        <c:if test="${proc eq 'success' }">
            <c:out value="회원가입이 완료되었습니다!"/>
        </c:if>
        <c:if test="${proc eq 'fail' }">
            <c:out value="중복된 아이디가 존재합니다!"/>
        </c:if>

    </header>
    <main>
        <div id="btn">
            <input type="button" id="join" value="join" onclick="location.href='/join'">
            <input type="button" id="login" value="login" onclick="location.href='/login'">
            <input type="button" id="userMyPage" value="userMyPage" onclick="location.href='/userMyPage'">
        </div>
    </main>
    <nav></nav>
    <aside></aside>
    <footer></footer>
</div>
</body>
</html>