<%-- 
    Document   : Cart.jsp
    Created on : May 15, 2024, 11:03:49 PM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="Models.Cart" %>
<%@ page import="Models.Item" %>
<%@ page import="Models.Product" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Bookly - Bookstore eCommerce Website Template</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            .container-error {
                text-align: center;
                background: #fff;
                border-radius: 10px;
                margin-top: 100px;
                margin-bottom: 100px;
            }
            .icon {
                font-size: 100px;
                color: #d9534f;
                margin-bottom: 20px;
            }
            .message {
                font-size: 30px;
                font-weight: bold;
                color: #d9534f;
                margin-bottom: 10px;
            }
            .error-details {
                font-size: 20px;
                margin-bottom: 30px;
            }
            .btn.secondary {
                background-color: #6c757d;
            }
            .btn:hover {
                background-color: black;
            }
            .btn.secondary:hover {
                background-color: #5a6268;
            }
        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <div class="container-error">
                <div class="icon">
                    <i class="fas fa-times-circle"></i>
                </div>
            <c:choose>
                <c:when test="${requestScope.message ne 'orderFail'}">
                    <div class="message">
                        Đặt hàng không thành công
                    </div>
                    <div class="error-details">
                        Có lỗi xảy ra trong quá trình đặt hàng.
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="message">
                        Thanh toán thất bại
                    </div>
                    <div class="error-details">
                        Có lỗi xảy ra trong quá trình thanh toán.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <jsp:include page="../common/footer.jsp"></jsp:include>

        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>
</html>
