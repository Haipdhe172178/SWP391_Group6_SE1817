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
        <style>
            .filter-box {
                border: 1px solid #ddd;
                padding: 20px;
                border-radius: 5px;
                background-color: white;
                margin-bottom: 20px;
                margin-top: 20px
            }

            .filter-box h2 {
                text-align: center;
                margin-top: 0;
                font-size: 20px;
            }
            .collapsible {
                cursor: pointer;
                user-select: none;
                background-color: #f86d72;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 10px;
                font-size: 14px;
                position: relative;
            }
            .collapsible::after {
                content: '\002B';
                font-size: 14px;
                position: absolute;
                right: 20px;
                color: #777;
            }
            .collapsible.active::after {
                content: '\2212';
            }
            .content {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                margin-bottom: 10px;
            }
            .section-title h3 {
                margin: 0;
                font-size: 16px;
                color: #fff;
            }
            .product-categories, .product-tags {
                padding-left: 20px;
            }
            .cat-item, .tags-item {
                margin-bottom: 10px;
                font-size: 18px;
            }
            button[type="submit"] {
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                background-color: #f86d72;
                color: #fff;
                cursor: pointer;
            }
            button[type="submit"]:hover {
                background-color: #f86d72;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <div id="blog" class="padding-large">
                <div class="container">
                    <div class="row flex-row-reverse g-md-5">
                        <main class="col-md-9 mb-4 mb-md-0">
                            <div class="filter-blog d-flex flex-wrap justify-content-between mb-4">
                                <div class="showing-product">
                                    <p>${requestScope.quantityNews} kết quả</p>
                            </div>
                            <div class="sort-by">
                                <select id="sorting" class="form-select" data-filter-sort="" data-filter-order="" onchange="location.href = 'blog?sort=' + this.value + '&id=${requestScope.tid}'">
                                    <option value="decrease" <c:if test="${requestScope.sortNews eq 'decrease'}">selected</c:if>>Mới nhất</option>
                                    <option value="increase" <c:if test="${requestScope.sortNews eq 'increase'}">selected</c:if>>Cũ nhất</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row post-contents">
                            <c:forEach var="news" items="${requestScope.listNews}">
                                <div class="col-lg-4 col-md-6 posts mb-5">
                                    <div class="img-container" style="display: flex; justify-content: center; align-items: center;">
                                        <img src="${news.imgNews}" alt="post image" class="img-fluid rounded-3" style="height: 12em; width: auto; border-radius: 1em">
                                    </div>
                                    <a href="blog?id=${news.topic.topicId}" class="fs-6 text-primary">${news.topic.topicName}</a>
                                    <h5 class="card-title mb-2 text-capitalize text-dark" style="font-weight: bold; font-size: 0.9em; color: #343a40; margin-bottom: 0.5em; text-transform: capitalize;">
                                        <a href="post?id=${news.newId}">${news.title}</a>
                                    </h5>
                                    <p class="mb-2" style="display: -webkit-box; -webkit-line-clamp: 3; -webkit-box-orient: vertical; overflow: hidden;">
                                        ${news.content}
                                        <span><a class="text-decoration-underline text-black-50" href="post?id=${news.newId}">Read More</a></span>
                                    </p>
                                </div>
                            </c:forEach>
                        </div>
                        <c:set var="total" value="${requestScope.endPage}" />
                        <c:set var="page" value="${requestScope.page}" />
                        <nav class="pt-5" aria-label="Page navigation">
                            <ul class="pagination justify-content-center gap-4">
                                <li class="page-item">
                                    <c:if test="${page > 1}">
                                        <a class="page-link" href="blog?page=${page-1}&id=${requestScope.tid}&sort=${requestScope.sortNews}">Prev</a>
                                    </c:if>
                                </li>
                                <c:if test="${total <= 5}">
                                    <c:forEach begin="1" end="${total}" var="pageNum">
                                        <li class="page-item ${page == pageNum ? 'active' : ''}">
                                            <a class="page-link" href="blog?page=${pageNum}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                        </li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${total > 5}">
                                    <li class="page-item ${page == 1 ? 'active' : ''}">
                                        <a class="page-link" href="blog?page=1&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == 1 ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>1</a>
                                    </li>
                                    <c:if test="${page > 3}">
                                        <li class="page-item disabled">
                                            <span class="page-link">...</span>
                                        </li>
                                    </c:if>
                                    <c:forEach begin="${page > 2 ? page - 1 : 2}" end="${page < total - 1 ? page + 1 : total - 1}" var="pageNum">
                                        <li class="page-item ${page == pageNum ? 'active' : ''}">
                                            <a class="page-link" href="blog?page=${pageNum}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                        </li>
                                    </c:forEach>
                                    <c:if test="${page < total - 2}">
                                        <li class="page-item disabled">
                                            <span class="page-link">...</span>
                                        </li>
                                    </c:if>
                                    <li class="page-item ${page == total ? 'active' : ''}">
                                        <a class="page-link" href="blog?page=${total}&id=${requestScope.tid}&sort=${requestScope.sortNews}" ${page == total ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${total}</a>
                                    </li>
                                </c:if>
                                <li class="page-item">
                                    <c:if test="${page < total}">
                                        <a class="page-link" href="blog?page=${page+1}&id=${requestScope.tid}&sort=${requestScope.sortNews}">Next</a>
                                    </c:if>
                                </li>
                            </ul>
                        </nav>
                    </main>
                    <aside class="col-md-3">
                        <div class="sidebar">
                            <div class="widget-product-categories pt-5">
                                <div class="section-title overflow-hidden mb-2">
                                    <h3 class="d-flex flex-column mb-0">Danh mục</h3>
                                </div>
                                <ul class="product-categories mb-0 sidebar-list list-unstyled">
                                    <li class="cat-item">
                                        <a href="blog?id=0" <c:if test="${requestScope.tid == topic.topicId}">style="color: #F86D72"</c:if>>Tất cả</a>
                                        </li>
                                    <c:forEach var="topic" items="${requestScope.listTopic}">
                                        <li class="cat-item">
                                            <a href="blog?id=${topic.topicId}" <c:if test="${requestScope.tid == topic.topicId}">style="color: #F86D72"</c:if>>${topic.topicName}</a>
                                            </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
        </div>
        <section id="instagram">
            <div class="container padding-large pb-0">
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
                                        "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người ta càng thấy còn nhiều điều cần phải đọc.” John Adams
                                    </p>
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
                                    <p>Nếu bạn cần hỗ trợ? Chỉ cần gọi cho chúng tôi. <a href="#" class="text-decoration-underline">+84 38 272 0127</a></p>
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
