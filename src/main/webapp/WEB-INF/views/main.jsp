<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<c:import url="/WEB-INF/views/layout/header.jsp"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>MAIN페이지</title>
</head>
<body>
<!-- Slider Light-->
<section class="swiper-container swiper-slider swiper-slider-light bg-image-1" data-loop="false" data-autoplay="false" data-simulate-touch="false" data-custom-slide-effect="inter-leave-effect" data-inter-leave-offset="-.5">
    <div class="swiper-wrapper">
        <div class="swiper-slide">
            <div class="slide-inner">
                <div class="container">
                    <div class="swiper-slide-caption">
                        <div class="row row-30">
                            <div class="col-lg-6 text-center text-lg-left">
                                <h1><span class="font-weight-light"><span></span></span><span class="font-weight-bold"><span>맛집검색</span></span></h1>
                                <div class="button-outer"><a class="button button-lg button-primary button-winona" href="<c:url value='/restaurantSearch'/>">맛집검색</a></div>
                            </div>
                            <div class="col-lg-6 position-static">
                                <c:url var="imagePath2" value="/images/bg-image-2.jpg"/>
                                <div class="floating-image" style="background-image: url(${imagePath2});"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="swiper-slide">
            <div class="slide-inner">
                <div class="container">
                    <div class="swiper-slide-caption">
                        <div class="row row-30">
                            <div class="col-lg-6 text-center text-lg-left">
                                <h1><span class="font-weight-light"><span></span></span><span class="font-weight-bold"><span>리뷰보러가기</span></span></h1>
                                <div class="button-outer"><a class="button button-lg button-primary button-winona" href="<c:url value='/list'/>">Free Review</a></div>
                            </div>
                            <div class="col-lg-6 position-static">
                                <c:url var="imagePath3" value="/images/bg-image-3.jpg"/>
                                <div class="floating-image" style="background-image: url(${imagePath3});"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="swiper-pagination-outer container">
        <div class="swiper-pagination swiper-pagination-modern swiper-pagination-marked" data-index-bullet="true"></div>
    </div>
</section>
</body>
</html>

<c:import url="/WEB-INF/views/layout/footer.jsp"/>