<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">

    <title></title>
</head>

<body>


<%String logValue=(String)session.getAttribute("log");%>
<c:out value="${loginUser.getPw()}"/>
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

        <input type="hidden" name=id value="<%=logValue%>">
    </div>
</form>

</body>
</html>