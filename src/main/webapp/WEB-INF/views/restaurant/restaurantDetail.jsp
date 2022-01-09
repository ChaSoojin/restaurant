<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4ab81001911aa4ddc5b7d6148c5a348b"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <title>Restaurant Detail</title>
</head>
<body onload="checkBrowser(${restaurant.get(0).getX()}, ${restaurant.get(0).getY()}, '${restaurant.get(0).getRestaurant_name()}' )">

<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
        <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">

            <table border="solid 1px"
                   style="border-collapse: collapse; width: 600px; height: 200px; margin:auto; text-align:center;">
                <tr>
                    <td>NO</td>
                    <td>${restaurant.get(0).getNo()}</td>
                    <td>ID</td>
                    <td>${restaurant.get(0).getRestaurant_id()}</td>
                    <td>좋아요</td>
                    <td style="display:none">${restaurant.get(0).getLikes()}</td>
                </tr>
                <tr>
                    <td>식당명</td>
                    <td colspan="6">
                    ${restaurant.get(0).getRestaurant_name() }
                    <td>
                </tr>
                <tr>
                    <td>전화번호</td>
                    <td colspan="6">${restaurant.get(0).getPhone()}</td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td colspan="6">${restaurant.get(0).getAddress()}</td>
                </tr>
            </table>
            <br><br>
            <div id="map" style="width:1000px;height:500px; display:inline-block;"></div>
            <br><br>

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
                <c:forEach items="${reviewList }" var="review">
                    <tr method="post" onclick="location.href='reviewView?no=<c:out value="${review.getNo()}"/>'">
                        <td><c:out value="${review.getNo()}"/></td>
                        <td><c:out value="${review.getTitle()}"/></td>
                        <td><c:out value="${review.getUser_id()}"/></td>
                        <td><c:out value="${review.getLikes()}"/></td>
                        <td><c:out value="${review.getView()}"/></td>
                        <td><c:out value="${review.getModified_at()}"/></td>
                    </tr>
                </c:forEach>
            </table>
            <br><br>
        </div>
    </div>
</div>

<script src="script/restaurantMap.js"></script>
</body>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>