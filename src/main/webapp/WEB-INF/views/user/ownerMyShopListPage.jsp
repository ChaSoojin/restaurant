<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4ab81001911aa4ddc5b7d6148c5a348b"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<style>
    #cancelbtn{
        cursor:pointer;
    }
    .container {
        display: flex;
    }
</style>

<section class="breadcrumbs-custom bg-image" style="background-image: url(logo.png);">
    <div class="breadcrumbs-custom-inner">
        <div class="breadcrumbs-custom-container container">
            <div class="breadcrumbs-custom-main">
                <h6 class="breadcrumbs-custom-subtitle title-decorated">사장페이지</h6>
                <h1 class="breadcrumbs-custom-title">사장페이지</h1>
            </div>
        </div>
    </div>
</section>

<section class="section section-lg">
    <div class="container">
        <div class="row">
<%--            <div class="col-sm-10 col-md-9 col-lg-6 col-xl-5">--%> <%-- 이거 삭제하니까 쫙 펴짐 --%>
                <h6>OwnerPage</h6>

                <!-- Bootstrap tabs -->
                <div class="tabs-custom tabs-horizontal tabs-line" id="tabs-1">
                    <!-- Nav tabs-->
                    <ul class="nav nav-tabs">
                        <li class="nav-item active" role="presentation">
                            <a class="nav-link active" href="#tabs-1-1" data-toggle="tab"
                               id="nav_first">내 가게 보기</a></li>

                        <li class="nav-item" role="presentation"><a class="nav-link"
                                                                    href="#tabs-1-2" data-toggle="tab">내 예약내역</a></li>

                        <li class="nav-item" role="presentation"><a class="nav-link"
                                                                    href="#tabs-1-3" data-toggle="tab">내가 쓴 리뷰</a></li>

                        <li class="nav-item" role="presentation"><a class="nav-link" href="#tabs-1-4" data-toggle="tab">
                            회원정보 수정</a></li>
                        <li class="nav-item" role="presentation"><a class="nav-link" href="#tabs-1-5" data-toggle="tab">
                            회원 탈퇴하기</a></li>
                    </ul>
                    <br> <br>
                    <div class="tab-content">
                        <div class="tab-pane fade show active in" id="tabs-1-1">

                            <c:set var="user_id" value="${sessionScope.log}"/>

                            <h3>${user_id}사장님 안녕하세요 :)<input type="button" value="내 가게 등록하기" onclick="location.href='searchPage'"></h3>

                            <c:set var="size" value="${list.size()}"/>
                            <c:choose>
                                <c:when test="${size eq 0}">
                                    <h3>등록된 가게가 없습니다. <br>추가해주세요! -> <input type="button" value="내 가게 등록하기" onclick="location.href='searchPage'" ></h3>
                                </c:when>
                                <c:otherwise>
                                    <div class="container">
                                        <c:forEach var="list" items="${list}">
                                            <div class="item">
                                                    <%--  레스토랑ID: <c:out value="${list.getRestaurant_id()}"/><br><br>--%>
                                                    <%--  레스토랑명: <c:out value="${list.getRestaurant_name()}"/><br><br>--%>

                                                <table border="1" width="100%">
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
                                                        <td>
                                                            <input type="button" onclick="checkBrowser(${list.getX()}, ${list.getY()}, '${list.getRestaurant_name()}' )" value="지도보기">
                                                                <%--                        <input type="button" onclick="location.href='ownerMapView?x=${list.getX()}&y=${list.getY()}&name=${list.getRestaurant_name()}'" value="지도보기">--%>
                                                            <input type="button" onclick="location.href='ownerReserveCheck?restaurant_id=${list.getRestaurant_id()}'" value="예약내역 보러가기">
                                                            <input type="button" onclick="location.href='ownerReviewCheck?restaurant_id=${list.getRestaurant_id()}'" value="리뷰 보기">
                                                            <input type="button" onclick="location.href='deleteRestaurantByUserId?restaurant_id=${list.getRestaurant_id()}&user_id=${user_id}'" value="가게 삭제">
                                                        </td>
                                                    </tr>
                                                </table><br>
                                            </div>
                                        </c:forEach>
                                    </div>

                                    <br><br>
                                </c:otherwise>
                            </c:choose>
                            <div id="map" style="width:1000px;height:500px; display:inline-block;"></div>
                            <script src="script/restaurantMap.js"></script>


                            <br>
                        </div>
                        <!-- 내 예약 -->
                        <div class="tab-pane fade" id="tabs-1-2">
                         <table class="table table-hover table-job-positions text-center">
                             <tr>
                                 <th>번호</th>
                                 <th>예약자</th>
                                 <th>식당명</th>
                                 <th>전화번호</th>
                                 <th>예약날짜</th>
                                 <th>예약인원</th>
                                 <th>예약취소</th>
                             </tr>

                             <c:forEach var="item" items="${myreserve}">
                                 <tr>
                                     <td>${item.getNo()}</td>
                                     <td>${item.getUser_id()}</td>
                                     <td style="cursor: pointer; color: blue" onclick="location.href='/detail?restaurant_id=<c:out value="${item.getRestaurant_id()}"/>'">${item.getRestaurant_name()}</td>
                                     <td>${item.getPhone()}</td>
                                     <td>${item.getReserve_time()}</td>
                                     <td>${item.getCnt()}</td>
                                     <td><button id="cancelbtn" onclick="location.href='/reserveDelete?no=<c:out value="${item.getNo()}"/>'">예약취소</button></td>
                                 </tr>
                             </c:forEach>

                         </table>
                        </div>

                        <!-- 내가 쓴 리뷰 -->
                        <div class="tab-pane fade" id="tabs-1-3">
                        <table class="table table-hover table-job-positions text-center">
                            <thead>
                            <tr>
                                <th>no</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>좋아요</th>
                                <th>조회수</th>
                                <th>등록일자</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${myreview}">
                                <tr>
                                    <td>${item.getNo()}</td>
                                    <td style="cursor: pointer; color: blue" onclick="location.href='reviewView?no=<c:out value="${item.getNo()}"/>'">${item.getTitle()}</td>
                                    <td>${item.getUser_id()}</td>
                                    <td>${item.getLikes()}</td>
                                    <td>${item.getView()}</td>
                                    <td>${item.getModified_at()}</td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>
                        </div>

                       <%-- 회원정보 수정 --%>
                        <div class="tab-pane fade" id="tabs-1-4">
                            <form action="userInfoUpdate" method="post">
                                <table>
                                    <tr>
                                        <td>비밀번호</td>
                                        <td><input type="password" name="pw" size="20" id="pw" value="${loginUser.getPw()}">*</td>
                                    </tr>

                                    <tr>
                                        <td>폰번호</td>
                                        <td><input type="text" name="phone" size="20" id="phone" value="${loginUser.getPhone()}">*</td>
                                    </tr>

                                    <tr>
                                        <td>email</td>
                                        <td><input type="text" name="email" size="20" id="email" value="${loginUser.getEmail()}">*</td>
                                    </tr>

                                </table>
                                <div id="wrapBtn">
                                    <%--<input type="submit" value="확인" id="btn" >--%>
                                    <input type="submit" value="수정완료" id="btn">

                                        <input type="hidden" name=id value="<%=(String)session.getAttribute("log")%>">
                                </div>
                            </form>
                        </div>
                        <%-- 회원탈퇴 --%>
                        <div class="tab-pane fade" id="tabs-1-5">
                            <form method="post" action="deleteUser">
                                <span>ID:&#9;</span><input type='text' name='id' name="id" required><br><br>
                                <span>&#9;PW:&#9;</span><input type='password' name='pw' required><br><br>
                                <%--                                <input type="hidden" id="num" name="num" value=<c:out value="${review.getNo()}"/>><br>--%>
                                <input type="submit" value="탈퇴하기" >
                            </form>
                        </div>
                    </div>
                </div>
<%--            </div>--%>
        </div>
    </div>
</section>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>