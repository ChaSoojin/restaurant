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
    <title>Review View</title>
</head>
<c:set var="review_id" value="${review.getUser_id()}"/>

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
                        <th  colspan="4"  height="30px">제목: <strong> <c:out value="${review.getTitle()}"/></strong></th>
                    </tr>
                </table>

                <th>ID: <c:out value="${review.getUser_id()}"/></th>
                <th>작성날짜: <c:out value="${review.getModified_at()}"/></th>
                <th>좋아요: <c:out value="${review.getLikes()}"/>개</th>
                <th>조회수: <c:out value="${review.getView()}"/></th>
                <%--    </div><td width="130px;"><img src="${map.bookImg }" style="height:173px;"/></td>--%>

                <table style="margin-top : 30px; margin-bottom: 70px;">
                    <tr>
                        <td  colspan="4" height="100px"><c:out value="${review.getContent()}"/></td>
                    </tr>
                </table>


                <br><br>
                <h3>사장님, 댓글을 달아주세요!</h3>
                <form method="post" action="ownerReviewUpdate">
                    <input type="hidden" name="no" value="${review.getNo()}">
                    <textarea name="comment" id="comment" rows="10" cols="60">${review.getComment()}</textarea><br><br>
                    <input type="submit" value="수정하기">
                </form>

            </div>
        </div>
    </div>




















<%--<body>--%>
<%--<c:set var="review_id" value="${review.getUser_id()}"/>--%>

<%--<c:if test="${sessionScope.log eq review_id}">--%>
<%--    <input type="button" value="수정하기" onclick="location.href='reviewUpdatePage?no=<c:out value="${review.getNo()}"/>'">--%>
<%--</c:if>--%>
<%--<button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>--%>

<%--<div class="wrap">--%>
<%--    <table border="1" width="100%">--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th  colspan="4"  height="30px">제목: <strong> <c:out value="${review.getTitle()}"/></strong></th>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <th>ID: <c:out value="${review.getUser_id()}"/></th>--%>
<%--            <th>작성날짜: <c:out value="${review.getModified_at()}"/></th>--%>
<%--            <th>좋아요: <c:out value="${review.getLikes()}"/>개</th>--%>
<%--            <th>조회수: <c:out value="${review.getView()}"/></th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td  colspan="4" height="100px"><c:out value="${review.getContent()}"/></td>--%>
<%--        </tr>--%>
<%--        </tbody>--%>
<%--    </table>--%>

<%--    <br><br>--%>

<%--    <h3>사장님, 댓글을 달아주세요!</h3>--%>
<%--    <form method="post" action="ownerReviewUpdate">--%>
<%--        <input type="hidden" name="no" value="${review.getNo()}">--%>
<%--        <textarea name="comment" id="comment" rows="10" cols="60">${review.getComment()}</textarea><br><br>--%>
<%--        <input type="submit" value="수정하기">--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>