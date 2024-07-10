<%-- 
    Document   : Home
    Created on : May 15, 2024, 9:56:38 PM
    Author     : huyca
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>

    <head>
        <title>ShopBook88-SB88</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <style>
            .swiper-container {
                width: 100%;
                height: 900px;
            }
            .swiper-slide {
                display: flex;
                justify-content: center;
                align-items: center;
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center;
                height: auto;
                width: 10000px;
            }
            .swiper-button-next, .swiper-button-prev {
                color: #fff;
                width: 50px;
                height: 50px;
                background-color: rgba(0, 0, 0, 0.5); /* Adjust the background color as needed */
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .swiper-button-next::after, .swiper-button-prev::after {
                font-size: 20px;
                #customers-reviews {
                    padding-top: 50px;
                    padding-bottom: 50px;
                }

                .swiper-next, .swiper-prev {
                    cursor: pointer;
                }

                .card {
                    background-color: rgba(255, 255, 255, 0.8); /* Slightly transparent background */
                }

                .star {
                    width: 20px;
                    height: 20px;
                    margin-right: 5px;
                }
            }
        </style>

        <style>
            .custom-time {
                display: inline-block;
            }
            .custom-fs-1 {
                font-size: 2rem;
            }
            .custom-fw-normal {
                font-weight: normal;
            }
        </style>
    </head>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            // Set the date we're counting down to
            var countDownDate = new Date("${date}").getTime();

            // Update the count down every 1 second
            var x = setInterval(function () {
                // Get today's date and time
                var now = new Date().getTime();

                // Find the distance between now and the count down date
                var distance = countDownDate - now;

                // Time calculations for days, hours, minutes and seconds
                var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                // Display the result in the elements with class "custom-days", "custom-hours", "custom-minutes", "custom-seconds"
                document.querySelector('.custom-days').innerText = days;
                document.querySelector('.custom-hours').innerText = hours;
                document.querySelector('.custom-minutes').innerText = minutes;
                document.querySelector('.custom-seconds').innerText = seconds;

                // If the count down is over, write some text and hide the promo code
                if (distance < 0) {
                    clearInterval(x);
                    document.getElementById("countdown-clocd").innerHTML = "Hết thời gian sử dụng";
                    document.getElementById("promo-code").style.display = "none";
                }
            }, 1000);
        });
    </script>
    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>

            <br> <br> <br><br><br>
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide" style="background-image: url('https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img1.jpg?v=1036')">

                    </div>

                    <div class="swiper-slide" style="background-image: url('https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img2.jpg?v=1036')">

                    </div>
                    <div class="swiper-slide" style="background-image: url('https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img3.jpg?v=1036')">

                    </div>
                    <div class="swiper-slide" style="background-image: url('https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img5.jpg?v=1036')">

                    </div>
                    <div class="swiper-slide" style="background-image: url('https://theme.hstatic.net/200000343865/1001052087/14/ms_banner_img4.jpg?v=1036')">

                    </div>






                </div>


                <div class="swiper-button-next"></div>
                <div class="swiper-button-prev"></div>
            </div>


            <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
            <script>
                var swiper = new Swiper('.swiper-container', {
                    loop: true,
                    navigation: {
                        nextEl: '.swiper-button-next',
                        prevEl: '.swiper-button-prev',
                    },
                    autoplay: {
                        delay: 5000,
                        disableOnInteraction: false,
                    },
                });
            </script>






            <section id="company-services" class="padding-large pb-0">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-6 pb-3 pb-lg-0">
                            <div class="icon-box d-flex">
                                <div class="icon-box-icon pe-3 pb-3">
                                    <svg class="cart-outline">
                                    <use xlink:href="#cart-outline" />
                                    </svg>
                                </div>
                                <div class="icon-box-content">
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Giao hàng miễn phí</h4>
                                    <p>Đóng gói cẩn thận</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 pb-3 pb-lg-0">
                            <div class="icon-box d-flex">
                                <div class="icon-box-icon pe-3 pb-3">
                                    <svg class="quality">
                                    <use xlink:href="#quality" />
                                    </svg>
                                </div>
                                <div class="icon-box-content">
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Đảm bảo chất lượng</h4>
                                    <p>Được kiểm tra khi giao hàng</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 pb-3 pb-lg-0">
                            <div class="icon-box d-flex">
                                <div class="icon-box-icon pe-3 pb-3">
                                    <svg class="price-tag">
                                    <use xlink:href="#price-tag" />
                                    </svg>
                                </div>
                                <div class="icon-box-content">
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Ưa đãi khủng</h4>
                                    <p>Giá cả hạt dẻ</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-6 pb-3 pb-lg-0">
                            <div class="icon-box d-flex">
                                <div class="icon-box-icon pe-3 pb-3">
                                    <svg class="shield-plus">
                                    <use xlink:href="#shield-plus" />
                                    </svg>
                                </div>
                                <div class="icon-box-content">
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Thanh toán an toàn 100%</h4>
                                    <p>Làm cho khách hàng hài lòng nhất</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="best-selling-items" class="position-relative padding-large ">
                <div class="container">
                    <div class="section-title d-md-flex justify-content-between align-items-center mb-4">
                        <h3 class="d-flex align-items-center">Được mua nhiều nhất</h3>
                        <a href="shop" class="btn">Hiển thi tất cả sách</a>

                    </div>
                    <div class="position-absolute top-50 end-0 pe-0 pe-xxl-5 me-0 me-xxl-5 swiper-next product-slider-button-next">
                        <svg class="chevron-forward-circle d-flex justify-content-center align-items-center p-2" width="80" height="80">
                        <use xlink:href="#alt-arrow-right-outline"></use>
                        </svg>
                    </div>
                    <div class="position-absolute top-50 start-0 ps-0 ps-xxl-5 ms-0 ms-xxl-5 swiper-prev product-slider-button-prev">
                        <svg class="chevron-back-circle d-flex justify-content-center align-items-center p-2" width="80" height="80">
                        <use xlink:href="#alt-arrow-left-outline"></use>
                        </svg>
                    </div>
                    <div class="swiper product-swiper">
                        <div class="swiper-wrapper"> 
                        <fmt:setLocale value="vi_VN" />
                        <c:forEach items="${data1}" var="d">
                            <div class="swiper-slide">
                                <a href="single?productID=${d.productId}">

                                    <div class="card position-relative p-4 border rounded-3">

                                        <img src="${d.imgProduct}" class="img-fluid shadow-sm" alt="product item">
                                        <h6 class="mt-4 mb-0 fw-bold"><a href="single?productID=${d.productId}">${d.name}</a></h6>
                                        <div class="review-content d-flex">                        
                                            <div class="rating text-warning d-flex align-items-center">
                                                <c:forEach begin="1" end="5">
                                                    <svg class="star star-fill">
                                                    <use xlink:href="#star-fill"></use>
                                                    </svg>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <span class="price text-primary fw-bold mb-2 fs-5">
                                            <fmt:formatNumber value="${d.price}" type="currency" currencySymbol="₫" groupingUsed="true" />

                                        </span>

                                        <!--                                    <div class="card-concern position-absolute start-0 end-0 d-flex gap-2">
                                                                                <button type="button" href="#" class="btn btn-dark" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Tooltip on top">
                                                                                    <svg class="cart">
                                                                                    <use xlink:href="#cart"></use>
                                                                                    </svg>
                                                                                </button>
                                        
                                                                            </div>-->
                                    </div>
                                </a>
                            </div>

                        </c:forEach>

                    </div>
                </div>





            </div>
        </section>

        <section id="limited-offer" class="padding-large" style="background-image: url(images/banner-image-bg-1.jpg); background-size: cover; background-repeat: no-repeat; background-position: center; height: 800px;">
            <div class="container">
                <div class="row d-flex align-items-center">
                    <div class="col-md-6 text-center">
                        <div class="image-holder">
                            <img src="${pageContext.request.contextPath}/images/banner-image3.png" class="img-fluid" alt="banner">
                        </div>
                    </div>
                    <div class="col-md-5 offset-md-1 mt-5 mt-md-0 text-center text-md-start">
                        <h2>Lấy mã không hết thời gian bạn ơi. Hurry Up !!!</h2>
                        <div id="countdown-clocd" class="text-dark d-flex align-items-center my-3">
                            <div class="custom-time d-grid pe-3">
                                <span class="custom-days custom-fs-1 custom-fw-normal"></span>
                                <small>Days</small>
                            </div>
                            <span class="custom-fs-1 text-primary">:</span>
                            <div class="custom-time d-grid pe-3 ps-3">
                                <span class="custom-hours custom-fs-1 custom-fw-normal"></span>
                                <small>Hrs</small>
                            </div>
                            <span class="custom-fs-1 text-primary">:</span>
                            <div class="custom-time d-grid pe-3 ps-3">
                                <span class="custom-minutes custom-fs-1 custom-fw-normal"></span>
                                <small>Min</small>
                            </div>
                            <span class="custom-fs-1 text-primary">:</span>
                            <div class="custom-time d-grid ps-3">
                                <span class="custom-seconds custom-fs-1 custom-fw-normal"></span>
                                <small>Sec</small>
                            </div>
                        </div>
                        <div id="promo-code">
                            <c:forEach items="${codediscount}" var="c">
                                ${c.codeName}<br>

                            </c:forEach>
                        </div>
                        <a href="shop" class="btn mt-3 btn-primary">Shop Collection</a>


                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="items-listing" class="padding-large">
        <div class="container">
            <div class="row">
                <h3 class="d-flex align-items-center">Sách mới</h3><br><br><br>
                <div class="col-md-6 mb-4 mb-lg-0 col-lg-3">

                    <div class="featured border rounded-3 p-4">



                        <div class="section-title overflow-hidden mb-5 mt-2">
                            <h3 class="d-flex flex-column mb-0">Văn Học Việt Nam</h3>
                        </div>

                        <c:forEach items="${data01}" var="d">
                            <hr class="gray-400">

                            <div class="items-lists">


                                <div class="item d-flex">
                                    <img src="${d.imgProduct}" class="img-fluid shadow-sm" alt="product item">


                                    <div class="item-content ms-3">
                                        <h6 class="mb-0 fw-bold"><a href="single?productID=${d.productId}">${d.name}</a></h6>


                                        <div class="review-content d-flex">
                                            <p class="my-2 me-2 fs-6 text-black-50"></p>

                                            <div class="rating text-warning d-flex align-items-center">
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                            </div>


                                        </div>


                                        <span class="price text-primary fw-bold mb-2 fs-5">

                                            <fmt:formatNumber value="${d.price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                        </span>


                                    </div>

                                </div>   
                            </div>
                            <hr>
                        </c:forEach>                                        
                    </div>
                </div>

                <div class="col-md-6 mb-4 mb-lg-0 col-lg-3">
                    <div class="latest-items border rounded-3 p-4">
                        <div class="section-title overflow-hidden mb-5 mt-2">
                            <h3 class="d-flex flex-column mb-0">Văn Học Nước Ngoài</h3>
                        </div>

                        <c:forEach items="${data02}" var="d">
                            <hr class="gray-400">
                            <div class="items-lists">


                                <div class="item d-flex">
                                    <img src="${d.imgProduct}" class="img-fluid shadow-sm" alt="product item">


                                    <div class="item-content ms-3">
                                        <h6 class="mb-0 fw-bold"><a href="single?productID=${d.productId}">${d.name}</a></h6>


                                        <div class="review-content d-flex">
                                            <p class="my-2 me-2 fs-6 text-black-50"></p>

                                            <div class="rating text-warning d-flex align-items-center">
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                            </div>


                                        </div>


                                        <span class="price text-primary fw-bold mb-2 fs-5">

                                            <fmt:formatNumber value="${d.price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                        </span>


                                    </div>

                                </div>   
                            </div>
                            <hr>
                        </c:forEach>                      
                    </div>
                </div>



                <div class="col-md-6 mb-4 mb-lg-0 col-lg-3">
                    <div class="best-reviewed border rounded-3 p-4">
                        <div class="section-title overflow-hidden mb-5 mt-2">
                            <h3 class="d-flex flex-column mb-0">Lịch Sử Truyền Thống</h3>
                        </div>


                        <c:forEach items="${data03}" var="d">
                            <hr class="gray-400">
                            <div class="items-lists">


                                <div class="item d-flex">
                                    <img src="${d.imgProduct}" class="img-fluid shadow-sm" alt="product item">


                                    <div class="item-content ms-3">
                                        <h6 class="mb-0 fw-bold"><a href="single?productID=${d.productId}">${d.name}</a></h6>


                                        <div class="review-content d-flex">
                                            <p class="my-2 me-2 fs-6 text-black-50"></p>

                                            <div class="rating text-warning d-flex align-items-center">
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                            </div>


                                        </div>


                                        <span class="price text-primary fw-bold mb-2 fs-5">

                                            <fmt:formatNumber value="${d.price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                        </span>


                                    </div>

                                </div>   
                            </div>
                            <hr>
                        </c:forEach>  

                    </div>
                </div>
                <div class="col-md-6 mb-4 mb-lg-0 col-lg-3">
                    <div class="on-sale border rounded-3 p-4">
                        <div class="section-title overflow-hidden mb-5 mt-2">
                            <h3 class="d-flex flex-column mb-0">Kiến Thức Khoa học</h3>
                        </div>


                        <c:forEach items="${data04}" var="d">
                            <hr class="gray-400">
                            <div class="items-lists">


                                <div class="item d-flex">
                                    <img src="${d.imgProduct}" class="img-fluid shadow-sm" alt="product item">


                                    <div class="item-content ms-3">
                                        <h6 class="mb-0 fw-bold"><a href="single?productID=${d.productId}">${d.name}</a></h6>


                                        <div class="review-content d-flex">
                                            <p class="my-2 me-2 fs-6 text-black-50"></p>

                                            <div class="rating text-warning d-flex align-items-center">
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                                <svg class="star star-fill">
                                                <use xlink:href="#star-fill"></use>
                                                </svg>
                                            </div>


                                        </div>


                                        <span class="price text-primary fw-bold mb-2 fs-5">

                                            <fmt:formatNumber value="${d.price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                        </span>


                                    </div>

                                </div>   
                            </div>
                            <hr>
                        </c:forEach>  

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</section>

