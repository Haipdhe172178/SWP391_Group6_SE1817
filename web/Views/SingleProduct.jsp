<%-- 
    Document   : SingleProduct.jsp
    Created on : May 15, 2024, 10:59:41 PM
    Author     : huyca
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
        <style>
            .nav-user-dropdown {
                position: relative;
                display: inline-block;
            }

            .dropbtn {
                color: #131814;
                background-color: white;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover, .dropbtn:focus {
                color: #F86D72/* Change this to your desired hover color */
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #ffffff;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #f1f1f1;
            }

            .nav-user-dropdown:hover .dropdown-content {
                display: block;
            }

        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="breadcrumbs" style="padding-top: 2em">
                        <span class="item"><a href="shop">Cửa hàng / </a></span>
                        <span class="item"><a href="shop?categoryId=${requestScope.category.categoryId}">${requestScope.category.categoryName} / </a></span>
                    <span class="item"><a href="single?productID=${requestScope.product.productId}">${requestScope.product.name} </a></span>
                </div>
            </div>
        </div>

        <section class="single-product padding-large" style="padding-top: 1em">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="d-flex gap-3 product-preview">
                            <div class="swiper thumb-swiper w-50">
                                <div class="swiper-wrapper d-flex flex-wrap gap-3 align-content-start">
                                    <div class="swiper-slide bg-white">
                                        <img src="${requestScope.product.imgProduct}" alt="product-thumb" class="img-fluid border rounded-3" style="width: 150px; height: auto">
                                    </div>
                                </div>
                            </div>
                            <div class="swiper large-swiper border rounded-3 overflow-hidden">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide bg-white">
                                        <img src="${requestScope.product.imgProduct}" alt="single-product"  class="img-fluid">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="product-info ps-lg-5 pt-3 pt-lg-0">
                            <div class="element-header">
                                <h1 class="product-title" style="font-size: 20px">${requestScope.product.name}</h1>
                                <fmt:setLocale value="vi_VN" />
                                <div class="product-price d-flex align-items-center mt-2">
                                    <span class="fs-2 fw-light text-primary me-2" id="price">
                                        <fmt:formatNumber value="${requestScope.product.price}" type="currency" currencySymbol="₫" groupingUsed="true" />
                                    </span>

                                    <!-- Khi nào giảm giá thì dùng -->
                                    <del></del>
                                </div>
                                <div class="rating text-warning d-flex align-items-center mb-2">
                                    <c:forEach var="rate" begin="1" end="${requestScope.avgRating}"> 
                                        <svg class="star star-fill">
                                        <use xlink:href="#star-fill"></use>
                                        </svg>
                                    </c:forEach>
                                    <c:forEach var="rate" begin="1" end="${5-requestScope.avgRating}"> 
                                        <svg class="star star-empty" style="stroke: gold">
                                        <use xlink:href="#star-empty"></use>
                                        </svg>
                                    </c:forEach>
                                </div>
                                <div class="product-quantity">Đã bán: ${requestScope.quantitySold}</div>
                            </div>

                            <hr>
                            <div class="cart-wrap">
                                <div class="meta-item d-flex mb-1">
                                    <span class="fw-medium me-2">Tác giả: </span>
                                    <ul class="select-list list-unstyled d-flex mb-0">
                                        <a href="#">${requestScope.author.authorName}</a>

                                    </ul>
                                </div>
                                <div class="meta-item d-flex mb-1">
                                    <span class="fw-medium me-2">Thể loại: </span>
                                    <ul class="select-list list-unstyled d-flex mb-0">
                                        <a href="shop?categoryId=${requestScope.category.categoryId}">${requestScope.category.categoryName}</a>
                                    </ul>
                                </div>
                                <div class="meta-item d-flex mb-1">
                                    <span class="fw-medium me-2">Đối tượng: </span>
                                    <ul class="select-list list-unstyled d-flex mb-0">
                                        <li data-value="S" class="select-item">
                                            <a href="#">${requestScope.objectAge.age}</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="product-quantity my-3">
                                    <div class="item-title">
                                        <l>${requestScope.product.quantity} sách có sẵn</l>
                                    </div>
                                    <div class="stock-button-wrap mt-2 d-flex flex-wrap align-items-center">
                                        <div class="product-quantity">
                                            <div class="input-group product-qty align-items-center" style="max-width: 150px;">
                                                <span class="input-group-btn">
                                                    <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-left-minus" data-type="minus" data-field="">
                                                        <svg width="16" height="16"><use xlink:href="#minus"></use></svg>
                                                    </button>
                                                </span>
                                                <input type="text" id="quantity" name="quantity" class="form-control bg-white shadow border rounded-3 py-2 mx-2 input-number text-center" value="1" min="1" max="100" required>
                                                <span class="input-group-btn">
                                                    <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-right-plus" data-type="plus" data-field="">
                                                        <svg width="16" height="16"><use xlink:href="#plus"></use></svg>
                                                    </button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="action-buttons my-3 d-flex flex-wrap gap-3">
                                    <a href="#" class="btn">Mua ngay</a>
                                    <a href="#" class="btn btn-dark">Thêm vào giỏ hàng</a>
                                </div>
                            </div>
                            <hr>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="product-tabs">
            <div class="container">
                <div class="row">
                    <div class="tabs-listing">
                        <nav>
                            <div class="nav nav-tabs d-flex justify-content-center py-3" id="nav-tab" role="tablist">
                                <button class="nav-link text-capitalize active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home" aria-selected="true">Mô tả</button>
                                <button class="nav-link text-capitalize" id="nav-review-tab" data-bs-toggle="tab" data-bs-target="#nav-review" type="button" role="tab" aria-controls="nav-review" aria-selected="false">Đánh giá (${requestScope.listFeedback.size()})</button>
                            </div>
                        </nav>
                        <div class="tab-content border-bottom py-4" id="nav-tabContent">

                            <!-- DESCRIPTION -->
                            <div class="tab-pane fade active show" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                                <p>${requestScope.product.description}</p>
                            </div>

                            <!-- FEEDBACK -->
                            <div class="tab-pane fade" id="nav-review" role="tabpanel" aria-labelledby="nav-review-tab">
                                <div class="review-box review-style d-flex gap-3 flex-column">
                                    <c:forEach var="feedback" items="${requestScope.listFeedback}">
                                        <div class="review-item d-flex">
                                            <div class="image-holder me-2">
                                                <c:forEach var="acc" items="${requestScope.listAccount}">
                                                    <c:if test="${feedback.accountId == acc.accountId}"> 
                                                        <img src="${acc.imgAccount}" alt="review" class="img-fluid rounded-3" style="width: 5em">
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                            <div class="review-content">
                                                <div class="rating text-primary">
                                                    <c:forEach begin="1" end="${feedback.rating}">
                                                        <svg class="star star-fill">
                                                        <use xlink:href="#star-fill" style="color: gold"></use>
                                                        </svg>
                                                    </c:forEach > 
                                                    <c:forEach begin="1" end="${5-feedback.rating}">
                                                        <svg class="star star-empty">
                                                        <use xlink:href="#star-empty" style="stroke: gold"></use>
                                                        </svg>
                                                    </c:forEach> 
                                                </div>
                                                <div class="review-header">
                                                    <c:forEach var="acc" items="${requestScope.listAccount}">
                                                        <c:if test="${feedback.accountId == acc.accountId}">
                                                            <span class="author-name fw-medium">${acc.fullName}</span>
                                                        </c:if> 
                                                    </c:forEach>
                                                    <span class="review-date"> / ${feedback.feedbackDate}</span>
                                                </div>
                                                <p>${feedback.comments}</p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <div class="add-review margin-small">
                                    <h3>Đánh giá</h3>
                                    <p>Bạn cần đăng nhập để có thể đánh giá <a href=""> </a></p>
                                    <!-- comment
                                    <div class="review-rating py-2">
                                        <span class="my-2">Your rating *</span>
                                        <div class="rating text-secondary">
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
                                    <input type="file" class="jfilestyle py-3 border-0" data-text="Choose your file">
                                    <form id="form" class="d-flex gap-3 flex-wrap">
                                        <div class="w-100 d-flex gap-3">
                                            <div class="w-50">
                                                <input type="text" name="name" placeholder="Write your name here *" class="form-control w-100">
                                            </div>
                                            <div class="w-50">
                                                <input type="text" name="email" placeholder="Write your email here *" class="form-control w-100">
                                            </div>
                                        </div>
                                        <div class="w-100">
                                            <textarea placeholder="Write your review here *" class="form-control w-100"></textarea>
                                        </div>
                                        <label class="w-100">
                                            <input type="checkbox" required="" class="d-inline">
                                            <span>Save my name, email, and website in this browser for the next time.</span>
                                        </label>
                                        <button type="submit" name="submit" class="btn my-3">Submit</button>
                                    </form>
                                    -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="related-items" class="position-relative padding-large ">
            <div class="container">
                <div class="section-title d-md-flex justify-content-between align-items-center mb-4">
                    <h3 class="d-flex align-items-center">Sách liên quan</h3>
                    <a href="#" class="btn">Xem tất cả</a>
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
                        <c:forEach var="p" items="${requestScope.relatedProduct}">
                            <div class="swiper-slide">
                                <a href="single?productID=${p.productId}">
                                    <div class="card position-relative p-4 border rounded-3">
                                        <div class="position-absolute">
                                            <!-- DISCOUNT -->
                                            <p class="bg-primary py-1 px-3 fs-6 text-white rounded-2">10% off</p>
                                        </div>
                                        <img src="${p.imgProduct}" class="img-fluid shadow-sm" alt="product item">
                                        <h6 class="mt-4 mb-0 fw-bold"><a href="single?productID=${p.productId}">${p.name}</a></h6>
                                        <div class="review-content d-flex">
                                            <p class="my-2 me-2 fs-6 text-black-50">${requestScope.author.authorName}</p>

                                            <div class="rating text-warning d-flex align-items-center">
                                                <c:forEach begin="1" end="5"> 
                                                    <svg class="star star-fill" style="stroke: gold">
                                                    <use xlink:href="#star-empty"></use>
                                                    </svg>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <span class="price text-primary fw-bold mb-2 fs-5">${p.price}</span>
                                        <div class="card-concern position-absolute start-0 end-0 d-flex gap-2">
                                            <button type="button" href="#" class="btn btn-dark" data-bs-toggle="tooltip" data-bs-placement="top" data-bs-title="Tooltip on top">
                                                <svg class="cart">
                                                <use xlink:href="#cart"></use>
                                                </svg>
                                            </button>
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
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
                <div class="swiper testimonial-swiper ">
                    <div class="swiper-wrapper">
                        <c:forEach var="feedback" items="${requestScope.listMostRating}">
                            <div class="swiper-slide">
                                <div class="card position-relative text-left p-5 border rounded-3">
                                    <blockquote>"${feedback.comments}"</blockquote>
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
                                    <c:forEach var="acc" items="${requestScope.listAccount}">
                                        <c:if test="${feedback.accountId == acc.accountId}">
                                            <h5 class="mt-1 fw-normal">${acc.fullName}</h5>
                                        </c:if> 
                                    </c:forEach>
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
                    <h3 class="d-flex align-items-center">Tin tức</h3>
                    <a href="blog" class="btn">Xem tất cả</a>
                </div>
                <div class="row">
                    <!-- NEWS -->
                    <c:forEach var="n" items="${requestScope.news}">
                        <div class="col-md-3 posts mb-4">
                            <img src="${n.imgNews1}" alt="post image" class="img-fluid rounded-3">
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

        <jsp:include page="../common/footer.jsp"></jsp:include>
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>

</html>
