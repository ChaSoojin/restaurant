<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4ab81001911aa4ddc5b7d6148c5a348b"></script>
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>

<c:import url="/WEB-INF/views/layout/header.jsp"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <style>
        #con {
            overflow: hidden;
            width: 1200px;
            height: 999px;
            margin: 100px auto;
            color: black;
            font-size: 20px;
            text-align: center;
            text-transform: uppercase;
        }

        .box {
            width: 333px;
            height: 333px;
            float: left;
            margin-right: 30px;
            margin-bottom: 30px;
        }

        .box:after {
            display:block;
            content:'';
            clear:both;
        }

        .content2 {
            margin-right: 0;
        }

        .content5 {
            margin-right: 0;
        }

        .content8 {
            margin-right: 0;
        }

    </style>
    <title>Restaurant Like</title>
</head>
<body>
<div style="text-align:center; margin: 50px 0px;">

    <div id="searchDiv">
        <input type="text" id="restName" name="restName" style="width:400px; display: inline" onkeyup="enterkey();"/>
        <button type="submit" id="search" name="search" style="margin:0px 30px;" onclick="restaurantSearch( '<%=(String)session.getAttribute("log")%>')">검색</button>
    </div>

    <div id="con">
    </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
<script src="script/restaurantLike.js"></script>
</html>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>