<%-- 
    Document   : Register
    Created on : May 28, 2024, 8:26:03 PM
    Author     : Hai Pham
--%>

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
                            <form action="register" method="post" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="name" placeholder="Họ Và Tên" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-email"></i></label>
                                    <input type="email" name="email" placeholder="Email" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-account-circle"></i></label>
                                    <input type="text" name="username"  placeholder="Tên đăng nhập" required/>
                                </div>
                                <div class="form-group">
                                    <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="pass" placeholder="Mật khẩu" required/>
                                </div>
                                <div class="form-group">
                                    <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                    <input type="password" name="re_pass" placeholder="Nhập lại mật khẩu" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-phone"></i></label>
                                    <input type="tel" name="phoneNumber" placeholder="Số điện thoại" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email"><i class="zmdi zmdi-directions"></i></label>
                                    <input type="text" name="address" placeholder="Địa chỉ" required/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="agree-term" class="agree-term" required/>
                                    <label for="agree-term" class="label-agree-term" ><span><span></span></span>Tôi đồng ý với tất cả   <a href="#" class="term-service" >Điều khoản dịch vụ</a></label>
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

