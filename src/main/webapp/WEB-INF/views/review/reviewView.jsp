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
    <script src="script/review.js"></script>
    <title>Review View</title>
</head>


<c:set var="user_id" value="${sessionScope.log}"/>
<c:set var="review_id" value="${review.getUser_id()}"/>
<div style=" border-top:1px solid black;  ">

</div>
<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
        <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">

            <div style=" border-top:1px solid black;  ">

            </div>

        <div style="font-size: 1.5em; margin : 40px 0px;">
        <span style="margin-right:30px;">제목 : <c:out value="${review.getTitle()}"/></span>
        <span style="margin-right:30px;">ID :  <c:out value="${review.getUser_id()}"/> </span>
        <span style="margin-right:30px;">등록일 : <c:out value="${review.getModified_at()}"/> </span>
        <c:if test="${ like gt 0 }"> <%--  --%>
            <a href=""><img src="<c:url value='/static/like/like.png'/>" style="width:46px; height:46px" id="like" data-like="N"></a>
        </c:if>
        <c:if test="${ like lt 1 }">
            <img src="<c:url value='/static/like/default_like.png'/>" style="width:46px; height:46px" id="like" data-like="N">
        </c:if>
    </div>

    <table style="margin : 40px 0px;">
        <tr>
            <td width="130px;"><img src="${map.bookImg }" style="height:173px;"/></td>
            <td><c:out value="${review.getContent()}"/><td>
        </tr>
    </table>
            <h3>⬇︎ 사장님 댓글 ⬇︎</h3>
            <div style="border: 2px solid tan; padding: 10px">
                <c:choose>
                    <c:when test="${empty review.getComment()}">
                        <span style="color: #6c757d">작성된 댓글이 없습니다.</span>
                    </c:when>
                    <c:otherwise>
                        <c:out value="${review.getComment()}"/>
                    </c:otherwise>
                </c:choose>

            </div>

    <div style="padding-top: 20px; border-top:1px solid black; height:300px; margin-bottom: 40px; ">
<%--       이자리에 사장님 코멘트 넣으면될듯 ${map.bookRev}--%>
    </div>



    <c:if test="${sessionScope.log eq review_id}">
        <input type="button" value="수정하기" onclick="location.href='reviewUpdatePage?no=<c:out value="${review.getNo()}"/>'">
    </c:if>
    <input type="button" value="뒤로가기" onclick="location.href='list'">

</div>
    </div>
</div>



















<%--<body>--%>
<%--<c:set var="user_id" value="${sessionScope.log}"/>--%>
<%--<c:set var="review_id" value="${review.getUser_id()}"/>--%>

<%--<c:if test="${sessionScope.log eq review_id}">--%>
<%--    <input type="button" value="수정하기" onclick="location.href='reviewUpdatePage?no=<c:out value="${review.getNo()}"/>'">--%>
<%--</c:if>--%>
<%--<input type="button" value="뒤로가기" onclick="location.href='list'">--%>

<%--<div class="wrap">--%>
<%--    <table border="1" width="100%">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th  colspan="2"  height="30px">제목: <strong> <c:out value="${review.getTitle()}"/></strong></th>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <th>ID: <c:out value="${review.getUser_id()}"/></th>--%>
<%--            <th>작성날짜: <c:out value="${review.getModified_at()}"/></th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td  colspan="2" height="100px"><c:out value="${review.getContent()}"/></td>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--    <label><input type="button" value="좋아요" onlick=""><c:out value="${review.getLikes()}개"/></label>--%>
<%--    <br><br>--%>
<%--    // 사장님 코멘트--%>
<%--</div>--%>
<%--</body>--%>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>