<section id="categories" class="padding-large pt-0">
    <div class="container">
        <div class="section-title overflow-hidden mb-4">
            <h3 class="d-flex align-items-center">Dành cho giới trẻ</h3>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="card mb-4 border-0 rounded-3 position-relative">
                    <a href="shop?categoryId=1">
                        <img src="https://th.bing.com/th/id/R.d0ff0068b9ac6780b042d124858333b6?rik=snO0WmgWevkfuw&pid=ImgRaw&r=0" class="img-fluid rounded-3" alt="cart item">
                        <h6 class=" position-absolute bottom-0 bg-primary m-4 py-2 px-3 rounded-3"><a href="shop?categoryId=1"
                                                                                                      class="text-white">Truyện tranh</a></h6>
                    </a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center mb-4 border-0 rounded-3">
                    <a href="shop?categoryId=4">
                        <img src="https://th.bing.com/th/id/OIP.lBn9hgk_oIGHmOPOKPbdJAHaEo?rs=1&pid=ImgDetMain" class="img-fluid rounded-3" alt="cart item">
                        <h6 class=" position-absolute bottom-0 bg-primary m-4 py-2 px-3 rounded-3"><a href="shop?categoryId=4"
                                                                                                      class="text-white">Lịch sử Việt Nam</a></h6>
                    </a>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center mb-4 border-0 rounded-3">
                    <a href="shop?categoryId=8">
                        <img src="https://th.bing.com/th/id/R.83bb7e61183ff0fb90cf6ba00e1d000e?rik=Q%2fE2L%2bsaF24JFA&riu=http%3a%2f%2fstatic.kites.vn%2farticle%2f2021%2f02%2f26%2f1614305426_547a3603d7cf.jpg&ehk=UOJiQtm%2beL4uiPvyILOkGo52i%2bEnp9cal5ff0Mm3DU8%3d&risl=&pid=ImgRaw&r=0" class="img-fluid rounded-3" alt="cart item">
                        <h6 class=" position-absolute bottom-0 bg-primary m-4 py-2 px-3 rounded-3"><a href="shop?categoryId=8"
                                                                                                      class="text-white">Giải mã bản thân</a></h6>
                    </a>
                </div>
            </div>
        </div>
    </div>
