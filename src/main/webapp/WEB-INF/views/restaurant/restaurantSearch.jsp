<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<section class="breadcrumbs-custom bg-image"
         style="background-image: url('/images/bg-image-1.jpg');">
    <div class="breadcrumbs-custom-inner">
        <div class="breadcrumbs-custom-container container">
            <div class="breadcrumbs-custom-main">
                <h6 class="breadcrumbs-custom-subtitle title-decorated" style="color:white;">우리동네 맛집찾기</h6>
                <h1 class="breadcrumbs-custom-title" style="color:white;">맛집 검색</h1>
            </div>
        </div>
    </div>
</section>

<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4ab81001911aa4ddc5b7d6148c5a348b"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<%--onload="checkBrowser()"--%>

<body>
<%--<div style="text-align:center; margin: 50px 0px; width:100vw;height:100vh;">--%>
    <div style="text-align:center; margin: 50px 0px; ">

<%--    <div id="map" style="width:70%; height:70%; display:inline-block;"></div>--%>
    <div id="map" style="width:700px; height:300px; display:inline-block;"></div>
    <br><br><br>

    <div style="display: inline-block; border-bottom: 1px solid black; padding-bottom: 30px; margin-bottom : 20px;  ">
        <input type="text" id="restName" name="restName" style="width:400px; display: inline" onkeyup="enterkey();"/>
        <button type="submit" id="search" name="search" style="margin:0px 30px;" onclick="search()">검색</button>
    </div>

    <br><br>
    <div id="restList" >
        <div style="height:400px; display:block; justify-content: center; align-items: center; font-size: 2em;">
<%--            <div style=" display:block; justify-content: center; align-items: center; font-size: 2em; ">--%>
            <div>맛집을 검색해 주세요!</div>
        </div>
    </div>

</div>
<script src="script/map.js"></script>
</body>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>