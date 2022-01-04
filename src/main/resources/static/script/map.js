var mylat;
var mylong;
var markers = [];

var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new kakao.maps.LatLng(37.56646, 126.98121), // 지도의 중심좌표
        level: 3, // 지도의 확대 레벨
        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
    };

// 지도생성
var map = new kakao.maps.Map(mapContainer, mapOption);

function locationLoadSuccess(pos){
    // 현재 위치 받아오기
    var currentPos = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude);

    mylat = pos.coords.latitude;
    mylong= pos.coords.longitude;

    console.log("현재위치 : " + currentPos + " 타입: " + typeof currentPos);
    restaurantSearch(mylat, mylong);

    // 지도 이동
    map.panTo(currentPos);

    var imageSrc = 'img/home.png',
        imageSize = new kakao.maps.Size(64, 69), // 마커이미지 크기
        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지 옵션 마커의 좌표와 일치시킬 이미지 안에서 좌표설정

    // 마커이미지 생성
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
        markerPosition = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude); // 마커가 표시될 위치

    // 마커 생성
    var marker = new kakao.maps.Marker({
        position: currentPos,
        image: markerImage // 마커이미지 설정
    });

    // 기존에 마커가 있다면 제거
    marker.setMap(null);
    setMarkers(null);

    marker.setMap(map);
};

//마커삭제
function setMarkers(map){
    for (var i = 0; i < markers.length; i++) {
        markers[i].setMap(map);
    }
}

function locationLoadError(pos){
    alert('위치 정보를 가져오는데 실패했습니다.');
};

// 검색 버튼 클릭 시
function search(){
    navigator.geolocation.getCurrentPosition(locationLoadSuccess,locationLoadError);
};

// 맛집 키워드 검색
function restaurantSearch(myY, myX){
    console.log(myY, myX);

    $.ajax({
        method : 'GET'
        , url : "https://dapi.kakao.com/v2/local/search/keyword.json"
        , data : {
            query : $("#restName").val() //사용자가 검색한 키워드
            , category_group_code : "FD6" //음식점 필터링
            , x : myX //중심좌표 X
            , y : myY //중심좌표 Y
            , sort : "distance" //거리순
            }
        , headers : {Authorization : "KakaoAK f1b2afc29adbbda05eea78825d075ca9"}
    })
        .done(function(data) {
            var getList = "";
            getList += "<table style='display:inline-block; border-collapse: separate; border-spacing: 0 10px;'>"

            for(var i=0; i<data.documents.length; i++) {
                getList += "<tr>"
                getList += "<td> <a href='/detail?restaurant_id=" + data.documents[i].id + "' style='text-decoration: none'>" + data.documents[i].place_name + "</a></td>"
                getList += "<td>" + data.documents[i].phone + "</td>"
                getList += "<td>" + data.documents[i].category_name + "</td>"
                getList += "<td>" + data.documents[i].address_name + "</td>"
                getList += "<td>"
                getList += "<a href='/restaurantReserve?restaurant_id=" + data.documents[i].id + "'><button style='padding:13px 13px;'>예약하기</button></a>"
                getList += "<a href='#'><button style='padding:13px 13px;'>리뷰작성</button></a>"
                getList += "</td>"
                getList += "</tr>"

                //식당위치 마커표시
                var pos = new kakao.maps.LatLng(data.documents[i].y,data.documents[i].x);
                var marker = new kakao.maps.Marker({
                    position: pos,
                    clickable: true // 마커 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록
                });

                marker.setMap(map);
                markers.push(marker); //markers 배열에 push
                saveData(data.documents[i]);

                makeInfoWindow(marker, data.documents[i].place_name, data.documents[i].phone);
            }

            getList += "</table>"

            $("#restList").html("");
            $("#restList").html(getList);
        })
}

// 마커 클릭했을 때 마커 위에 표시할 인포윈도우 생성
function makeInfoWindow(marker, place_name, phone){
    var iwContent = '<div style="padding:5px;">' + place_name + '<br>' + phone + '</div>',
        iwRemoveable = true; // removeable : ture 인포윈도우를 닫을 수 있는 x버튼표시

    // 인포윈도우 생성
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    // 마커에 클릭이벤트 등록
    kakao.maps.event.addListener(marker, 'click', function() {
        infowindow.open(map, marker);  // 마커 위에 인포윈도우 표시
    });
}

function enterkey() {
    if (window.event.keyCode == 13) {
        search();
    }
}

//조회한 맛집 데이터 DB저장
function saveData(datas){
    $.ajax({
        type : 'POST',
        url : "/restaurantSearch",
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        dataType : "json",
        data : {'restaurant_id': datas.id, 'restaurant_name': datas.place_name, 'phone' : datas.phone, 'x' : datas.x, 'y' : datas.y, 'address' : datas.address_name},
        success : function(data) {
            if(data.proc == "success") {
                console.log("DB 저장완료")
            }
        },
        error : function(xhr, status, error) {
            console.log("에러발생");
        }
    });
}