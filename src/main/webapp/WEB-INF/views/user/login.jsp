<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" pageEncoding="utf-8" contentType="text/html; ISO-8859-1" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>
<!doctype html>
<html lang="en">

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
<c:set var="path" value="${pageContext.request.contextPath}" />

<section class="section section-lg">
    <div class="container">
        <div class="row">
            <div class="col-sm-10 col-md-9 col-lg-6 col-xl-5">
                <h6>sign in</h6>
                <!-- Bootstrap tabs -->
                <div class="tabs-custom tabs-horizontal tabs-line" id="tabs-1">
                    <div class="tab-content">
                        <div class="tab-pane fade show active in" id="tabs-1-1">
                            <form class="usrLoginForm" name="usrLoginForm" id="usrLoginForm" action="loginProM" method="post">
                                <label class="form-label-outside">아이디</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="text" id="id" name="id" placeholder="영문(대소문자) 또는 숫자 4~12자리 조합">
                                <span id="idCheck"></span>
                                <br>

                                <label class="form-label-outside">비밀번호</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="password" id="pw" name="pw" placeholder="영문(대소문자) 또는 숫자 4~12자리 조합">
                                <span id="pwCheck"></span>
                                <br >


                                <input type="submit" value="sign in" id="btn">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
































<%--<body>--%>
<%--<div class="wrap">--%>
<%--    <header></header>--%>
<%--    <main>--%>
<%--        <H1>LOGIN</H1>--%>
<%--        &lt;%&ndash;        <form action="/v1/users/loginForm" method="post">&ndash;%&gt;--%>
<%--        <form action="loginProM" method="post">--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td>아이디</td>--%>
<%--                    <td><input type="text" name="id" id= "id" size="20"></td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>비밀번호</td>--%>
<%--                    <td><input type="password" name="pw" id="pw" size="20"></td>--%>
<%--                </tr>--%>

<%--            </table>--%>

<%--            <input type="submit" value="확인" id="btn">--%>

<%--        </form >--%>
<%--    </main>--%>
<%--    <nav></nav>--%>
<%--    <aside></aside>--%>
<%--</div>--%>
<%--<footer></footer>--%>
<%--</body>--%>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>