<%@ page import="org.springframework.web.context.request.SessionScope" %>
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <title>Review List</title>
</head>
<body>
<c:set var="path" value="${pageContext.request.contextPath}" />

<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="justify-content: center;">
        <div class="col-md-12 table-responsive wow fadeIn row ">
            <table class="table table-hover table-job-positions text-center">
                <thead class="thead_top">
                <tr>
                    <th>No</th>
                    <th>Title</th>
                    <th>ID</th>
                    <th>Like</th>
                    <th>View</th>
                    <th>Date</th>
                </tr>
                </thead>
                <c:forEach items="${list }" var="review">
                    <tr  method="post" style="cursor: pointer;" onclick="location.href='reviewView?no=<c:out value="${review.getNo()}"/>'">
                        <td><c:out value="${review.getNo()}"/></td>
                        <td><c:out value="${review.getTitle()}"/></td>
                        <td><c:out value="${review.getUser_id()}"/></td>
                        <td><c:out value="${review.getLikes()}"/></td>
                        <td><c:out value="${review.getView()}"/></td>
                        <td><c:out value="${review.getModified_at()}"/></td>
                    </tr>
                </c:forEach>
<%--                <a class="button button-lg button-primary button-winona" href="<c:url value='/bookSearch/bookSearch'/>">리뷰작성하기</a>--%>
<%--                <c:if test="${empty list }">--%>
<%--                <tr>--%>
<%--                    <th colspan="7">작성된 리뷰가 없습니다.</th>--%>
<%--                <tr>--%>
<%--                    </c:if>--%>
            </table>
        </div>
    </div>
</div>
























<%--<h1>리뷰 게시판</h1>--%>

<%--<div class="wrap">--%>
<%--    <table border="1" width="100%">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>No</th>--%>
<%--            <th>Title</th>--%>
<%--            <th>ID</th>--%>
<%--            <th>Like</th>--%>
<%--            <th>View</th>--%>
<%--            <th>Date</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <c:forEach var="review" items="${list}">--%>

<%--            &lt;%&ndash;                <tr method="post" onclick="location.href='/v1/review/{<c:out value="${review.getNo()}"/>}'">&ndash;%&gt;--%>
<%--            <tr method="post" onclick="location.href='reviewView?no=<c:out value="${review.getNo()}"/>'">--%>
<%--                <td><c:out value="${review.getNo()}"/></td>--%>
<%--                <td><c:out value="${review.getTitle()}"/></td>--%>
<%--                <td><c:out value="${review.getUser_id()}"/></td>--%>
<%--                <td><c:out value="${review.getLikes()}"/></td>--%>
<%--                <td><c:out value="${review.getView()}"/></td>--%>
<%--                <td><c:out value="${review.getModified_at()}"/></td>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>
</body>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>