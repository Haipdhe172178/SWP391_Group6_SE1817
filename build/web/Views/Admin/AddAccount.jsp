<%-- 
    Document   : AddAcount.jsp
    Created on : Jun 18, 2024, 10:50:22 AM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>

        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Sales</title>
        <link rel="stylesheet" href="css/bootstrap1.min.css" />
        <link rel="stylesheet" href="vendors/themefy_icon/themify-icons.css" />
        <link rel="stylesheet" href="vendors/scroll/scrollable.css" />
        <link rel="stylesheet" href="vendors/font_awesome/css/all.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/responsive.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/buttons.dataTables.min.css" />
        <link rel="stylesheet" href="css/metisMenu.css">
        <link rel="stylesheet" href="css/style1.css" />
        <title> Thêm Tài Khoản</title>
        <style>
            .error {
                color: red;
                font-size: 14px;
                margin-top: 5px;
            }
            .notification-container {
                position: fixed;
                top: 10px;
                right: 10px;
                display: none;
                z-index: 1000;
                animation: slideIn 0.5s ease-in-out, slideOut 0.5s ease-in-out 4.5s;
            }

            .notification {
                background-color: #4CAF50;
                color: white;
                padding: 15px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            .notification.success {
                background-color: #4CAF50;
                color: white;
            }

            .notification.error {
                background-color: #f44336;
                color: white;
            }
        </style>
    </head>

    <body class="crm_body_bg">

        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">

            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                <div id="notification-container" class="notification-container"></div>
                <div class="main_content_iner">
                    <div class="container-fluid p-0">
                        <div class="row justify-content-center">
                            <div class="col-lg-12">
                                <div class="white_card card_height_100 mb_30">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Thêm Tài Khoản</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <form action="addac" method="POST" enctype="multipart/form-data" id="accountForm">
                                        <div class="white_card_body">
                                            <div class="mb-3">
                                                <label for="fullName">Họ và tên</label>
                                                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Nhập họ và tên" value="${requestScope.fullName}" required>
                                            <div id="fullNameError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="userName">Tên tài khoản</label>
                                            <input type="text" class="form-control" id="userName" name="userName" placeholder="nhập tên tài khoản" value="${requestScope.userName}" required>
                                            <div id="userNameError" class="error">${requestScope.userNameError}</div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="passWord">Mật khẩu</label>
                                            <input type="password" class="form-control" id="passWord" name="passWord" placeholder="nhập mật khẩu" value="${requestScope.passWord}" required>
                                            <div id="passWordError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="confirmPassWord">Nhập lại mật khẩu</label>
                                            <input type="password" class="form-control" id="confirmPassWord" name="confirmPassWord" placeholder="nhập lại mật khẩu" value="${requestScope.confirmPassWord}" required>
                                            <div id="confirmPassWordError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="gender">Giới tính</label><br>
                                            <input type="radio" id="male" name="gender" value="male" ${requestScope.gender == 'male' ? 'checked' : ''} required>
                                            <label for="male">Nam</label><br>
                                            <input type="radio" id="female" name="gender" value="female" ${requestScope.gender == 'female' ? 'checked' : ''} required>
                                            <label for="female">Nữ</label>
                                            <div id="genderError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="email">Email</label>
                                            <input type="text" class="form-control" id="email" name="email" placeholder="Nhập Email" value="${requestScope.email}" required>
                                            <div id="emailError" class="error">${requestScope.emailError}</div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="phoneNumber">Số điện thoại</label>
                                            <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Nhập số điện thoại" value="${requestScope.phoneNumber}" required>
                                            <div id="phoneNumberError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="address">Địa chỉ</label>
                                            <input type="text" class="form-control" id="address" name="address" placeholder="Nhập địa chỉ" value="${requestScope.address}" required>
                                            <div id="addressError" class="error"></div>
                                        </div>
                                        <div class="mb-3">
                                            <label for="imgAccount" class="form-label">Ảnh</label>
                                            <input type="file" class="form-control" id="imgAccount" name="imgAccount">
                                            <div id="imgAccountError" class="error"></div>
                                        </div> 
                                        <div>
                                            <button type="submit" class="btn btn-primary">Thêm tài khoản</button>
                                            <a href="manages" class="btn btn-warning">Trở lại</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="footer_part">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="footer_iner text-center">
                                <p>2020 © Influence - Designed by <a href="#"> <i class="ti-heart"></i> </a><a href="#"> Dashboard</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="notification-container" style="display:none;"></div>

        <div class="CHAT_MESSAGE_POPUPBOX">
            <div class="CHAT_POPUP_HEADER">
                <div class="MSEESAGE_CHATBOX_CLOSE">
                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M7.09939 5.98831L11.772 10.661C12.076 10.965 12.076 11.4564 11.772 11.7603C11.468 12.0643 10.9766 12.0643 10.6726 11.7603L5.99994 7.08762L1.32737 11.7603C1.02329 12.0643 0.532002 12.0643 0.228062 11.7603C-0.0760207 11.4564 -0.0760207 10.965 0.228062 10.661L4.90063 5.98831L0.228062 1.3156C-0.0760207 1.01166 -0.0760207 0.520226 0.228062 0.216286C0.379534 0.0646715 0.578697 -0.0114918 0.777717 -0.0114918C0.976738 -0.0114918 1.17576 0.0646715 1.32737 0.216286L5.99994 4.889L10.6726 0.216286C10.8243 0.0646715 11.0233 -0.0114918 11.2223 -0.0114918C11.4213 -0.0114918 11.6203 0.0646715 11.772 0.216286C12.076 0.520226 12.076 1.01166 11.772 1.3156L7.09939 5.98831Z" fill="white" />
                    </svg>
                </div>
                <h3>Chat with us</h3>
                <div class="Chat_Listed_member">
                    <ul>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/1.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/2.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/3.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/4.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/5.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <div class="more_member_count">
                                        <span>90+</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="CHAT_POPUP_BODY">
                <p class="mesaged_send_date">
                    Sunday, 12 January
                </p>
                <div class="CHATING_SENDER">
                    <div class="SMS_thumb">
                        <img src="img/staf/1.png" alt>
                    </div>
                    <div class="SEND_SMS_VIEW">
                        <P>Hi! Welcome . How can I help you?</P>
                    </div>
                </div>
                <div class="CHATING_SENDER CHATING_RECEIVEr">
                    <div class="SEND_SMS_VIEW">
                        <P>Hello</P>
                    </div>
                    <div class="SMS_thumb">
                        <img src="img/staf/1.png" alt>
                    </div>
                </div>
            </div>
            <div class="CHAT_POPUP_BOTTOM">
                <div class="chat_input_box d-flex align-items-center">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Write your message" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn " type="button">

                                <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M18.7821 3.21895C14.4908 -1.07281 7.50882 -1.07281 3.2183 3.21792C-1.07304 7.50864 -1.07263 14.4908 3.21872 18.7824C7.50882 23.0729 14.4908 23.0729 18.7817 18.7815C23.0726 14.4908 23.0724 7.50906 18.7821 3.21895ZM17.5813 17.5815C13.9525 21.2103 8.04773 21.2108 4.41871 17.5819C0.78907 13.9525 0.789485 8.04714 4.41871 4.41832C8.04752 0.789719 13.9521 0.789304 17.5817 4.41874C21.2105 8.04755 21.2101 13.9529 17.5813 17.5815ZM6.89503 8.02162C6.89503 7.31138 7.47107 6.73534 8.18131 6.73534C8.89135 6.73534 9.46739 7.31117 9.46739 8.02162C9.46739 8.73228 8.89135 9.30811 8.18131 9.30811C7.47107 9.30811 6.89503 8.73228 6.89503 8.02162ZM12.7274 8.02162C12.7274 7.31138 13.3038 6.73534 14.0141 6.73534C14.7241 6.73534 15.3002 7.31117 15.3002 8.02162C15.3002 8.73228 14.7243 9.30811 14.0141 9.30811C13.3038 9.30811 12.7274 8.73228 12.7274 8.02162ZM15.7683 13.2898C14.9712 15.1332 13.1043 16.3243 11.0126 16.3243C8.8758 16.3243 6.99792 15.1272 6.22834 13.2744C6.09642 12.9573 6.24681 12.593 6.56438 12.4611C6.64238 12.4289 6.72328 12.4136 6.80293 12.4136C7.04687 12.4136 7.27836 12.5577 7.37772 12.7973C7.95376 14.1842 9.38048 15.0799 11.0126 15.0799C12.6077 15.0799 14.0261 14.1836 14.626 12.7959C14.7625 12.4804 15.1288 12.335 15.4441 12.4717C15.7594 12.6084 15.9048 12.9745 15.7683 13.2898Z" fill="#707DB7" />
                                </svg>

                            </button>
                            <button class="btn" type="button">

                                <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M11 0.289062C4.92455 0.289062 0 5.08402 0 10.9996C0 16.9152 4.92455 21.7101 11 21.7101C17.0755 21.7101 22 16.9145 22 10.9996C22 5.08472 17.0755 0.289062 11 0.289062ZM11 20.3713C5.68423 20.3713 1.375 16.1755 1.375 10.9996C1.375 5.82371 5.68423 1.62788 11 1.62788C16.3158 1.62788 20.625 5.82371 20.625 10.9996C20.625 16.1755 16.3158 20.3713 11 20.3713ZM15.125 10.3302H11.6875V6.98314C11.6875 6.61363 11.3795 6.31373 11 6.31373C10.6205 6.31373 10.3125 6.61363 10.3125 6.98314V10.3302H6.875C6.4955 10.3302 6.1875 10.6301 6.1875 10.9996C6.1875 11.3691 6.4955 11.669 6.875 11.669H10.3125V15.016C10.3125 15.3855 10.6205 15.6854 11 15.6854C11.3795 15.6854 11.6875 15.3855 11.6875 15.016V11.669H15.125C15.5045 11.669 15.8125 11.3691 15.8125 10.9996C15.8125 10.6301 15.5045 10.3302 15.125 10.3302Z" fill="#707DB7" />
                                </svg>

                                <input type="file">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div id="back-top" style="display: none;">
            <a title="Go to Top" href="#">
                <i class="ti-angle-up"></i>
            </a>
        </div>



        <script src="js/jquery1-3.4.1.min.js"></script>

        <script src="js/popper1.min.js"></script>

        <script src="js/bootstrap.min.js"></script>

        <script src="js/metisMenu.js"></script>

        <script src="vendors/datatable/js/jquery.dataTables.min.js"></script>
        <script src="vendors/datatable/js/dataTables.responsive.min.js"></script>
        <script src="vendors/datatable/js/dataTables.buttons.min.js"></script>
        <script src="vendors/datatable/js/buttons.flash.min.js"></script>
        <script src="vendors/datatable/js/jszip.min.js"></script>
        <script src="vendors/datatable/js/pdfmake.min.js"></script>
        <script src="vendors/datatable/js/vfs_fonts.js"></script>
        <script src="vendors/datatable/js/buttons.html5.min.js"></script>
        <script src="vendors/datatable/js/buttons.print.min.js"></script>

        <script src="vendors/scroll/perfect-scrollbar.min.js"></script>
        <script src="vendors/scroll/scrollable-custom.js"></script>
        <!--kiem tra validation-->
        <script>
            document.getElementById('accountForm').addEventListener('submit', function (event) {
                var fullName = document.getElementById('fullName').value.trim();
                var userName = document.getElementById('userName').value.trim();
                var passWord = document.getElementById('passWord').value.trim();
                var confirmPassWord = document.getElementById('confirmPassWord').value.trim();
                var gender = document.querySelector('input[name="gender"]:checked');
                var email = document.getElementById('email').value.trim();
                var phoneNumber = document.getElementById('phoneNumber').value.trim();
                var address = document.getElementById('address').value.trim();
                var imgAccount = document.getElementById('imgAccount').files[0]; // Use files[0] to access the file input

                var fullNameError = document.getElementById('fullNameError');
                var userNameError = document.getElementById('userNameError');
                var passWordError = document.getElementById('passWordError');
                var confirmPassWordError = document.getElementById('confirmPassWordError');
                var genderError = document.getElementById('genderError');
                var emailError = document.getElementById('emailError');
                var phoneNumberError = document.getElementById('phoneNumberError');
                var addressError = document.getElementById('addressError');
                var imgAccountError = document.getElementById('imgAccountError');

                var isValid = true;

                const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.svg)$/i;

                // Reset previous error messages
                fullNameError.textContent = '';
                userNameError.textContent = '';
                passWordError.textContent = '';
                confirmPassWordError.textContent = '';
                genderError.textContent = '';
                emailError.textContent = '';
                phoneNumberError.textContent = '';
                addressError.textContent = '';
                imgAccountError.textContent = '';

                if (fullName === '') {
                    fullNameError.textContent = 'Vui lòng nhập họ và tên.';
                    isValid = false;
                }
                if (userName === '') {
                    userNameError.textContent = 'Vui lòng nhập tên tài khoản.';
                    isValid = false;
                }
                if (passWord === '') {
                    passWordError.textContent = 'Vui lòng nhập mật khẩu.';
                    isValid = false;
                } else if (!/^[a-zA-Z0-9]{8,}$/.test(passWord)) {
                    passWordError.textContent = 'Mật khẩu phải có ít nhất 8 ký tự và chỉ bao gồm chữ cái và số.';
                    isValid = false;
                }
                if (confirmPassWord === '') {
                    confirmPassWordError.textContent = 'Vui lòng nhập lại mật khẩu.';
                    isValid = false;
                } else if (passWord !== confirmPassWord) {
                    confirmPassWordError.textContent = 'Mật khẩu không khớp.';
                    isValid = false;
                }
                if (!gender) {
                    genderError.textContent = 'Vui lòng chọn giới tính.';
                    isValid = false;
                }
                if (email === '') {
                    emailError.textContent = 'Vui lòng nhập email.';
                    isValid = false;
                } else if (!/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/.test(email)) {
                    emailError.textContent = 'Email không đúng định dạng.';
                    isValid = false;
                }
                if (phoneNumber === '') {
                    phoneNumberError.textContent = 'Vui lòng nhập số điện thoại.';
                    isValid = false;
                } else if (!/^0\d{9}$/.test(phoneNumber)) {
                    phoneNumberError.textContent = 'Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0.';
                    isValid = false;
                }
                if (address === '') {
                    addressError.textContent = 'Vui lòng nhập địa chỉ.';
                    isValid = false;
                }
                if (!imgAccount) {
                    imgAccountError.textContent = 'Vui lòng chọn ảnh.';
                    isValid = false;
                } else if (!allowedExtensions.exec(imgAccount.value)) {
                    imgAccountError.textContent = 'Định dạng ảnh không hợp lệ. Chỉ hỗ trợ các định dạng JPG, JPEG, PNG, GIF, SVG.';
                    isValid = false;
                }

                if (!isValid) {
                    event.preventDefault();
                }
            });
        </script>
        <script>
            function showNotificationAndRedirect() {
                var notification = '<%= session.getAttribute("notification") %>';
                if (notification === 'success') {
                    var notificationContainer = document.getElementById('notification-container');
                    if (notificationContainer) {
                        var notificationElement = document.createElement('div');
                        notificationElement.classList.add('notification', 'success');
                        notificationElement.textContent = 'Thêm tài khoản thành công!';
                        notificationContainer.appendChild(notificationElement);
                        notificationContainer.style.display = 'block';
                        setTimeout(function () {
                            notificationContainer.style.display = 'none';
                            window.location.href = '<%= request.getContextPath() %>/manages';
                        }, 5000);
                    }
                } else if (notification === 'error') {
                    var errorMessage = '<%= session.getAttribute("errorMessage") %>';
                    var notificationContainer = document.getElementById('notification-container');
                    if (notificationContainer) {
                        var notificationElement = document.createElement('div');
                        notificationElement.classList.add('notification', 'error');
                        notificationElement.textContent = 'Lỗi: ' + errorMessage;
                        notificationContainer.appendChild(notificationElement);
                        notificationContainer.style.display = 'block';
                        setTimeout(function () {
                            notificationContainer.style.display = 'none';
                        }, 5000);
                    }
                }
            <% session.removeAttribute("notification"); %>
            <% session.removeAttribute("errorMessage"); %>
            }
            window.onload = showNotificationAndRedirect;
        </script>
    </body>
</html>