</section>

<section id="customers-reviews" class="position-relative padding-large" style="background-image: url(images/banner-image-bg.jpg); background-size: cover; background-repeat: no-repeat; background-position: center; height: 600px;">
    <div class="container offset-md-3 col-md-6 ">
        <div class="position-absolute top-50 end-0 pe-0 pe-xxl-5 me-0 me-xxl-5 swiper-next testimonial-button-next">
            <svg class="chevron-forward-circle d-flex justify-content-center align-items-center p-2" width="80" height="80">
            <use xlink:href="#alt-arrow-right-outline"></use>
            </svg>
        </div>
        <div class="position-absolute top-50 start-0 ps-0 ps-xxl-5 ms-0 ms-xxl-5 swiper-prev testimonial-button-prev">
            <svg class="chevron-back-circle d-flex justify-content-center align-items-center p-2" width="80" height="80">
            <use xlink:href="#alt-arrow-left-outline"></use>
            </svg>
        </div>
        <div class="section-title mb-4 text-center">
            <h3 class="mb-4">Đánh giá từ khách hàng</h3>
        </div>
        <div class="swiper testimonial-swiper">
            <div class="swiper-wrapper">
                <c:forEach var="feedback" items="${requestScope.listMostRating}">
                    <div class="swiper-slide d-flex justify-content-center" style="height:300px">
                        <div class="card position-relative text-left p-5 border rounded-3 d-flex flex-row" style="width: 90%; max-width: 900px;">
                            <div class="col-lg-8">
                                <blockquote style="height:150px; overflow: auto">"${feedback.comments}"</blockquote>
                                <div class="rating text-warning d-flex align-items-center">
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                </div>
                                <h5 class="mt-1 fw-normal">${feedback.account.fullName}</h5>
                            </div>
                            <div class="col-lg-4 d-flex flex-column align-items-center justify-content-center">
                                <a href="single?productID=${feedback.product.productId}" style="margin-left: 30px"><img src="${feedback.product.imgProduct}" width="75%" height="auto" alt="Product Image" /><h6 style="font-size: 10px; margin-top: 5px">${feedback.product.name}</h6></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</section>


