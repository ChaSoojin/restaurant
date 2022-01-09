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
<body>
<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
        <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">
            <h4 style="text-align: center;">내가 찜한 목록</h4>
            <table border="solid 1px" style="border-collapse: collapse; margin : 40px 200px; text-align: center">
                <c:forEach var="item" items="${likelist}">
                    <tr>
                        <td>식당ID</td>
                        <td>${item.getRestaurant_id() }</td>
                        <td>식당명</td>
                        <td>
                            ${item.getRestaurant_name() }
                        <td>
                        <td>취소</td>
                        <td>예약하기</td>
                        <td>리뷰작성</td>
                    </tr>
                    <tr>
                        <td>식당번호</td>
                        <td colspan="3">${item.getPhone()}</td>
                        <td colspan="2" rowspan="2">
                            <img src="<c:url value='like/like.png'/>"
                                 onclick="location.href='/myLikeDelete?restaurant_id=${item.getRestaurant_id() }'"
                                 style="width:30px; height:30px; cursor:pointer; text-align:center;">
                        </td>
                        <td rowspan="2">
                            <button onclick="location.href='/restaurantReserve?restaurant_id=${item.getRestaurant_id() }'"
                                    style='padding:13px 13px; cursor: pointer;'>예약하기
                            </button>
                        </td>
                        <td rowspan="2">
                            <button onclick="location.href='/writePage?restaurant_id=${item.getRestaurant_id() }'"
                                    style='padding:13px 13px; cursor: pointer;'>리뷰작성
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td>식당주소</td>
                        <td colspan="3">${item.getAddress()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>