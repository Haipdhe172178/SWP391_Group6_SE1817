<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
              rel="stylesheet">
        <script src="./js/jquery-1.11.3.min.js"></script>
        <style>
            .form-control {
                height: 60px;
                width: 100%;
            }

            .form-select-sm {
                font-size: 20px;
            }

            .address-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 10px;
            }

            .edit-link {
                margin-left: 4rem;
            }

            .add-address {
                display: flex;
                align-items: center;
                cursor: pointer;
                margin-top: 20px;
            }

            .add-address i {
                margin-right: 5px;
            }

            h3 {
                text-align: center;
                margin: 20px 0;
                color: #333;
            }

            .search-box {
                margin-bottom: 20px;
                display: flex;
                justify-content: flex-end;
            }

            .search-box input {
                width: 300px;
                height: 35px;
                padding: 5px 10px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            }

            .search-box input:focus {
                outline: none;
                border-color: #80bdff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
            }

            .order-section {
                border: 1px solid #ced4da;
                background-color: #f8f9fa;
                padding: 20px;
                margin-bottom: 20px;
            }

            .footer-menu a.active {
                color: #007bff;
                font-weight: bold;
            }
            .tracking-section {
                margin: 20px 0;
            }

            .tracking-header {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .tracking-header h4 {
                margin: 0;
            }

            .tracking-header button {
                background-color: #dc3545;
                border: none;
                padding: 5px 10px;
                color: white;
                border-radius: 5px;
                cursor: pointer;
            }

            .tracking-steps {
                display: flex;
                justify-content: space-between;
                margin-top: 20px;
            }

            .tracking-step {
                text-align: center;
                flex-grow: 1;
                position: relative;
            }

            .tracking-step:before,
            .tracking-step:after {
                content: '';
                position: absolute;
                top: 50%;
                width: 50%;
                height: 4px;
                background-color: #ddd;
                z-index: 1;
            }

            .tracking-step:before {
                left: 0;
            }

            .tracking-step:after {
                right: 0;
            }

            .tracking-step:first-child:before,
            .tracking-step:last-child:after {
                display: none;
            }

            .tracking-icon {
                width: 30px;
                height: 30px;
                background-color: #ddd;
                border-radius: 50%;
                display: inline-block;
                line-height: 30px;
                margin-bottom: 5px;
                position: relative;
                z-index: 2;
            }

            .tracking-step.active .tracking-icon {
                background-color: #28a745;
                color: white;
            }

            .tracking-step.cancelled .tracking-icon {
                background-color: #dc3545;
                color: white;
            }

            .tracking-step span {
                display: block;
                font-size: 14px;
            }
            .order-details-container {
                display: flex;
                justify-content: space-between;
                padding: 20px;
                background-color: #f9f9f9;
                border: 1px solid #ddd;
            }

            .address-container, .payment-method, .total-amount {
                width: 30%;
                padding: 10px;
                background-color: #fff;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            }

            .address-container h5, .payment-method h5, .total-amount h5 {
                margin-bottom: 10px;
                font-size: 16px;
                color: #333;
            }

            .address-container p, .payment-method p, .total-amount p {
                margin: 5px 0;
                font-size: 14px;
                color: #555;
            }

        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>

            <div class="order-section">
                <h3>Chi tiết sản phẩm</h3>
                <button>
                    <a href="/SWP391/ordercustomer">Quay lại</a>
                </button>

                <div class="order-details-container">
                    <div class="address-container">
                        <h5>Thông tin người nhận</h5>
                        <p>${order.account.fullName}</p>
                    <p>Tel: ${order.account.phoneNumber}</p>
                    <p>🏠 ${shippingAddress.address}</p>
                </div>
                <div class="payment-method">
                    <h5>Phương thức thanh toán</h5>
                    <c:choose>
                        <c:when test="${order.paymentStatus == 1}">
                            <p>Đã thanh toán</p>
                        </c:when>
                        <c:otherwise>
                            <p>Chưa thanh toán</p>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="total-amount">
                    <h5>Tổng tiền</h5>
                    <p>Tạm tính:<fmt:formatNumber value="${order.totalPrice} " type="number" minFractionDigits="0" maxFractionDigits="0" /> VND</p>
                    <p>Phí vận chuyển: 20.000 VND</p>
                </div>
            </div>
                    <a href="Views/TrackOrder.jsp">Theo dõi đơn hàng</a>
            <table class="table">
                <thead>
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${orderDetails}">
                        <tr>
                            <td>${detail.orderCId}</td>
                            <td>
                                <a href="single?productID=${detail.product.productId}">
                                    <img src="${detail.product.imgProduct}" alt="${detail.product.name}" width="50" height="50" />
                                    ${detail.product.name}
                                </a>
                            </td>
                            <td>${detail.quantity}</td>
                            <td>
                                <fmt:formatNumber value="${detail.unitPrice}" type="number" minFractionDigits="0" maxFractionDigits="0" /> VND
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="footer-top-area">
                        <div class="row d-flex flex-wrap justify-content-between">
                            <div class="col-lg-3 col-sm-6 pb-3">
                                <div class="footer-menu">
                                    <img src="images/anh456.png" alt="logo" class="img-fluid mb-2">
                                    <p>
                                        "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người
                                        ta càng thấy còn nhiều điều cần phải đọc.” John Adams
                                    </p>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-6 pb-3">
                                <div class="footer-menu text-capitalize">
                                    <h5 class="widget-title pb-2">Trang chính</h5>
                                    <ul class="menu-list list-unstyled text-capitalize">
                                        <li class="menu-item mb-1">
                                            <a href="home" class="${status == 'home' ? 'active' : ''}">Trang chủ</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="about" class="${status == 'about' ? 'active' : ''}">Giới thiệu</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="shop" class="${status == 'shop' ? 'active' : ''}">Sản phẩm</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="blog" class="${status == 'blog' ? 'active' : ''}">Tin tức</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="contact" class="${status == 'contact' ? 'active' : ''}">Liên hệ</a>
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
                                    <h5 class="widget-title pb-2">Thông tin liên hệ</h5>
                                    <ul class="list-unstyled mb-0">
                                        <li class="d-flex mb-1">
                                            <span class="me-2">
                                                <i class="fas fa-map-marker-alt"></i>
                                            </span>
                                            <p class="mb-0">Address: 123, Street Name, City</p>
                                        </li>
                                        <li class="d-flex mb-1">
                                            <span class="me-2">
                                                <i class="fas fa-phone"></i>
                                            </span>
                                            <p class="mb-0">Phone: +123 456 789</p>
                                        </li>
                                        <li class="d-flex mb-1">
                                            <span class="me-2">
                                                <i class="fas fa-envelope"></i>
                                            </span>
                                            <p class="mb-0">Email: info@example.com</p>
                                        </li>
                                        <li class="d-flex mb-1">
                                            <span class="me-2">
                                                <i class="fas fa-globe"></i>
                                            </span>
                                            <p class="mb-0">Website: www.example.com</p>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="footer-bottom-area">
                        <div class="d-flex flex-wrap justify-content-between">
                            <div class="payment-card d-flex flex-column">
                                <h6>We accept</h6>
                                <img src="images/payment.png" alt="payment" class="img-fluid">
                            </div>
                            <div class="footer-bottom-wrapper">
                                <div class="social-links">
                                    <ul class="list-unstyled d-flex justify-content-end mb-0">
                                        <li class="me-2">
                                            <a href="#" class="text-white">
                                                <i class="fab fa-facebook-f"></i>
                                            </a>
                                        </li>
                                        <li class="me-2">
                                            <a href="#" class="text-white">
                                                <i class="fab fa-twitter"></i>
                                            </a>
                                        </li>
                                        <li class="me-2">
                                            <a href="#" class="text-white">
                                                <i class="fab fa-linkedin-in"></i>
                                            </a>
                                        </li>
                                        <li class="me-2">
                                            <a href="#" class="text-white">
                                                <i class="fab fa-instagram"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="copyright">
                                    <p class="mb-0">© 2024 <a href="#" class="text-white">Bookly</a>. All Rights
                                        Reserved.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const searchInput = document.getElementById('searchInput');
                searchInput.addEventListener('keyup', function () {
                    const filter = searchInput.value.toLowerCase();
                    const rows = document.querySelectorAll('#orderTable tbody tr');
                    rows.forEach(row => {
                        const cells = row.getElementsByTagName('td');
                        let match = false;
                        for (let i = 0; i < cells.length; i++) {
                            if (cells[i].textContent.toLowerCase().includes(filter)) {
                                match = true;
                                break;
                            }
                        }
                        row.style.display = match ? '' : 'none';
                    });
                });
            });
        </script>
    </body>

</html>