<section id="latest-posts" class="padding-large">
    <div class="container">
        <div class="section-title d-md-flex justify-content-between align-items-center mb-4">
            <h3 class="d-flex align-items-center">Tin tức mới nhất</h3>
            <a href="blog" class="btn">Xem tất cả</a>
        </div>
        <div class="row">
            <!-- NEWS -->
            <c:forEach var="n" items="${requestScope.news}">
                <div class="col-md-3 posts mb-4">
                    <img src="${n.imgNews}" alt="post image" class="img-fluid rounded-3">
                    <a href="blog" class="fs-6 text-primary">${n.topic.topicName}</a>
                    <h4 class="card-title mb-2 text-capitalize text-dark"><a href="post?id=${n.newId}">${n.title}</a></h4>
                    <p class="mb-2" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient:vertical; overflow: hidden ">${n.content} <span><a class="text-decoration-underline text-black-50" href="post?id=${n.newId}">Read More</a></span></p>
                </div>
            </c:forEach>
        </div>
    </div>
</section>

<section id="instagram">
    <div class="container">
        <div class="text-center mb-4">
            <h3>Instagram</h3>
        </div>
        <div class="row">
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href="https://www.instagram.com/p/C7d6aa0vQ4P/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==" class="image-link position-relative">
                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item1.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href="https://www.instagram.com/p/C7d6rGdv9lN/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==" class="image-link position-relative">
                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item2.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href="https://www.instagram.com/p/C7d62jNv9Ps/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==" class="image-link position-relative">
                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item3.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href=" https://www.instagram.com/p/C7d7AipPB9i/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==" class="image-link position-relative">

                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item4.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href="https://www.instagram.com/p/C7d7NzpvVPC/?utm_source=ig_web_copy_link&igsh=MzRlODBiNWFlZA==" class="image-link position-relative">

                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item5.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
            <div class="col-md-2">
                <figure class="instagram-item position-relative rounded-3">
                    <a href="https://www.instagram.com/p/C7d7Thrv6zW/?utm_source=ig_web_copy_link_" class="image-link position-relative">
                        <div class="icon-overlay position-absolute d-flex justify-content-center">
                            <svg class="instagram">
                            <use xlink:href="#instagram"></use>
                            </svg>
                        </div>
                        <img src="${pageContext.request.contextPath}/images/insta-item6.jpg" alt="instagram" class="img-fluid rounded-3 insta-image">
                    </a>
                </figure>
            </div>
        </div>
    </div>
