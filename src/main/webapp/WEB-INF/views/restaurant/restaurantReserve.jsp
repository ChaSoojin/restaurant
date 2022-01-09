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
    <style>
        #name, #phone, #cnt {
            border: none;
            width: 300px;
            height: 50px;
        }

        #date {
            height: 30px;
            margin-left: 10px;
        }
    </style>
    <title>Restaurant Reserve Page</title>
</head>
<body>
<div class="container" style="margin-top : 30px;">
    <div class="row container-board-padding" style="display: flex; justify-content: center;">
       <%-- <div class="col-sm-12 col-md-12 col-lg-12  col-xl-9 table-responsive wow fadeIn row ">--%>

            <form method="post" action="/reserve">
                <!-- reserve_seq no id cnt days r_day use_navi use_seat -->
                <table border="solid 1px"
                       style="border-collapse: collapse; width: 800px; height: 400px; margin:auto; text-align:center;">
                    <tr>
                        <td>NO</td>
                        <td>${restaurantList.get(0).getNo()}</td>
                        <td>식당ID</td>
                        <td>${restaurantList.get(0).getRestaurant_id()}</td>
                        <td>좋아요</td>
                        <td>${restaurantList.get(0).getLikes()}</td>
                    </tr>
                    <tr>
                        <td>식당명</td>
                        <td colspan="5">
                        ${restaurantList.get(0).getRestaurant_name() }
                        <td>
                    </tr>
                    <tr>
                        <td>식당전화번호</td>
                        <td colspan="5">${restaurantList.get(0).getPhone()}</td>
                    </tr>
                    <tr>
                        <td>식당주소</td>
                        <td colspan="5">${restaurantList.get(0).getAddress()}</td>
                    </tr>
                    <tr>
                        <td>이름</td>
                        <td colspan="5"><input type="text" placeholder="이름 입력" id="name" name="name"></td>
                    </tr>
                    <tr>
                        <td>유저ID</td>
                        <td colspan="5">${sessionScope.log}</td>
                    </tr>
                    <tr>
                        <td>번호입력</td>
                        <td colspan="5"><input type="tel" placeholder="전화번호 입력" name="phone" id="phone" required><br>
                        </td>
                    </tr>
                    <tr>
                        <td>예약날짜선택</td>
                        <td colspan="5"><input type="date" name="date" id="date"></td>
                    </tr>
                    <tr>
                        <td>예약인원</td>
                        <td colspan="5">
                            <input type="text" placeholder="예약인원 입력" name="cnt" id="cnt"></td>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="restaurant_id" value="${restaurantList.get(0).getRestaurant_id()}">
                <input type="hidden" name="restaurant_name" value="${restaurantList.get(0).getRestaurant_name()}">

                <br>
                <input type="button" id="btn1" onclick="checkInfo(form)" value="예약완료"
                       class="button button-primary button-winona" style="margin-bottom: 40px;">
                <button type="button" id="btn2" onclick="history.back(-1);" class="button button-primary button-winona"
                        style="margin-bottom: 40px;">뒤로가기
                </button>
            </form>
       <%-- </div>--%>
    </div>
</div>
<script src="script/reserveValidation.js"></script>
</body>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>