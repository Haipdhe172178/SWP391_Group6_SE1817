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
        <!-- Sing in  Form -->
        <div class="main">
            <section class="sign-in" style="margin: 5em; ">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="images/SB88.png" alt="sing up image"></figure>
                            <a href="register" class="signup-image-link">Bạn Chưa Có Tài Khoản</a>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Đăng nhập</h2>
                            <form method="POST" class="register-form" id="login-form">
                                <c:if test="${not empty errorMessage}">
                                    <div class="error-message" style="color: red;">${errorMessage}</div>
                                </c:if>
                                <div class="form-group">
                                    <label for="your_name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="your_name" id="your_name" placeholder="Tài khoản"/>
                                </div>
                                <div class="form-group">
                                    <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="your_pass" id="your_pass" placeholder="Mật khẩu"/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                    <label for="remember-me" class="label-agree-term"><span><span></span></span>Ghi nhớ đăng nhập</label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="Đăng nhập"/>
                                </div>
                            </form>
                            <div class="social-login">
                                <span class="social-label">Đăng nhập với</span>
                                <ul class="socials">
                                    <li><a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=652190424847-po8btulm9gsk84b8o7hfmsdjs597p866.apps.googleusercontent.com&redirect_uri=http://localhost:9999/SWP391/LoginGoogleHandler&response_type=code&scope=profile%20email&access_type=online"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>