</section>

<footer id="footer" class="padding-large">
    <div class="container">
        <div class="row">
            <div class="footer-top-area">
                <div class="row d-flex flex-wrap justify-content-between">
                    <div class="col-lg-3 col-sm-6 pb-3">
                        <div class="footer-menu">
                            <img src="images/anh456.png" alt="logo" class="img-fluid mb-2">
                            <p>
                                "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người ta càng thấy còn nhiều điều cần phải đọc.” John Adams</p>
                            <!--                            <div class="social-links">
                                                            <ul class="d-flex list-unstyled">
                                                                <li>
                                                                    <a href="#">
                                                                        <svg class="facebook">
                                                                        <use xlink:href="#facebook" />
                                                                        </svg>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">
                                                                        <svg class="instagram">
                                                                        <use xlink:href="#instagram" />
                                                                        </svg>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">
                                                                        <svg class="twitter">
                                                                        <use xlink:href="#twitter" />
                                                                        </svg>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">
                                                                        <svg class="linkedin">
                                                                        <use xlink:href="#linkedin" />
                                                                        </svg>
                                                                    </a>
                                                                </li>
                                                                <li>
                                                                    <a href="#">
                                                                        <svg class="youtube">
                                                                        <use xlink:href="#youtube" />
                                                                        </svg>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                        </div>-->
                        </div>
                    </div>
                    <div class="col-lg-2 col-sm-6 pb-3">
                        <div class="footer-menu text-capitalize">
                            <h5 class="widget-title pb-2">Trang chính</h5>
                            <ul class="menu-list list-unstyled text-capitalize">
                                <li class="menu-item mb-1">
                                    <a href="home">Trang chủ</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="about">Giới thiệu</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="shop">Sản phẩm</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="blog">Tin tức</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="contact">Liên hệ</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 pb-3">
                        <div class="footer-menu text-capitalize">
                            <h5 class="widget-title pb-2">Trợ giúp & Thông tin Trợ giúp</h5>
                            <ul class="menu-list list-unstyled">
                                <li class="menu-item mb-1">
                                    <a href="#">Theo dõi đơn hàng của bạn</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="#">Chính sách hoàn trả</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="#">Vận chuyển + Giao hàng</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="#">Liên hệ chúng tôi</a>
                                </li>
                                <li class="menu-item mb-1">
                                    <a href="#">Câu hỏi thường gặp</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-3 col-sm-6 pb-3">
                        <div class="footer-menu contact-item">
                            <h5 class="widget-title text-capitalize pb-2">Liên hệ chúng tôi</h5>
                            <p>Bạn có bất kỳ thắc mắc hoặc gợi ý nào không? <a href="mailto:" class="text-decoration-underline">sshopbook88@gmail.com</a></p>
                            <p>Nếu bạn cần hỗ trợ? Chỉ cần gọi cho chúng tôi. <a href="#" class="text-decoration-underline">+84 38 272
                                    0127</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<hr>
