<%-- 
    Document   : Login
    Created on : May 28, 2024, 7:37:32 PM
    Author     : Asus
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Sign Up Form by Colorlib</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="./css/style_2.css">
        <style>
            body{
                background-image: url('images/banner-image-bg-1.jpg');
            }
        </style>
    </head>
    <body>
         <!-- Forgot Password Form -->
    <div class="main">
        <section class="sign-in" style="margin: 5em;">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure><img src="images/SB88.png" alt="forgot password image"></figure>
                    </div>

                    <div class="signin-form">
                        <h3 class="form-title">Quên mật khẩu</h3>
                        <p>Cung cấp địa chỉ email được liên kết với tài khoản của bạn để khôi phục mật khẩu.</p>
                        <form method="Get" class="register-form" id="forgot-form" action="forgotPassword">
                            <div class="form-group">
                                    <h4>Nhập địa chỉ email:</h4>
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}" name="email" placeholder="Email" required/>
                                                      <small class="form-text text-muted">Nhập địa chỉ email đã đăng ký. Chúng tôi sẽ gửi mã OTP đến địa chỉ này.</small>
                            </div>
                            <div class="form-group form-button">
                                <button  class="form-submit" value="${message}" type="submit">Lấy mật khẩu mới</button>
                            </div>
                            <a href="login" class="btn btn-danger">Đăng nhập</a>
                            <a href="register" class="btn btn-danger">Đăng ký</a>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </div>
    </body>
</html>