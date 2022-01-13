let tmpKeyWord = "";
let cnt = 0;

function restaurantSearch(){
    $.ajax({
        method : 'GET'
        , url : "https://dapi.kakao.com/v2/local/search/keyword.json"
        , data : {
            query : $("#restName").val() //사용자가 검색한 키워드
            , category_group_code : "FD6" //음식점 필터링
            , sort : "accuracy" //정확도 순
            , size : 9} //사이즈 9개까지만
        , headers : {Authorization : "KakaoAK f1b2afc29adbbda05eea78825d075ca9"}
    })
        .done(function(data) {
            var num = 0;

            if($("#restName").val() !== "" && tmpKeyWord !== $("#restName").val()){
                $('h5').remove();
                $('p').remove();
                $('img').remove();
            }

            tmpKeyWord = $("#restName").val();

            for(var i=0; i<data.documents.length; i++) {

                let conArea = document.querySelector('div#con');
                const content = document.createElement('div');

                if(i < 3 && i % 3 == i){
                    content.setAttribute("class", "box content" + i);
                }
                else if(i >= 3 && i % 3 == i - 3){
                    content.setAttribute("class", "box content" + i);
                }
                else if(i >= 6 && i % 3 == i - 6){
                    content.setAttribute("class", "box content" + i);
                }

                $(conArea).append(content);

                $(content).append("<p id='place_id' name='id'>" + data.documents[i].id + "</p>");
                $(content).append("<p id='place_name'>" + data.documents[i].place_name + "</p>");
                $(content).append("<p id='address'>" + data.documents[i].address_name + "</p>");
                $(content).append("<p id='phone'>" + data.documents[i].phone + "</p>");

                $(content).append("<input type='hidden' value='" + data.documents[i].x + "'>");
                $(content).append("<input type='hidden' value='" + data.documents[i].y + "'>");
                $(content).append("<h5 id='x'>" + data.documents[i].x + "</h5>");

                /*console.log(checkData(data.documents[i]));*/

                $(content).append("<img src='like/default_like.png' id='likeimg" + num + "' width='50' height='50' onclick='javascript:saveData(" + num + ")' style='cursor: pointer'>");

                console.log(i + ","+data.documents[i].id);
                // 식당 데이터 save
                num += 1;
            }

            $("#restList").html("");
        })
}
function checkData(datas){
    $.ajax({
        type : 'POST',
        url : "/checklikes",
        contentType : "application/x-www-form-urlencoded; charset=UTF-8",
        dataType : "json",
        data : {'restaurant_id': datas.id, 'restaurant_name': datas.place_name, 'phone' : datas.phone, 'x' : datas.x, 'y' : datas.y, 'address' : datas.address_name},
        success : function(data) {
            if(data.proc == "success") {
                console.log("체크완료")
            }
        },
        error : function(xhr, status, error) {
            console.log("에러발생");
        }
    });
}

function enterkey() {
    if (window.event.keyCode == 13) {
        restaurantSearch();
    }
}

//조회한 맛집 데이터 DB저장
function saveData(num){
    var no = num * 4;
    var inputNum = num * 2 + 1 ;
    // alert("num:"+num);
    // alert("no:"+no);
    var str1 = document.getElementsByTagName('p')[no].childNodes[0].nodeValue; // id
    var str2 = document.getElementsByTagName('p')[no+1].childNodes[0].nodeValue; // name
    var str3 = document.getElementsByTagName('p')[no+2].childNodes[0].nodeValue; // address
    var str4 = document.getElementsByTagName('p')[no+3].childNodes[0].nodeValue; // phone

    var x = document.getElementsByTagName('input')[inputNum].value; // x
    var y = document.getElementsByTagName('input')[inputNum +1].value; // y

   /* alert(x + ", "+ y);
    alert(str1 + ", "+str2 + ", "+str3 + ", "+str4);*/

    const dataStr = {
        "restaurant_id": str1,
        "restaurant_name": str2,
        "phone" : str4,
        "x" : x,
        "y" : y,
        "address" : str3
    }

    if(cnt == 0){
        cnt += 1;

        $.ajax({
            method : 'POST',
            url : "/like",
            contentType : "application/json",
            dataType : "json",
            data : JSON.stringify(dataStr),
            success : function(data) {
                // if(data.proc == "success") {
                //     console.log("DB 저장완료")
                // }
            },
            error : function(xhr, status, error) {
                console.log("에러발생");
            }
        });

        document.getElementById('likeimg' + num).src = 'like/like.png'
    }

    else{
        $.ajax({
            method : 'GET',
            url : "/myLikeDelete",
            contentType : "application/json",
            dataType : "json",
            data : {'restaurant_id': str1},
            success : function(data) {
                // if(data.proc == "success") {
                //     console.log("DB 저장완료")
                // }
            },
            error : function(xhr, status, error) {
                console.log("에러발생");
            }
        });


        document.getElementById('likeimg' + num).src = 'like/default_like.png'
        cnt = 0;
    }

    location.href="#";
}