<div id="footer-bottom" class="mb-2">
    <div class="container">
        <div class="d-flex flex-wrap justify-content-between">
            <div class="ship-and-payment d-flex gap-md-5 flex-wrap">
                <div class="shipping d-flex">
                    <p>Giao hàng nhanh</p>
                    <div class="card-wrap ps-2">
<!--                        <img src="${pageContext.request.contextPath}/images/dhl.png" alt="visa">
                        <img src="${pageContext.request.contextPath}/images/shippingcard.png" alt="mastercard">-->
                    </div>
                </div>
                <div class="payment-method d-flex">

                    <p>Thanh toán trực tiếp hoặc qua các thẻ</p>

                    <div class="card-wrap ps-2">
                        <img src="${pageContext.request.contextPath}/images/visa.jpg" alt="visa">
                        <img src="${pageContext.request.contextPath}/images/mastercard.jpg" alt="mastercard">
                        <img src="${pageContext.request.contextPath}/images/paypal.jpg" alt="paypal">
                    </div>
                </div>
            </div>
            <div class="copyright">
                <p>ShopBook88 <a href="home" target="_blank">mang lại thế giới cho bạn</a>
                </p>
            </div>
        </div>
    </div>
</div>

<script src="js/jquery-1.11.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</body>

</html>
