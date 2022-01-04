<%--
  Created by IntelliJ IDEA.
  User: kyb
  Date: 2022-01-02
  Time: 오전 12:20
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script type="text/javascript" src="script/validation.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src = "js/login.js"></script>
    <title>Title</title>
</head>

<body>
<div class="wrap">
    <header></header>
    <main>
        <H1>LOGIN</H1>
        <%--        <form action="/v1/users/loginForm" method="post">--%>
        <form action="loginProM" method="post">
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="id" id= "id" size="20"></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="pw" id="pw" size="20"></td>
                </tr>

            </table>
            <%--            <div id="wrapBtn">--%>
            <%--<input type="submit" value="확인" id="btn" >--%>
            <input type="submit" value="확인" id="btn">
            <%--            </div>--%>
        </form >
    </main>
    <nav></nav>
    <aside></aside>
</div>
<footer></footer>
</body>
</html>