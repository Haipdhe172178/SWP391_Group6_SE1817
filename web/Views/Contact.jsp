<%-- 
    Document   : Contact.jsp
    Created on : May 15, 2024, 11:14:39 PM
    Author     : huyca
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <title>Bookly - Bookstore eCommerce Website Template</title>
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
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>

            <div class="contact-us padding-large">
                <div class="container">
                    <div class="row">
                        <div class="contact-info col-lg-6 pb-3">
                            <h3>THÔNG TIN LIÊN LẠC</h3>

                            <div class="page-content d-flex flex-wrap">
                                <div class="col-lg-6 col-sm-12">
                                    <div class="content-box text-dark pe-4 mb-5">
                                        <h5 class="fw-bold">Văn phòng</h5>
                                        <div class="contact-address pt-3">
                                            <p>Phạm Đình Hồ, Hai Bà Trưng, Hà Nội</p>
                                        </div>
                                        <div class="contact-number">
                                            <p>
                                                <a href="#">0339638903</a>
                                            </p>

                                        </div>
                                        <div class="email-address">
                                            <p>
                                                <a href="#">ShopBook88@gmail.com</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-6 col-sm-12">
                                    <div class="content-box">
                                        <h5 class="fw-bold">Quản lý</h5>
                                        <div class="contact-address pt-3">
                                            <p>Phạm Đình Hồ, Hai Bà Trưng, Hà Nội</p>
                                        </div>
                                        <div class="contact-number">
                                            <p>
                                                <a href="#">0339638903</a>
                                            </p>

                                        </div>
                                        <div class="email-address">
                                            <p>
                                                <a href="#">anh553181@gmail.com</a>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="inquiry-item col-lg-6">
                            <h3>HÃY ĐỂ LẠI LỜI NHẮN CHO CHÚNG TÔI</h3>
                            <p class="mb-5">Vui lòng liên hệ theo biểu mẫu dưới. 
                                ShopBook88 sẽ hồi âm trong thời gian nhanh nhất.</p>
                            <c:set var="acc" value="${sessionScope.account}"/>
                        <form id="form" class="d-flex gap-3 flex-wrap" method="post" action="${pageContext.request.contextPath}/contact">
                            <div class="w-100 d-flex gap-3">
                                <div class="w-50">
                                    <input type="text" name="name" pattern="^(?! ).*$" placeholder="Họ và tên *" class="form-control w-100" required value="${acc.fullName}">
                                </div>
                                <div class="w-50">
                                    <input type="email" name="email" placeholder="Email *" class="form-control w-100" required value="${acc.email}" >
                                </div>
                            </div>
                            <div class="w-100">
                                <input type="text" name="phoneNumber" pattern="[0-9]*" placeholder="Số điện thoại" class="form-control w-100" value="${acc.phoneNumber}" >
                            </div>
                            <div class="w-100">
                                <input type="text" name="topic" pattern="^(?! ).*$" placeholder="Chủ đề" class="form-control w-100">
                            </div>
                            <div class="w-100">
                                <textarea name="message" pattern="^(?! ).*$" placeholder="Nội dung *" class="form-control w-100" required></textarea>
                            </div>
                            <button type="submit" name="submit" class="btn my-3">Gửi liên hệ của bạn</button>
                        </form>
                        <div>
                            ${requestScope.message}
                        </div>


                    </div>
                </div>
            </div>
        </div>

        <section id="our-store" class="padding-large pt-0">
            <div class="container">
                <div class="row d-flex flex-wrap align-items-center">
                    <div class="col-lg-6">
                        <h3>Đường đến cửa hàng</h2>
                            <div class="image-holder mb-5">
                                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.20034887179!2d105.84517807387608!3d21.02466808792173!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135ab03ca410b3d%3A0xc71c904043064efc!2sShop%20Book%2088!5e0!3m2!1svi!2s!4v1716220140248!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>                </div>

                    </div>
                </div>
        </section>


        <section id="latest-posts" class="padding-large">
            <div class="container">
                <div class="section-title d-md-flex justify-content-between align-items-center mb-4">
                    <h3 class="d-flex align-items-center">Latest posts</h3>
                    <a href="shop" class="btn">View All</a>
                </div>
                <div class="row">
                    <!-- NEWS -->
                    <c:forEach var="n" items="${requestScope.news}">
                        <div class="col-md-3 posts mb-4">
                            <img src="${n.imgNews}" alt="post image" class="img-fluid rounded-3">
                            <a href="blog" class="fs-6 text-primary">${n.topic.topicName}</a>
                            <h4 class="card-title mb-2 text-capitalize text-dark"><a href="post">${n.title}</a></h4>
                            <p class="mb-2" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient:vertical; overflow: hidden ">${n.content} <span><a class="text-decoration-underline text-black-50" href="post">Read More</a></span> 
                            </p>
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                            <a href="https://templatesjungle.com/" class="image-link position-relative">
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
                                    <p>Bạn có bất kỳ thắc mắc hoặc gợi ý nào không? <a href="mailto:" class="text-decoration-underline">shopbook88@gmail.com</a></p>
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
