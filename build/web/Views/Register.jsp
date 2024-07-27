<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Form by Colorlib</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style_2.css">
        <style>
            body{
                background-image: url('images/banner-image-bg-1.jpg');
            }
        </style>
    </head>
    <body>

        <div class="main">
            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Đăng ký</h2>

                            <c:if test="${not empty requestScope.notification}">
                                <div class="notification">
                                    <p style="color: red;">${requestScope.notification}</p>
                                </div>
                            </c:if>
                            <form action="register" method="post" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="name" placeholder="Họ Và Tên" value="${param.name}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" name="email" placeholder="Email" value="${param.email}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account-circle"></i></label>
                                    <input type="text" name="username" pattern="[a-zA-Z0-9]{3,20}" placeholder="Tên đăng nhập" value="${param.username}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="pass" placeholder="Mật khẩu" pattern="[a-zA-Z0-9]{8,}" title="Mật khẩu phải dài ít nhất 8 ký tự và chỉ chứa chữ cái và số" required />
                                </div>
                                <div class="form-group">
                                    <label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" name="re_pass" placeholder="Nhập lại mật khẩu" required/>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber"><i class="zmdi zmdi-phone"></i></label>
                                    <input type="tel" name="phoneNumber" placeholder="Số điện thoại" pattern="0\d{9}" title="Vui lòng nhập số điện thoại hợp lệ bắt đầu bằng 0 và dài đúng 10 chữ số" value="${param.phoneNumber}" required />
                                </div>
                                <div class="form-group">
                                    <label for="address"><i class="zmdi zmdi-directions"></i></label>
                                    <input type="text" name="address" placeholder="Địa chỉ" value="${param.address}" required/>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" name="agree-term" class="agree-term" id="agree-term-checkbox" ${param.agreeTerm == null ? "" : "checked"} required/>
                                    <label for="agree-term-checkbox" class="label-agree-term" >
                                        <span></span> Tôi đồng ý với tất cả <a href="#" class="term-service" >Điều khoản dịch vụ</a>
                                    </label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="Đăng ký"/>
                                </div>
                            </form>
                        </div>
                        <div class="signup-image">
                            <figure><img src="images/SB88.png" alt="sing up image"></figure>
                            <a href="login" class="signup-image-link">Tôi đã có tài khoản</a>
                        </div>
                    </div>
                </div>
            </section>
        </div>

        <!-- JS -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="js/main.js"></script>
    </body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
