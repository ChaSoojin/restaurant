function checkBrowser(x,y, place_name){
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new kakao.maps.LatLng(y, x), // 지도의 중심좌표
            level: 3, // 지도의 확대 레벨
            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
        };

    // 지도생성
    var map = new kakao.maps.Map(mapContainer, mapOption);

    // 마커 생성
    var marker = new kakao.maps.Marker({
        position: new kakao.maps.LatLng(y,x)
    });

    // 기존에 마커가 있다면 제거
    marker.setMap(null);
    marker.setMap(map);
    makeInfoWindow(marker, place_name, map);
}

function makeInfoWindow(marker, place_name, map){
    var iwContent = '<div style="padding:5px;">' + place_name + '</div>',
        iwRemoveable = true; // removeable : ture 인포윈도우를 닫을 수 있는 x버튼표시

    // 인포윈도우 생성
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });

    infowindow.open(map, marker);  // 마커 위에 인포윈도우 표시
}