function checkInfo(form){
    const name = form.name.value;
    const phone = form.phone.value;
    const cnt = form.cnt.value;
    const date = form.date.value;

    console.log("name: " + name + " phone: " + phone + " cnt: " + cnt + " date: " + date);

    if(name === "" || phone === "" || cnt === "" || date === ""){
        alert("정보를 빠짐없이 입력해주세요!");
    }
    else if(phone.substring(0,3) !== "010"){
        alert("올바른 핸드폰 형식을 입력하세요!")
    }
    else{
        alert("예약되었습니다.")
        form.submit();
    }
}