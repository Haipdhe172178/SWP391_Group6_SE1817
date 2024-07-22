<%-- 
    Document   : About
    Created on : May 15, 2024, 10:22:41 PM
    Author     : huyca
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <section id="company-services" class="padding-large">
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
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Giao Hàng Miễn Phí</h4>
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
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Đảm Bảo Chất Lượng</h4>
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
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Ưa Đãi Khủng</h4>
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
                                    <h4 class="card-title mb-1 text-capitalize text-dark">Thanh Toán An Toàn 100%</h4>
                                    <p>Làm cho khách hàng hài lòng nhất</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section id="about-us" class="padding-large pt-0">
                <div class="container">
                    <div class="row">

                        <div class="display-header">
                            <h3>ShopBook88 – Nơi sách trở nên sống động!</h3>
                            <h4 class="mb-3">Chào mừng đến với ShopBook88</h4>
                            <p class="pb-1">Tại ShopBook88, chúng tôi tin rằng mỗi cuốn sách đều có sức mạnh thay đổi cuộc sống,
                                truyền cảm hứng và mở ra những cánh cửa tới vô vàn cơ hội. Sứ mệnh của chúng tôi là mang đến
                                trải nghiệm đọc sách này cho tất cả mọi người, ở khắp mọi nơi. Dù bạn là người đam mê đọc sách,
                                học sinh, chuyên gia, hay chỉ đơn giản là tìm kiếm một câu chuyện hay, ShopBook88 chính là điểm 
                                đến lý tưởng cho mọi nhu cầu đọc sách của bạn. </p>
                            <h4>Ghé thăm ShopBook88 ngay hôm nay</h4>
                            <p>- Bộ sưu tập phong phú: Khám phá bộ sưu tập đa dạng của chúng tôi với các thể loại khác nhau –
                                từ những cuốn tiểu thuyết ly kỳ, những tác phẩm kinh điển vượt thời gian đến những cuốn sách 
                                phi hư cấu sâu sắc và những tài liệu học thuật tiên tiến.</p>
                            <p>- Trải nghiệm người dùng thân thiện: Thiết kế website trực quan của chúng tôi giúp bạn dễ dàng tìm kiếm và mua sách yêu thích.
                                Với các tùy chọn tìm kiếm nâng cao, 
                                mô tả chi tiết và đánh giá của khách hàng, bạn có thể đưa ra quyết định mua hàng một cách thông minh.</p>
                            <p>- Giá cả hợp lý: Tận hưởng mức giá cạnh tranh và các chương trình giảm giá đặc biệt trên nhiều loại sách. 
                                Chúng tôi tin vào việc mang đến những cuốn sách chất lượng với giá cả phải chăng cho mọi người.</p>
                            <p>- Giao hàng tiện lợi: Với các tùy chọn giao hàng nhanh chóng và đáng tin cậy, sách của bạn sẽ được giao 
                                tận nơi. Bạn cũng có thể theo dõi đơn hàng của mình theo thời gian thực và nhận thông báo về trạng thái giao hàng.</p>
                            <p>- Hỗ trợ khách hàng: Đội ngũ chăm sóc khách hàng tận tình của chúng tôi luôn sẵn sàng hỗ trợ bạn với mọi 
                                thắc mắc hoặc vấn đề. Sự hài lòng của bạn là ưu tiên hàng đầu của chúng tôi.</p>
                            <a href="shop" class="btn mt-3">Mua sắm</a>
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
