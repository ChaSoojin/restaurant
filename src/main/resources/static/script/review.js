sessionStorage.setItem("log",'apple');

function loginCheck(){
    const id = sessionStorage.getItem("log");

    if(id === "" || id === null){
        alert("로그인을 먼저 해주세요.");
    }else{
        // location.href = "reviewWrite";
        location.href = "/list";
    }
}
function ownerCheck(){
    const id = sessionStorage.getItem("log");

    if(id === "" || id === null){
        alert("로그인을 먼저 해주세요.");
    }
        // else if(""){
        //     alert("사장님 페이지 입니다.");
    // }
    else{
        location.href = "/ownerPage";
    }
}