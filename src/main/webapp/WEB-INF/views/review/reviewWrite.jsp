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
    <title>Review Write Page</title>
</head>

<section class="breadcrumbs-custom bg-image">

   <div class="breadcrumbs-custom-inner">
        <div class="breadcrumbs-custom-container container">
            <div class="breadcrumbs-custom-main">
                <h6 class="breadcrumbs-custom-subtitle title-decorated" style="color:white;">맛집 리뷰</h6>
                <h1 class="breadcrumbs-custom-title" style="color:white;">맛집집 리뷰</h1>            </div>
        </div>
    </div>
</section>

<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
        <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">


            <form name="write_form" action="/write" method="post">

                <div style="font-size: 1.5em; margin : 40px 0px;">
                    제목 : <input type="text" id="title" name="title" required>
                    식당ID: ${restaurant_id}
                </div>

                <div style="text-align: center;">
                    <textarea rows="10" cols="80" id="content" name="content" class="form-input form-control-has-validation form-control-last-child"></textarea>
                </div>


                <input type="hidden" name="restaurant_id" value="${restaurant_id}">


                <input type="submit" id="subBtn1" value="쓰기" class="button button-primary button-winona" style="margin-bottom: 40px;" >

            </form>


        </div>
    </div>
</div>





























<%--        <body>--%>
<%--<h1>리뷰 작성</h1>--%>
<%--<form method="post" action="/write">--%>
<%--    <input type="hidden" name="restaurant_id" value="${restaurant_id}">--%>

<%--    <span>제목: </span><input type="text" id="title" name="title" required><br><br>--%>
<%--    <span>식당ID: ${restaurant_id}</span><br><br>--%>
<%--    <textarea name="content" id="content" rows="30" cols="100" placeholder="새로운 글을 작성하세요!" required></textarea><br><br>--%>
<%--    <input type="submit" value="제출하기">--%>
<%--&lt;%&ndash;    <input type="button" value="뒤로가기" onclick="location.href='reviewList'">&ndash;%&gt;--%>
<%--    <input type="button" value="뒤로가기" onclick="location.href='list'">--%>

<%--</form>--%>
<%--</body>--%>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>