<%-- 
    Document   : AddAuthor.jsp
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
        <title> Thêm Tác Giả</title>
        <style>
            .error {
                color: red;
                font-size: 0.8em;
            }
            /* CSS for notification */
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

            @keyframes slideIn {
                from {
                    transform: translateX(100%);
                }
                to {
                    transform: translateX(0);
                }
            }

            @keyframes slideOut {
                from {
                    transform: translateX(0);
                }
                to {
                    transform: translateX(100%);
                }
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
                                                <h3 class="m-0">Trả lời liên hệ cho người dùng</h3>
                                            </div>
                                        </div>
                                    </div>


                                    <form action="reply" id="myForm" method="get">
                                        <div class="white_card_body">
                                            <div class="mb-3">
                                                <label for="emailTo">Gửi tới Email: </label>
                                                <input type="email" class="form-control" id="emailTo" name="to" placeholder="Nhập địa chỉ Gmail" required>
                                                <div id="emailToError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="subject">Chủ đề</label>
                                                <input type="text" class="form-control" id="subject" name="subject" placeholder="Nhập chủ đề" required>
                                                <div id="subjectError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="message">Lời nhắn</label>
                                                <textarea class="form-control" id="message" name="message" placeholder="Nhập lời nhắn" required></textarea>
                                                <div id="messageError" class="error"></div>
                                            </div>
                                            <div>
                                                <button type="submit" class="btn btn-primary">Gửi</button>
                                                <a href="contactAdmin" class="btn btn-warning">Trở lại</a>
                                            </div>
                                        </div>
                                    </form>

                                <c:if test="${not empty message}">
                                    <div class="alert ${message.contains('success') ? 'alert-success' : 'alert-success'}">
                                        ${message}
                                    </div>
                                </c:if>

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
            // JavaScript for form validation
            document.getElementById('myForm').addEventListener('submit', function (event) {
                var authorName = document.getElementById('authorName').value.trim();
                var authorDescription = document.getElementById('authortDescription').value.trim();
                var authorNameError = document.getElementById('authorNameError');
                var authorDescriptionError = document.getElementById('authorDescriptionError');
                var isValid = true;
                // Reset previous error messages
                authorNameError.textContent = '';
                authorDescriptionError.textContent = '';
                if (authorName === '') {
                    authorNameError.textContent = 'Vui lòng nhập tên tác giả.';
                    isValid = false;
                }
                if (authorDescription === '') {
                    authorDescriptionError.textContent = 'Vui lòng nhập mô tả sản phẩm.';
                    isValid = false;
                }

                if (!isValid) {
                    event.preventDefault();
                }
            });
        </script>
        <!--hien thi thong bao-->
        <script>
            function showNotificationAndRedirect() {
                var notification = '<%= session.getAttribute("notification") %>';
                if (notification === 'success') {
                    var notificationContainer = document.getElementById('notification-container');
                    if (notificationContainer) {
                        var notificationElement = document.createElement('div');
                        notificationElement.classList.add('notification', 'success');
                        notificationElement.textContent = 'Thêm tác giả thành công!';
                        notificationContainer.appendChild(notificationElement);
                        notificationContainer.style.display = 'block';
                        setTimeout(function () {
                            notificationContainer.style.display = 'none';
                            window.location.href = '<%= request.getContextPath() %>/author';
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

<li><a href="data">Sản phẩm</a></li>
<li><a href="">Thể Loại</a></li>
<li><a href="author">Tác Giả</a></li>

