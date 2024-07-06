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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rating.css">
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

            .linkLogin {
                color: #0099cc;
                text-decoration: none;
                transition: color 0.3s;
            }

            .linkLogin:hover {
                color: #F86D72; /* Change to desired hover color */
            }
            .filterFeedback {
                /* Căn chỉnh các phần tử con trên cùng một hàng */
                display: flex;
                align-items: center;
                justify-content: space-between;
            }

            .filterFeedback filter {
                /* Đặt các thẻ <a> trong cùng một hàng và cài đặt kiểu dáng */
                display: inline-block;
                margin-bottom: 10px; /* Khoảng cách dưới mỗi thẻ <a> */
                padding: 8px 12px; /* Khoảng cách lề nội dung bên trong thẻ <a> */
                text-decoration: none; /* Loại bỏ gạch chân mặc định */
                font-weight: bold;
                color: black; /* Màu chữ mặc định */
                border-radius: 4px; /* Đường viền cong */
                transition: background-color 0.3s ease; /* Hiệu ứng chuyển đổi màu nền */
                background-color: #F86D72; /* Màu nền */
                border: 1px solid #F86D72; /* Viền cùng màu với nền */
            }


            .row.border-bottom .filter.active {
                font-weight: bold;
                color: #F86D72; /* Thay đổi màu chữ khi active */
            }

            /* Basic styles for the notification container */
            .notification-container {
                position: fixed;
                top: 20px;
                right: 20px;
                width: 300px;
                z-index: 9999;
                display: none; /* Hidden by default, will be shown when needed */
            }
            input[type=number]::-webkit-outer-spin-button,
            input[type=number]::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }

            /* Keyframes for fade in and fade out animations */
            @keyframes fadein {
                from {
                    opacity: 0;
                }
                to {
                    opacity: 1;
                }
            }

            @keyframes fadeout {
                from {
                    opacity: 1;
                }
                to {
                    opacity: 0;
                }
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <div class="container">
                <div class="row">
                    <div class="breadcrumbs" style="padding-top: 2em">
                        <span class="item"><a href="shop">Cửa hàng / </a></span>
                        <span class="item"><a href="filter?categoryId=${requestScope.product.category.categoryId}">${requestScope.product.category.categoryName} / </a></span>
                    <span class="item"><a href="single?productID=${requestScope.product.productId}">${requestScope.product.name} </a></span>
                </div>
            </div>
        </div>

        <section class="single-product padding-large" style="padding-top: 1em;
                 padding-bottom: 1em">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="d-flex gap-3 product-preview">
                            <div class="swiper thumb-swiper w-50">
                                <div class="swiper-wrapper d-flex flex-wrap gap-3 align-content-start">
                                    <div class="swiper-slide bg-white">
                                        <img src="${requestScope.product.imgProduct}" alt="product-thumb" class="img-fluid border rounded-3" style="width: 150px;
                                             height: auto">
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
                                        <a href="shop?categoryId=${requestScope.product.author.authorID}">${requestScope.product.author.authorName}</a>
                                    </ul>
                                </div>
                                <div class="meta-item d-flex mb-1">
                                    <span class="fw-medium me-2">Thể loại: </span>
                                    <ul class="select-list list-unstyled d-flex mb-0">
                                        <a href="shop?categoryId=${requestScope.product.category.categoryId}">${requestScope.product.category.categoryName}</a>
                                    </ul>
                                </div>
                                <div class="meta-item d-flex mb-1">
                                    <span class="fw-medium me-2">Đối tượng: </span>
                                    <ul class="select-list list-unstyled d-flex mb-0">
                                        <li data-value="S" class="select-item">
                                            <a href="shop?objage=${requestScope.product.oage.ageId}">${requestScope.product.oage.age}</a>
                                        </li>
                                    </ul>
                                </div>
                                <form id="formSubmit">
                                    <div class="product-quantity my-3">
                                        <div class="item-title">
                                            <l>${requestScope.product.quantity} sách có sẵn</l>
                                        </div>
                                        <div class="stock-button-wrap mt-2 d-flex flex-wrap align-items-center">
                                            <div class="product-quantity">
                                                <div class="input-group product-qty align-items-center" style="max-width: 150px;">
                                                    <span class="input-group-btn">
                                                        <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-left-minus" data-type="minus" data-field="" onclick="decreaseQuantity()">
                                                            <svg width="16" height="16"><use xlink:href="#minus"></use></svg>
                                                        </button>
                                                    </span>
                                                    <input type="number" id="quantity" name="quantity" class="form-control bg-white shadow border rounded-3 py-2 mx-2 input-number text-center" value="1" min="1" max="${requestScope.product.quantity}" required>
                                                    <span class="input-group-btn">
                                                        <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-right-plus" data-type="plus" data-field="" onclick="increaseQuantity()">
                                                            <svg width="16" height="16"><use xlink:href="#plus"></use></svg>
                                                        </button>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="action-buttons my-3 d-flex flex-wrap gap-3">
                                        <input type="hidden" name="action" value="singleToCheckout">
                                        <button type="button" class="btn" onclick="buyNow()">Mua ngay</button>
                                        <input type="hidden" name="productId" value="${requestScope.product.productId}">
                                        <button type="button" formaction="cart"  onclick="addToCart()" formmethod="post" class="btn btn-dark">Thêm vào giỏ hàng</button>
                                    </div>
                                </form>
                                <c:if test="${message ne null}">
                                    <div id="alert" class="alert-box ${type}">
                                        ${message}
                                    </div>
                                </c:if>

                                <% 
                                    if (session.getAttribute("message") != null) {
                                        session.removeAttribute("message");
                                    }
                                %>


                                <script>
//                                    xử lí form
                                    function buyNow() {
                                        var form = document.getElementById('formSubmit');
                                        form.action = 'check';
                                        form.method = 'post';
                                        form.submit();
                                    }
                                     function addToCart() {
                                        var form = document.getElementById('formSubmit');
                                        form.action = 'cart';
                                        form.method = 'post';
                                        form.submit();
                                    }
                                    
                                    function decreaseQuantity() {
                                        let quantityInput = document.getElementById('quantity');
                                        let currentValue = parseInt(quantityInput.value);
                                        let minValue = parseInt(quantityInput.getAttribute('min')) + 1;

                                        if (currentValue <= minValue) {
                                            quantityInput.value = minValue;
                                        } else {
                                            quantityInput.value = currentValue--;
                                        }
                                    }

                                    function increaseQuantity() {
                                        let quantityInput = document.getElementById('quantity');
                                        let currentValue = parseInt(quantityInput.value);
                                        let maxValue = parseInt(quantityInput.getAttribute('max')) - 1;

                                        if (currentValue < maxValue) {
                                            quantityInput.value = currentValue++;
                                        } else {
                                            quantityInput.value = maxValue;
                                        }
                                    }

                                    document.getElementById('quantity').addEventListener('input', function () {
                                        let quantityInput = document.getElementById('quantity');
                                        let currentValue = parseInt(quantityInput.value);
                                        let minValue = parseInt(quantityInput.getAttribute('min'));
                                        let maxValue = parseInt(quantityInput.getAttribute('max'));

                                        if (isNaN(currentValue) || currentValue < minValue) {
                                            quantityInput.value = minValue;
                                        } else if (currentValue > maxValue) {
                                            quantityInput.value = maxValue;
                                        }
                                    });

                                </script>

                            </div>
                        </div>
                        <hr>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!--RELATED PRODUCT-->                           
    <section id="related-items" class="position-relative padding-large ">
        <div class="container">
            <div class="section-title d-md-flex justify-content-between align-items-center mb-4">
                <h3 class="d-flex align-items-center">Sách cùng thể loại</h3>
                <a href="filter?categoryId=${requestScope.product.category.categoryId}" class="btn">Xem tất cả</a>
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
                                    <div style="width: 250px;
                                         height: 300px;
                                         display: flex;
                                         align-items: center;
                                         justify-content: center;
                                         overflow: hidden;">
                                        <img src="${p.imgProduct}" class="img-fluid shadow-sm" alt="product item" style="max-width: 100%;
                                             max-height: 100%;
                                             object-fit: cover;"></div>
                                    <h6 class="mt-4 mb-0 fw-bold"><a href="single?productID=${p.productId}" style="display: -webkit-box;
                                                                     -webkit-box-orient: vertical;
                                                                     -webkit-line-clamp: 2;
                                                                     overflow: hidden;
                                                                     text-overflow: ellipsis;
                                                                     max-width: 100%; /* Adjust this value if needed */
                                                                     line-height: 1.2em; /* Adjust line height if needed */
                                                                     height: 2.4em;">${p.name}</a></h6>
                                    <div class="review-content d-flex">
                                        <p class="my-2 me-2 fs-6 text-black-50">${requestScope.p.author.authorName}</p>

                                        <div class="rating text-warning d-flex align-items-center">
                                            <c:forEach begin="1" end="5"> 
                                                <svg class="star star-fill" style="stroke: gold">
                                                <use xlink:href="#star-empty"></use>
                                                </svg>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <span class="price text-primary fw-bold mb-2 fs-5"><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="₫" groupingUsed="true" /></span>
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
    <!--END RELATED PRODUCT-->

    <!--DESCRIPTION-->
    <section class="product-tabs">
        <div class="container">
            <div class="row">
                <div class="tabs-listing">
                    <nav>                                
                        <div class="nav nav-tabs d-flex justify-content-center py-3" id="nav-tab" role="tablist">
                            <h4>Mô tả sản phẩm</h4>
                        </div>
                    </nav>
                    <div class="tab-content border-bottom py-4" id="nav-tabContent">

                        <!-- DESCRIPTION -->
                        <div class="tab-pane fade active show" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                            <p>${requestScope.product.description}</p>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </section>
    <!--modal-->
    <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header" style="border-bottom: none">
                </div>
                <div class="modal-body d-flex justify-content-center align-items-center" style="text-align: center">
                    <h4>Đánh giá của bạn sẽ được kiểm duyệt trước khi hiển thị</h4>
                </div>
            </div>
        </div>
    </div>
    <!--modal-->

    <!--DESCRIPTION-->
    <section class="product-tabs">
        <div class="container">
            <div class="row" style="border-radius: 10px;
                 box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Adds shadow for a subtle 3D effect */
                 border: 1px solid #ddd; /* Light border for better definition */
                 padding: 20px; /* Adds padding inside the border */
                 background-color: #fff;">
                <div class="tabs-listing">
                    <div >
                        <div id="notification-container" class="notification-container"></div>

                    </div>
                    <div class="row border-bottom">

                        <div id="feedback-section" class="rating text-warning align-items-start margin-small col-md-6"  style="margin-bottom: 20px">
                            <h4 style="font-weight: bold;
                                color: black">Đánh giá sản phẩm</h4>

                            <h3>${requestScope.avgRating} / 5</h3>
                            <div class="stars">
                                <c:forEach var="rate" begin="1" end="${requestScope.avgRating}">
                                    <svg class="star star-fill">
                                    <use xlink:href="#star-fill"></use>
                                    </svg>
                                </c:forEach>
                                <c:forEach var="rate" begin="1" end="${5 - requestScope.avgRating}">
                                    <svg class="star star-empty" style="stroke: gold">
                                    <use xlink:href="#star-empty"></use>
                                    </svg>
                                </c:forEach>
                            </div>
                            <p>(${requestScope.quantityFeedback} đánh giá)</p>
                        </div>
                        <c:choose>
                            <c:when test="${sessionScope.account eq null}">
                                <div class="add-review margin-small col-md-6 text-md-right" style="align-items: center">
                                    <p>Chỉ có thành viên mới có thể viết nhận xét. 
                                        Vui lòng 
                                        <a href="login?productId=${requestScope.product.productId}&index=1" class="linkLogin">đăng nhập</a>  
                                    </p>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="add-review margin-small col-md-6 text-md-right" style="display: inline-block;
                                     text-align: center;">
                                    <form id="form" class="d-flex gap-3 flex-wrap" action="feedback" method="post" onsubmit="return validateForm()">
                                        <input type="hidden" name="productID" value="${requestScope.product.productId}">
                                        <input type="hidden" name="index" value="1">
                                        <input type="hidden" name="success" value="1">
                                        <div class="review-rating py-2 align-items-start">
                                            <div class="rate-box">
                                                <input type="radio" name="star" id="star0" value="5"/>
                                                <label class="star" for="star0"></label>
                                                <input type="radio" name="star" id="star1" value="4"/>
                                                <label class="star" for="star1"></label>
                                                <input type="radio" checked name="star" id="star2" value="3"/>
                                                <label class="star" for="star2"></label>
                                                <input type="radio" name="star" id="star3" value="2"/>
                                                <label class="star" for="star3"></label>
                                                <input type="radio" name="star" id="star4" value="1"/>
                                                <label class="star" for="star4"></label>
                                            </div>

                                        </div>
                                        <div class="w-100 d-flex align-items-start">
                                            <textarea  id="textArea" placeholder="Viết đánh giá của bạn" class="form-control feedback-textarea" name="feedbackText" oninput="validateTextarea(this)" style="font-size: 16px;
                                                       height: 9rem"></textarea>
                                        </div>
                                        <div>
                                            <span class="validation-message" id="validationMessage" style="color: red;
                                                  display: none">
                                                Vui lòng điền ít nhất 100 kí tự.
                                            </span>
                                        </div></br>
                                        <div style="margin-top: auto;
                                             margin-left: auto;">
                                            <button type="submit" name="submit" style="background-color: #007bff;
                                                    color: white;
                                                    border: none;
                                                    padding: 10px 15px;
                                                    border-radius: 4px;
                                                    cursor: pointer;
                                                    font-size: 14px;">Gửi đánh giá</button>
                                        </div>

                                    </form>
                                </div>
                                <script>
                                    function validateTextarea(textarea) {
                                        var message = document.getElementById('validationMessage');
                                        var inputValue = textarea.value.trim();

                                        if (inputValue.length < 100) {
                                            message.style.display = 'block';
                                            return false;
                                        } else {
                                            message.style.display = 'none';
                                            return true;
                                        }
                                    }
                                    function validateForm() {
                                        var textarea = document.getElementsByName('feedbackText')[0];
                                        return validateTextarea(textarea);
                                    }
                                </script>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${requestScope.listFeedback != null}">
                            <div class="filterFeedback col-lg-6">
                                <a href="single?page=1&productID=${requestScope.product.productId}&index=1" class="filter  ${requestScope.rating eq null ?'active':''}" style="margin-bottom: 10px" >Mới nhất</a>
                                <a href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=5&index=1" class="filter ${requestScope.rating eq "5" ?'active':''}" style="margin-bottom: 10px">5 Sao</a>
                                <a href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=4&index=1" class="filter ${requestScope.rating eq "4" ?'active':''}"" style="margin-bottom: 10px">4 Sao</a>
                                <a href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=3&index=1" class="filter ${requestScope.rating eq "3" ?'active':''}"" style="margin-bottom: 10px">3 Sao</a>
                                <a href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=2&index=1" class="filter ${requestScope.rating eq "2" ?'active':''}"" style="margin-bottom: 10px">2 Sao</a>
                                <a href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=1&index=1" class="filter ${requestScope.rating eq "1" ?'active':''}"" style="margin-bottom: 10px">1 Sao</a>
                            </div>
                        </c:if>
                    </div>

                    <div class="tab-content py-4" id="nav-tabContent">
                        <!-- FEEDBACK -->
                        <div class="tab-pane fade  active show" id="nav-review" role="tabpanel" aria-labelledby="nav-review-tab">
                            <div class="review-box review-style d-flex gap-3 flex-column">
                                <c:forEach var="feedback" items="${requestScope.listFeedback}">
                                    <div class="review-item d-flex row">
                                        <div class="col-md-3">
                                            <div class="image-holder me-2" style="display: flex">
                                                <img src="${feedback.account.imgAccount}" alt="review" class="img-fluid rounded-3" style="width:4rem;
                                                     margin-right: 1rem">
                                                <div style="display: flex;
                                                     flex-direction: column;">
                                                    <span class="author-name" style="font-weight: bold">${feedback.account.fullName}</span>
                                                    <span class="review-date" style="font-size: 15px">${feedback.feedbackDate}</span>
                                                </div>
                                            </div>
                                            <div class="review-content">
                                            </div>
                                        </div>
                                        <div class="col-md-9">
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
                                            <p style="font-size: 16px;
                                               font-weight: 400">${feedback.comments}</p>
                                        </div>

                                    </div>
                                </c:forEach>
                                <!--endPage,page,listFeedback-->
                                <c:set var="total" value="${requestScope.endPage}" />
                                <c:set var="page" value="${requestScope.page}" />
                                <c:if test="${requestScope.listFeedback != null}">
                                    <nav class="pt-5 border-top" aria-label="Page navigation">
                                        <ul class="pagination justify-content-center gap-5">
                                            <li class="page-item">
                                                <c:if test="${page > 1}">
                                                    <a class="page-link" href="single?page=${page-1}&productID=${requestScope.product.productId}&rating=${requestScope.rating}" >   <   </a>
                                                </c:if>
                                            </li>
                                            <c:if test="${total <= 5}">
                                                <c:forEach begin="1" end="${total}" var="pageNum">
                                                    <li class="page-item ${page == pageNum ? 'active' : ''}">
                                                        <a class="page-link" href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=${requestScope.rating}" ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                                    </li>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${total > 5}">
                                                <li class="page-item ${page == 1 ? 'active' : ''}">
                                                    <a class="page-link" href="single?page=1&productID=${requestScope.product.productId}&rating=${requestScope.rating}"  ${page == 1 ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>1</a>
                                                </li>
                                                <c:if test="${page > 3}">
                                                    <li class="page-item disabled">
                                                        <span class="page-link">...</span>
                                                    </li>
                                                </c:if>
                                                <c:forEach begin="${page > 2 ? page - 1 : 2}" end="${page < total - 1 ? page + 1 : total - 1}" var="pageNum">
                                                    <li class="page-item ${page == pageNum ? 'active' : ''}">
                                                        <a class="page-link" href="single?page=${pageNum}&productID=${requestScope.product.productId}&rating=${requestScope.rating}"  ${page == pageNum ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                                    </li>
                                                </c:forEach>
                                                <c:if test="${page < total - 2}">
                                                    <li class="page-item disabled">
                                                        <span class="page-link">...</span>
                                                    </li>
                                                </c:if>
                                                <li class="page-item ${page == total ? 'active' : ''}">
                                                    <a class="page-link" href="single?page=${total}&productID=${requestScope.product.productId}&rating=${requestScope.rating}" ${page == total ? 'style="background-color: #F86D72; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${total}</a>
                                                </li>
                                            </c:if>
                                            <li class="page-item">
                                                <c:if test="${page < total}">
                                                    <a class="page-link" href="single?page=${page+1}&productID=${requestScope.product.productId}&rating=${requestScope.rating}" > > </a>
                                                </c:if>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <script>
                                    // Hàm kiểm tra nếu URL chứa tham số index
                                    function scrollToFeedbackSection() {
                                        // Lấy giá trị của tham số index từ URL
                                        const urlParams = new URLSearchParams(window.location.search);
                                        const indexParam = urlParams.get('index');
                                        const successParam = urlParams.get('success');

                                        // Nếu có tham số index và phần feedback có sẵn
                                        if (indexParam && document.getElementById('feedback-section')) {
                                            // Cuộn trang đến vị trí của phần feedback
                                            document.getElementById('feedback-section').scrollIntoView({
                                                behavior: 'smooth', // Cuộn mượt
                                                block: 'start' // Đặt phần tử đến vị trí đầu của viewport
                                            });
                                            if (successParam) {
                                                $('#feedbackModal').modal('show');
                                            }
                                            // Hiển thị modal nếu notification là 'display'
                                        }
                                    }
                                    // Gọi hàm này khi trang đã load hoàn toàn
                                    window.onload = scrollToFeedbackSection;
    </script>
    <jsp:include page="../common/footer.jsp"></jsp:include>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</body>

</html>
