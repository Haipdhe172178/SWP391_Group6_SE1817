<%-- 
    Document   : Profile
    Created on : May 25, 2024, 5:49:46 PM
    Author     : Hai Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Profile</title>
        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css' rel='stylesheet'>
        <link href='#' rel='stylesheet'>
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

        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>

            .add-experience:hover {
                background: #f86d72;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }

            .profile{
                border-radius: 2em;
                box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.3),
                    -3px -3px 6px rgba(255, 255, 255, 0.5);
            }
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
            #customButton {
                margin-left: 5px;
                padding: 10px 20px;
                background-color: #016dd8;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }

            #customButton:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <jsp:include page="../common/header.jsp"></jsp:include>
        <body className='snippet-body'>
        <c:set var="acc" value="${sessionScope.account}"/>
        <div class="container rounded bg-white mt-5 mb-5" style="width: 60%">
            <div class="row profile">
                <div class="col-md-4 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="${acc.imgAccount}"><span class="font-weight-bold">${acc.fullName}</span><span class="text-black-50">${acc.email}</span><span> </span></div>
                    <center>
                        <form id="uploadForm" action="profile" method="post" enctype="multipart/form-data">
                            <input type="file" id="avatarInput" name="avatar" style="display:none" required oninvalid="this.setCustomValidity('Vui lòng chọn một tệp hình ảnh để tải lên')" onchange="document.getElementById('uploadForm').submit()">
                            <button type="button" id="customButton">Chọn Ảnh</button>
                            <input type="hidden" value="changeAvt" name="action">
                        </form>
                    </center>
                    <script>
                        document.getElementById('customButton').addEventListener('click', function () {
                            document.getElementById('avatarInput').click();
                        });
                    </script>
                </div>
                <div class="col-md-8" >
                    <!-- Profile -->
                    <div class="p-3 py-5" id="profile">
                        <form action="profile" method="post">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Hồ Sơ Của Tôi</h4>                    <h5 style="color: red">${requestScope.message}</h5>

                                <span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Đổi mật khẩu</span>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-6 inputprofile"><label class="labels">Họ và tên</label><input type="text" name="fullname" class="form-control" placeholder="full name" value="${acc.fullName}" ></div>
                                <div class="col-md-6 "><label class="labels">Tên đăng nhập</label><input type="text" name="username" class="form-control" value="${acc.userName}" placeholder="username" disabled></div>
                            </div>

                            <div class="row mt-3">
                                <label class="labels">Giới tính</label>
                                <div class="d-flex align-items-center gender-options">
                                    <div class="form-check form-check-inline">
                                        <input type="radio" name="gender" class="form-check-input" value="Male"
                                               <c:if test="${acc.gender eq 'Male'}">checked</c:if>> Nam
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input type="radio" name="gender" class="form-check-input" value="Female"
                                            <c:if test="${acc.gender eq 'Female'}">checked</c:if>> Nữ
                                        </div>
                                        <div class="form-check form-check-inline">
                                            <input type="radio" name="gender" class="form-check-input" value="Other"
                                            <c:if test="${acc.gender eq 'Other'}">checked</c:if>> Khác
                                        </div>

                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12 inputprofile"><label class="labels">Số điện thoại</label><input type="text" name="phonenumber" class="form-control" placeholder="enter phone number" value="${acc.phoneNumber}" required pattern="^0\d{9}$" oninvalid="this.setCustomValidity('Vui lòng nhập số điện thoại đúng định dạng 0xxxxxxxxx')" onchange="this.setCustomValidity('')"></div>
                                <div class="col-md-12 inputprofile"><label class="labels">Địa chỉ</label><input type="text" name="address" class="form-control" placeholder="enter address" value="${acc.address}" required oninvalid="this.setCustomValidity('Vui lòng nhập địa chỉ')" ></div>
                                <div class="col-md-12 "><label class="labels">Email</label><input type="text" name="email" class="form-control" placeholder="enter email" value="${acc.email}" disabled></div>
                            </div>
                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button buttonprofile" type="submit" value="changeInfo" name="action">Lưu</button>
                            </div>
                        </form>
                    </div>

                    <!-- Change Password -->
                      <div class="p-3 py-5" id="changepass" style="display: none;">
                        <form action="changepassword" method="post">
                            <input type="hidden" name="username" value="${sessionScope.account.userName}">
                            <div class="d-flex justify-content-between align-items-center mb-3">
                                <h4 class="text-right">Đổi mật khẩu</h4>
                            </div>
                            <div class="row mt-2">
                                <div class="col-md-12">
                                    <label class="labels">Mật khẩu hiện tại</label>
                                    <input type="password" class="form-control" placeholder="nhập mật khẩu hiện tại" name="oldpassword" required>
                                    <h4 style="color: red">${requestScope.ms}</h4>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Mật khẩu mới</label>
                                    <input type="password" class="form-control" placeholder="nhập mật khẩu mới" name="newpassword" required>
                                    <p class="labels">Mật khẩu của bạn phải có tối thiểu 8 ký tự, đồng thời bao gồm cả chữ số, chữ cái và ký tự đặc biệt (!@$%#).</p>
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">Xác nhận mật khẩu mới</label>
                                    <input type="password" class="form-control" placeholder="xác nhận mật khẩu mới" name="confirmpassword" required>
                                    <h4 style="color: red">${requestScope.m}</h4>
                                </div>
                            </div>
                            <h4 style="color: green">${requestScope.mess}</h4>
                            <div class="mt-5 text-center">
                                <button class="btn btn-primary profile-button" type="submit" value="CHANGE">Đổi mật khẩu</button>
                                <button class="btn btn-secondary profile-button" type="button" onclick="document.getElementById('changepass').style.display = 'none'">Thoát</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../common/footer.jsp"></jsp:include>
    <script type='text/javascript' src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>
    <script type='text/javascript' src='#'></script>
    <script type='text/javascript' src='#'></script>
    <script type='text/javascript' src='#'></script>
    <script type='text/javascript'>#</script>
    <script type='text/javascript'>
        var myLink = document.querySelector('a[href="#"]');
        myLink.addEventListener('click', function (e) {
            e.preventDefault();
        });
    </script>
<% Boolean showChangePassword = (Boolean) request.getAttribute("showChangePassword"); %>

<script>
    window.onload = function () {
    <% if (showChangePassword != null && showChangePassword) { %>
        showChangePassword();
    <% } else { %>
        showProfile();
    <% } %>
    }

    // Hàm để ẩn phần tử có id là "profile" và hiển thị phần tử có id là "changepass"
    function showChangePassword() {
        document.getElementById('profile').style.display = 'none';
        document.getElementById('changepass').style.display = '';
    }

    // Hàm để ẩn phần tử có id là "changepass" và hiển thị phần tử có id là "profile"
    function showProfile() {
        document.getElementById('profile').style.display = '';
        document.getElementById('changepass').style.display = 'none';
    }

    // Sự kiện click cho nút "Change Password"
    document.querySelector('.btn.btn-secondary.profile-button').addEventListener('click', function () {
        showChangePassword();
    });

    // Sự kiện click cho nút "Cancel" trong form "Change Password"
    document.querySelector('.p-3.py-5#changepass .btn.btn-secondary.profile-button').addEventListener('click', function () {
        showProfile();
    });

    // Sự kiện click cho nút "Edit Profile"
    document.querySelector('.add-experience').addEventListener('click', function () {
        showChangePassword();
    });
</script>
</html>
