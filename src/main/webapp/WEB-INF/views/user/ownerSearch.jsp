<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Restaurant Search</title>
</head>
<body>
<h1>내 가게 등록 페이지</h1>
<div style="text-align:center; margin: 50px 0px;">

    <div style="display: inline-block; border-bottom: 1px solid black; padding-bottom: 30px; margin-bottom : 20px;">
        <input type="text" id="restName" name="restName" style="width:400px; display: inline" onkeyup="enterkey();"/>
        <button type="submit" id="search" name="search" style="margin:0px 30px;" onclick="restaurantSearch()">검색</button>
        <button type="button" id="btn2" onclick="history.back(-1);">뒤로가기</button>
    </div>

    <div id="restList">
        <div style="height:400px; display:flex; justify-content: center; align-items: center; font-size: 2em;">
            <div>맛집을 검색해 주세요!</div>
        </div>
    </div>

</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</body>
<script type="text/javascript">
    function restaurantSearch(){
        $.ajax({
            method : 'GET'
            , url : "https://dapi.kakao.com/v2/local/search/keyword.json"
            , data : {
                query : $("#restName").val() //사용자가 검색한 키워드
                , category_group_code : "FD6" //음식점 필터링
                , sort : "accuracy" //정확도 순
                , size : 10} //사이즈 10개까지만
            , headers : {Authorization : "KakaoAK f1b2afc29adbbda05eea78825d075ca9"}
        })
            .done(function(data) {
                var getList = "";
                var num = 0;
                getList += "<table style='display:inline-block; border-collapse: separate; border-spacing: 0 10px;'>"
                for(var i=0; i<data.documents.length; i++) {

                    getList += "<tr>"
                    getList += "<td id='place_id' name='id'>" + data.documents[i].id +  "</td>"
                    getList += "<td id='place_name'>" + data.documents[i].place_name +  "</td>"
                    getList += "<td id='phone'>" + data.documents[i].phone + "</td>"
                    getList += "<td id='category'>" + data.documents[i].category_name + "</td>"
                    getList += "<td id='address'>" + data.documents[i].address_name + "</td>"
                    getList += "<td>"
                    getList += "<input type='hidden' value='" + data.documents[i].x + "'>";
                    getList += "<input type='hidden' value='" + data.documents[i].y + "'>";
                    getList += "<button onclick='saveData("+ num +")' style='padding:13px 13px;'>내 가게로 등록</button>"
                    getList += "</td>"
                    getList += "</tr>"
                    num += 1;
                }
                getList += "</table>"
                $("#restList").html("");
                $("#restList").html(getList);
            })
    }

    function enterkey() {
        if (window.event.keyCode == 13) {
            restaurantSearch();
        }
    }
    //조회한 맛집 데이터 DB저장
    function saveData(num){
        var no = num * 6;
        var inputNum = num * 2 + 1 ;
        var str1 = document.getElementsByTagName('td')[no].childNodes[0].nodeValue; // id
        var str2 = document.getElementsByTagName('td')[no+1].childNodes[0].nodeValue; // name
        var str3 = document.getElementsByTagName('td')[no+2].childNodes[0].nodeValue; // phone
        var str4 = document.getElementsByTagName('td')[no+3].childNodes[0].nodeValue; // category
        var str5 = document.getElementsByTagName('td')[no+4].childNodes[0].nodeValue; // address
        var x = document.getElementsByTagName('input')[inputNum].value; // x
        var y = document.getElementsByTagName('input')[inputNum +1].value; // y
        alert(str2 + " : 내 가게로 등록되었습니다.");

        const dataStr = {
            "restaurant_id": str1,
            "restaurant_name": str2,
            "phone" : str3,
            "x" : x,
            "y" : y,
            "address" : str5
        }
        $.ajax({
            method : 'POST',
            url : "/shopSave",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(dataStr),
        });
        location.href="ownerPage";
    }
</script>
</html>