let cnt = 0;
let tmpNum = -1;

function locationLoadSuccess(pos) {
    // 현재 위치 받아오기
    var currentPos = new kakao.maps.LatLng(pos.coords.latitude, pos.coords.longitude);

    mylat = pos.coords.latitude;
    mylong = pos.coords.longitude;

    console.log("현재위치 : " + currentPos + " 타입: " + typeof currentPos);
    restaurantSearch(mylat, mylong);

};

function locationLoadError(pos) {
    alert('위치 정보를 가져오는데 실패했습니다.');
};

function searchRes(likelist) {
    console.log(likelist);

    navigator.geolocation.getCurrentPosition(locationLoadSuccess, locationLoadError);
};

function restaurantSearch(myY, myX) {
    console.log("**" + myY, myX);

    $.ajax({
        method: 'GET'
        , url: "https://dapi.kakao.com/v2/local/search/keyword.json"
        , data: {
            query: $("#restName").val() //사용자가 검색한 키워드
            , category_group_code: "FD6" //음식점 필터링
            , x: myX //중심좌표 X
            , y: myY //중심좌표 Y
            , sort: "distance" //거리순
        }
        , headers: {Authorization: "KakaoAK f1b2afc29adbbda05eea78825d075ca9"}
    })
        .done(function (data) {
            var getList = "";
            getList += "<table style='display:inline-block; border-collapse: separate; border-spacing: 0 10px;'>"

            for (var i = 0; i < data.documents.length; i++) {
                getList += "<tr>"
                getList += "<td> <a href='/detail?restaurant_id=" + data.documents[i].id + "' style='color:blue; text-decoration: none;'>" + data.documents[i].place_name + "</a></td>"
                getList += "<input type='hidden' value='" + data.documents[i].id + "'>"
                getList += "<input type='hidden' value='" + data.documents[i].place_name + "'>"
                getList += "<input type='hidden' value='" + data.documents[i].x + "'>"
                getList += "<input type='hidden' value='" + data.documents[i].y + "'>"

                getList += "<td>" + data.documents[i].phone + "</td>"
                getList += "<td>" + data.documents[i].category_name + "</td>"
                getList += "<td>" + data.documents[i].address_name + "</td>"
                getList += "<td>"
                getList += "<img src='like/default_like.png' id='likeimg" + i + "' width='50' height='50' onclick='javascript:saveData(" + i + ")' style='cursor: pointer'>"
                getList += "</td>"
                getList += "</tr>"
            }

            getList += "</table>"

            $("#restList").html("");
            $("#restList").html(getList);
        })
}


function enterkey() {
    if (window.event.keyCode == 13) {
        searchRes();
    }
}


function saveData(num) {
    var no = num * 5;
    var inputNum = num * 4 + 1;

    /*var str1 = document.getElementsByTagName('td')[no].childNodes[0].nodeValue; // id
    var str2 = document.getElementsByTagName('td')[no + 1].childNodes[0].nodeValue; // name*/
    var str3 = document.getElementsByTagName('td')[no + 1].childNodes[0].nodeValue; // phone
    var str4 = document.getElementsByTagName('td')[no + 3].childNodes[0].nodeValue; // address

    var str1 = document.getElementsByTagName('input')[inputNum + 1].value; // id
    var str2 = document.getElementsByTagName('input')[inputNum + 2].value; // name
    var x = document.getElementsByTagName('input')[inputNum + 3].value; // x
    var y = document.getElementsByTagName('input')[inputNum + 4].value; // y

    /*alert(x + ", "+ y);
    alert("ID: " + str1 + ", NAME: "+str2 + ", phone: "+str3 + ", 주소: "+str4);*/

    const dataStr = {
        "restaurant_id": str1,
        "restaurant_name": str2,
        "phone": str3,
        "x": x,
        "y": y,
        "address": str4
    }


    if (document.getElementById('likeimg' + num).src === "http://localhost:8084/like/default_like.png") {
        cnt += 1;

        $.ajax({
            method: 'POST',
            url: "/like",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(dataStr),
            success: function (data) {
                console.log("찜하기 성공");
            },
            error: function (xhr, status, error) {
                console.log("에러발생");
            }
        });

        document.getElementById('likeimg' + num).src = 'like/like.png'
    }

    else {
        $.ajax({
            method: 'GET',
            url: "/myLikeDelete",
            contentType: "application/json",
            dataType: "json",
            data: {'restaurant_id': str1},
            success: function (data) {
                // if(data.proc == "success") {
                //     console.log("DB 저장완료")
                // }
            },
            error: function (xhr, status, error) {
                console.log("에러발생");
            }
        });

        cnt = 0;
        document.getElementById('likeimg' + num).src = 'like/default_like.png'
    }

    tmpNum = num;
    location.href = "#";
}

function checkdata(str){
    $.ajax({
        method: 'POST',
        url: "/checklikes",
        contentType: "application/json",
        dataType: "json",
        data: {'restaurant_id': str},
        success: function (data) {
            // if(data.proc == "success") {
            //     console.log("DB 저장완료")
            // }
            return true;
        },
        error: function (xhr, status, error) {
            console.log("에러발생!!");
        }
    });
    return false;
}