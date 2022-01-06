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
    <link rel="stylesheet" href="css/join.css">

    <script src = "script/join.js"></script>
    <title>Join</title>
</head>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%--<body>--%>
<section class="section section-lg">
    <div class="container">
        <div class="row">
            <div class="col-sm-10 col-md-9 col-lg-6 col-xl-5">
                <h6>sign up</h6>
                <!-- Bootstrap tabs -->
                <div class="tabs-custom tabs-horizontal tabs-line" id="tabs-1">
                    <div class="tab-content">
                        <div class="tab-pane fade show active in" id="tabs-1-1">
                            <form class="usrJoinForm" name="usrJoinForm" id="usrJoinForm" method="post">
                                <label class="form-label-outside">아이디</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="text" id="id" name="id" placeholder="영문자 또는 숫자 6~20자리 조합">
                                <span id="idCheck"></span>
                                <br>

                                <label class="form-label-outside">비밀번호</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="password" id="pw1" name="pw1" placeholder="영문자+숫자+특수문자 8~16자리 조합">
                                <span id="pwCheck"></span>
                                <br >

                                <label class="form-label-outside">비밀번호 확인</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="password" id="pw2" name="pw2" placeholder="비밀번호를 확인해주세요."> <br>

                                <label class="form-label-outside">이름</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="text" id="name" name="name" placeholder="이름을 입력해주세요.">
                                <br >



                                <label class="form-label-outside">일반회원</label>
                                <input  type="radio"  name="separate" value="1">
                                <br >
                                <br >
                                <label class="form-label-outside">사업자</label>
                                <input  type="radio"  name="separate" value="2">
                                <br >
                                <br >



                                <label class="form-label-outside">전화번호</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="text" id="phone" name="phone" placeholder="전화번호를 입력해주세요.">
                                <br >
                                <label class="form-label-outside">이메일</label>
                                <input class="form-input form-control-has-validation form-control-last-child" type="text" id="email" name="email" placeholder="메일주소를 입력해주세요.">
                                <span id="nameCheck"></span>
                                <br>
                                <input type="button" value="sign up" id="btn" onclick="join(form);">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


















<%--<div class="wrap">--%>

<%--    <main>--%>
<%--        <h1>JOIN</h1>--%>
<%--        <form>--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <td>아이디</td>--%>
<%--                    <td><input type="text" name="id" size="20" id="id">*--%>
<%--                    </td>--%>

<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>비밀번호</td>--%>
<%--                    <td><input type="password" name="pw1" size="20" id="pw">*</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>비밀번호 재확인</td>--%>
<%--                    <td><input type="password" name="pw2" size="20" id="pw2">*</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>이름</td>--%>
<%--                    <td><input type="text" name="name" size="20" id="name">*</td>--%>
<%--                </tr>--%>
<%--                <tr>--%>
<%--                    <td>일반회원</td>--%>
<%--                    <td><input type="radio" name="separate" value="1"></td>--%>
<%--                    <td>사업자</td>--%>
<%--                    <td> <input type="radio" name="separate" value="2"></td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>폰번호</td>--%>
<%--                    <td><input type="text" name="phone" size="20" id="phone">*</td>--%>
<%--                </tr>--%>

<%--                <tr>--%>
<%--                    <td>email</td>--%>
<%--                    <td><input type="text" name="email" size="20" id="email">*</td>--%>
<%--                </tr>--%>

<%--            </table>--%>
<%--            <div id="wrapBtn">--%>
<%--                &lt;%&ndash;<input type="submit" value="확인" id="btn" >&ndash;%&gt;--%>
<%--                <input type="button" value="확인" id="btn" onclick="join(form);">--%>
<%--            </div>--%>
<%--        </form>--%>
<%--    </main>--%>
<%--    <nav></nav>--%>
<%--    <aside></aside>--%>
<%--</div>--%>
<%--</body>--%>
</html>
<c:import url="/WEB-INF/views/layout/footer.jsp"/>