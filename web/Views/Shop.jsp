<%-- 
    Document   : Shop.jsp
    Created on : May 15, 2024, 10:48:10 PM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                font-size: 14px;
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
            <div class="shopify-grid padding-large">
                <div class="container">
                    <div class="row flex-row-reverse g-md-5">
                        <main class="col-md-9">
                            <div class="filter-shop d-flex flex-wrap justify-content-between mb-5">
                                <div class="showing-product">
                                    <h6>Tìm thấy <span>${count}</span> Sản Phẩm</h6>
                            </div>
                            <form method="GET" action="shop">                          
                                <div class="sort-by">
                                    <select id="sorting" class="form-select" name="sortBy" onchange="this.form.submit()">
                                        <option value="">Mặc Định</option>
                                        <option value="name_asc" ${sortBy == 'name_asc' ? 'selected' : ''}>Tên (A - Z)</option>
                                        <option value="name_desc" ${sortBy == 'name_desc' ? 'selected' : ''}>Tên (Z - A)</option>
                                        <option value="price_asc" ${sortBy == 'price_asc' ? 'selected' : ''}>Giá (Low-High)</option>
                                        <option value="price_desc" ${sortBy == 'price_desc' ? 'selected' : ''}>Giá (High-Low)</option>
                                    </select>
                                </div>
                            </form>

                        </div>
                        <div class="row product-content product-store">
                            <fmt:setLocale value="vi_VN" />
                            <c:forEach items="${ListA}" var="pro">
                                <div class="col-lg-3 col-md-4 mb-4">
                                    <a href="single?productID=${pro.productId}">
                                        <div class="card position-relative p-4 border rounded-3">
                                            <div class="position-absolute">
                                                <p class="bg-primary py-1 px-3 fs-6 text-white rounded-2"></p>
                                            </div>
                                            <img src="${pro.imgProduct}" class="img-fluid shadow-sm" alt="product item">
                                            <h6 class="mt-4 mb-0 fw-bold">
                                                <a href="single?productID=${pro.productId}">${pro.name}</a>
                                            </h6>
                                            <div class="review-content d-flex">

                                                <p class="my-2 me-2 fs-6 text-black-50">
                                                    ${pro.author.authorName}
                                                </p>

                                                <!--                                            <div class="rating text-warning d-flex align-items-center">
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
                                                                                            </div>-->
                                            </div>
                                            <span class="price text-primary fw-bold mb-2 fs-5">

                                                <fmt:formatNumber value=" ${pro.price}" type="currency" currencySymbol="₫" groupingUsed="true" />


                                            </span>
                                            <!--                                        <div class="card-concern position-absolute start-0 end-0 d-flex gap-2">
                                                                                        <button type="button" class="btn btn-dark" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Tooltip on top">
                                                                                            <svg class="cart">
                                                                                            <use xlink:href="#cart"></use>
                                                                                            </svg>
                                                                                        </button>   
                                                                                        <a href="#" class="btn btn-dark">
                                                                                            <span>
                                                                                                <svg class="wishlist">
                                                                                                <use xlink:href="#heart"></use>
                                                                                                </svg>
                                                                                            </span>
                                                                                        </a>
                                                                                    </div>-->
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>
                        </div>

                        <nav class="py-5" aria-label="Page navigation">
                            <ul class="pagination justify-content-center gap-4">
                                <!-- Nút Prev -->
                                <c:choose>
                                    <c:when test="${tag > 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="shop?index=${tag - 1}${query}">Prev</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <span class="page-link">Prev</span>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                                <c:set var="start" value="${tag > 3 ? tag - 2 : 1}" />
                                <c:set var="end" value="${tag > 3 ? tag + 2 : 5}" />
                                <c:if test="${end > endP}">
                                    <c:set var="end" value="${endP}" />
                                    <c:set var="start" value="${endP - 4 > 0 ? endP - 4 : 1}" />
                                </c:if>

                                <c:forEach begin="${start}" end="${end}" var="i">
                                    <li class="page-item ${tag == i ? 'active' : ''}">
                                        <a class="page-link" href="shop?index=${i}${query}">${i}</a>
                                    </li>
                                </c:forEach>

                                <!-- Nút Next -->
                                <c:choose>
                                    <c:when test="${tag < endP}">
                                        <li class="page-item">
                                            <a class="page-link" href="shop?index=${tag + 1}${query}">Next</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item disabled">
                                            <span class="page-link">Next</span>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </nav>

                    </main>
                    <aside class="col-md-3">
                        <div class="sidebar ps-lg-5">
                            <div class="widget-menu">
                                <div class="widget-search-bar">
                                    <form class="d-flex border rounded-3 p-2" role="search" method="GET" action="shop">
                                        <input class="form-control border-0 me-2 py-2" type="search" name="s" placeholder="Tìm kiếm">
                                        <button class="btn rounded-3 p-3 d-flex align-items-center" type="submit">
                                            <svg class="search text-light" width="18" height="18">
                                            <use xlink:href="#search"></use>
                                            </svg>
                                        </button>
                                    </form>
                                </div>
                            </div>
                            <div class="filter-box">
                                <h2>Lọc sản phẩm</h2>
                                <form action="shop" method="get">
                                    <div class="widget-product-categories pt-5">
                                        <div class="section-title overflow-hidden mb-2 collapsible">
                                            <h3 class="d-flex flex-column mb-0">Thể loại</h3>
                                        </div>
                                        <div class="content">
                                            <ul class="product-categories mb-0 sidebar-list list-unstyled">
                                                <c:forEach items="${category}" var="c">
                                                    <li class="cat-item">
                                                        <label>
                                                            <input type="checkbox" name="categoryId" value="${c.categoryId}" 
                                                                   ${selectedCategoryIds.contains(c.categoryId) ? "checked" : ""}> ${c.categoryName}
                                                        </label>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="widget-product-tags pt-5">
                                        <div class="section-title overflow-hidden mb-2 collapsible">
                                            <h3 class="d-flex flex-column mb-0">Độ tuổi</h3>
                                        </div>
                                        <div class="content">
                                            <ul class="product-tags mb-0 sidebar-list list-unstyled">
                                                <c:forEach items="${objage}" var="ob">
                                                    <li class="cat-item">
                                                        <label>
                                                            <input type="checkbox" name="objage" value="${ob.ageId}" 
                                                                   ${selectedAgeId == ob.ageId ? "checked" : ""} onclick="limitCheckboxSelection(this)"> ${ob.age}
                                                        </label>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="widget-price-filter pt-5">
                                        <div class="section-title overflow-hidden mb-2 collapsible">
                                            <h3 class="d-flex flex-column mb-0">Khoảng giá</h3>
                                        </div>
                                        <div class="content">
                                            <ul class="product-tags mb-0 sidebar-list list-unstyled">
                                                <li class="tags-item">
                                                    <label>
                                                        <input type="checkbox" name="price_filter" value="lessthan10" 
                                                               ${"lessthan10".equals(selectedPriceFilter) ? "checked" : ""} onclick="limitCheckboxSelection(this)"> Nhỏ hơn 100,000₫
                                                    </label>
                                                </li>
                                                <li class="tags-item">
                                                    <label>
                                                        <input type="checkbox" name="price_filter" value="10to20" 
                                                               ${"10to20".equals(selectedPriceFilter) ? "checked" : ""} onclick="limitCheckboxSelection(this)"> Từ 100,000₫ - 200,000₫
                                                    </label>
                                                </li>
                                                <li class="tags-item">
                                                    <label>
                                                        <input type="checkbox" name="price_filter" value="20to30" 
                                                               ${"20to30".equals(selectedPriceFilter) ? "checked" : ""} onclick="limitCheckboxSelection(this)"> Từ 200,000₫ - 300,000₫
                                                    </label>
                                                </li>
                                                <li class="tags-item">
                                                    <label>
                                                        <input type="checkbox" name="price_filter" value="30to40" 
                                                               ${"30to40".equals(selectedPriceFilter) ? "checked" : ""} onclick="limitCheckboxSelection(this)"> Từ 300,000₫ - 400,000₫
                                                    </label>
                                                </li>
                                                <li class="tags-item">
                                                    <label>
                                                        <input type="checkbox" name="price_filter" value="morethan50" 
                                                               ${"morethan50".equals(selectedPriceFilter) ? "checked" : ""} onclick="limitCheckboxSelection(this)"> Lớn hơn 500,000₫
                                                    </label>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>

                                    <div class="pt-5 text-center">
                                        <button type="submit">Lọc</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </aside>
                </div>
            </div>
        </div>

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
        <script>
            function limitCheckboxSelection(checkbox) {
                var checkboxes = document.getElementsByName(checkbox.name);
                checkboxes.forEach(function (currentCheckbox) {
                    if (currentCheckbox !== checkbox) {
                        currentCheckbox.checked = false;
                    }
                });
            }

        </script>
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>

</html>
