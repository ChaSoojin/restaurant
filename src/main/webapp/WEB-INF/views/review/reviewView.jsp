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
    <style>
        #like_span{
            text-align: center;
        }
    </style>
    <title>Review View</title>
</head>


<c:set var="user_id" value="${sessionScope.log}"/>
<c:set var="review_no" value="${review.getNo()}"/>
<%--<c:out value="user_id:${user_id} review_no:${review_no}"/>--%>

<div style=" border-top:1px solid black;  ">

</div>
<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
        <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">

            <div style=" border-top:1px solid black;  ">
            </div>

        <div style="font-size: 1.5em; margin : 40px 0px;">
            <table style="margin-bottom: 10px; margin-top: 10px;">
                <tr>
                    <span style="margin-right:30px;">제목 : <c:out value="${review.getTitle()}"/></span>
                </tr>
            </table>

        <span style="margin-right:30px;">ID :  <c:out value="${review.getUser_id()}"/> </span>
        <span style="margin-right:30px;">등록일 : <c:out value="${review.getModified_at()}"/> </span>
        <span style="margin-right:30px;">조회수 : <c:out value="${review.getView()}"/> </span>
<%--    </div><td width="130px;"><img src="${map.bookImg }" style="height:173px;"/></td>--%>

    <table style="margin-top : 30px; margin-bottom: 70px;">
        <tr>
            <td><c:out value="${review.getContent()}"/><td>
        </tr>
    </table>


<br><br>
            <div style=" border-top:1px solid black;  "></div>
                <br>

    <div  id = "like_span" style="padding-top: 20px; height:300px; margin-bottom: 40px; ">
        <c:if test="${not empty user_id}">
                <span style="padding: 20px">
                    <%-- 좋아요 눌러져 있는 상태 --%>
                    <c:if test="${like_exist eq 'true'}">
                        <%-- reviewLike 테이블에서 삭제 --%>
                        <a href="/deleteLike?no=${review.getNo()}">
                            <img src="<c:url value='like/like.png'/>" style="width:46px; height:46px" id="like1" data-like="N">${review.getLikes()}</a>
                    </c:if>

                    <%-- 좋아요 안눌러져 있는 상태 --%>
                    <c:if test="${like_exist eq 'false'}">
                        <%-- reviewLike 테이블에 추가 --%>
                        <a href="/addLike?no=${review.getNo()}">
                            <img src="<c:url value='like/default_like.png'/>" style="width:46px; height:46px" id="like2" data-like="N">${review.getLikes()}</a>
                    </c:if>
                </span>
        </c:if><br><br>
<%--       이자리에 사장님 코멘트 넣으면될듯 --%>
    <h5>◼︎︎ 사장님 댓글 ◼︎</h5>
    <div style="padding: 10px">
        <c:choose>
            <c:when test="${empty review.getComment()}">
                <span style="color: #6c757d">작성된 댓글이 없습니다.</span>
            </c:when>
            <c:otherwise>
                <c:out value="${review.getComment()}"/>
            </c:otherwise>
        </c:choose>

    </div>
    </div>



    <c:if test="${sessionScope.log eq user_id}">
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