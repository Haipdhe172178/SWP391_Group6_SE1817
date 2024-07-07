<%-- 
    Document   : Checkout.jsp
    Created on : May 15, 2024, 11:09:07 PM
    Author     : huyca
--%>

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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
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
        </style>
    </head>

    <body>



        <jsp:include page="../common/header.jsp"></jsp:include>
            <h3>Đơn hàng của tôi</h3>

            <div class="search-box">
                <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm...">
            </div>

            <div class="container">
                <ul id="navbar" class="navbar-nav d-flex justify-content-center justify-content-lg-start align-items-center flex-wrap flex-column flex-lg-row">
                    <li class="nav-item">
                        <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=all">Tất cả <span>${all}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=pending">Chờ xác nhận <span>${pendingCount}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=confirmed">Đã xác nhận <span>${confirmedCount}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=shipping">Chờ giao hàng <span>${shippingCount}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=completed">Hoàn thành <span>${completedCount}</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link me-4" href="ordercustomer?accountId=${requestScope.accountId}&status=canceled">Đã hủy <span>${canceledCount}</span></a>
                </li>
            </ul>
        </div>




      <style>
    .clickable-row {
        cursor: pointer;
    }
</style>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const rows = document.querySelectorAll(".clickable-row");
        rows.forEach(row => {
            row.addEventListener("click", function() {
                window.location.href = row.dataset.href;
            });
        });
    });
</script>

<c:choose>
    <c:when test="${requestScope.noOrders}">
        <p>Bạn chưa có đơn hàng nào</p>
    </c:when>
    <c:otherwise>
        <table class="table">
            <thead>
                <tr>
                    <th>Mã đơn hàng</th>
                    <th>Sản phẩm</th>
                    <th>Tổng giá tiền</th>
                    <th>Ngày mua</th>
                    <th>Trạng thái</th>
                    <th>Hành động</th> <!-- Thêm cột mới cho hành động -->
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr class="clickable-row" data-href="ordercustomerdetail?orderId=${order.orderDetails[0].orderCId}">
                        <td>${order.orderDetails[0].orderCId}</td>
                        <td>${order.orderDetails[0].productId}</td>
                        <td>
                            <fmt:formatNumber value="${order.totalPrice}" type="number" minFractionDigits="0" maxFractionDigits="0"/> VND
                        </td>
                        <td><fmt:formatDate value="${order.date}" pattern="dd-MM-yyyy"/></td>
                        <td>${order.status.statusName}</td>
                        <td>
                            <c:if test="${order.status == 'Chờ xác nhận' || order.status == 'Đã xác nhận'}">
                                <form action="ordercustomer" method="post">
                                    <input type="hidden" name="action" value="cancel">
                                    <input type="hidden" name="orderId" value="${order.orderId}">
                                    <textarea name="cancelReason" rows="2" cols="20" placeholder="Nhập lý do hủy"></textarea>
                                    <button type="submit">Hủy đơn hàng</button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

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
                                        "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người ta càng thấy còn nhiều điều cần phải đọc.” John Adams</p>
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
                                    <p class="mb-0">© 2024 <a href="#" class="text-white">Bookly</a>. All Rights Reserved.</p>
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