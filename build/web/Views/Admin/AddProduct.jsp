<%-- 
    Document   : DataTable
    Created on : May 27, 2024, 10:09:46 AM
    Author     : huyca
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title> Thêm Sản Phẩm</title>
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
            .suggestions {
                border: 1px solid #ddd;
                max-height: 200px;
                overflow-y: auto;
                position: absolute;
                z-index: 1000;
                background-color: #fff;
            }
            .suggestion-item {
                padding: 8px;
                cursor: pointer;
            }
            .suggestion-item:hover {
                background-color: #f0f0f0;
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
                                                <h3 class="m-0">Thêm sản phẩm</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <form action="add" method="POST" enctype="multipart/form-data" id="myForm">
                                        <div class="white_card_body">
                                            <div class="mb-3">
                                                <label for="productName">Tên sản phẩm:</label>
                                                <input type="text" class="form-control" id="productName" name="name" placeholder="nhập tên sản phẩm" required>
                                                <div id="productNameError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="productPrice">Giá:</label>
                                                <input type="number" class="form-control" id="productPrice" placeholder="nhập giá" name="price" required>
                                                <div id="productPriceError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="productQuantity">Số lượng:</label>
                                                <input type="number" class="form-control" id="productQuantity" placeholder="nhập số lượng" name="quantity" required>
                                                <div id="productQuantityError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="productDescription">Mô tả sản phẩm:</label>
                                                <textarea class="form-control" id="productDescription" name="description" placeholder="nhập mô tả" required></textarea>
                                                <div id="productDescriptionError" class="error"></div>
                                            </div>
                                            <div class="mb-3">
                                                <label for="productCategory" class="form-label">Thể loại</label>
                                                <select class="form-select" id="productCategory" name="categoryId" required>
                                                <c:forEach items="${category}" var="c">
                                                    <option value="${c.categoryId}">${c.categoryName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="productAuthor" class="form-label">Tác giả</label>
                                            <input type="text" class="form-control" id="authorSearch" placeholder="Nhập tên tác giả để tìm kiếm" oninput="filterAuthors() " style="width: 200px;">
                                            <div id="suggestions" class="suggestions"></div>
                                            <select class="form-select" id="productAuthor" name="author" required>
                                                <c:forEach items="${author}" var="au">
                                                    <option value="${au.authorID}">${au.authorName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="productAgeSelect" class="form-label">Độ tuổi</label>
                                            <select class="form-select" id="productAgeSelect" name="ageId" required>
                                                <c:forEach items="${obage}" var="o">
                                                    <option value="${o.ageId}">${o.age}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="mb-3">
                                            <label for="productImage" class="form-label">Ảnh</label>
                                            <input type="file" class="form-control" id="productImage" name="imgProduct" required>
                                             <div id="productImageError" class="error"></div>
                                        </div>
                                        <div>
                                            <button type="submit" class="btn btn-primary">Thêm Sản Phẩm</button>
                                            <a href="data" class="btn btn-warning">Trở lại</a>
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
            // JavaScript for form validation
            document.getElementById('myForm').addEventListener('submit', function (event) {
                var productName = document.getElementById('productName').value.trim();
                var productPrice = document.getElementById('productPrice').value.trim();
                var productQuantity = document.getElementById('productQuantity').value.trim();
                var productDescription = document.getElementById('productDescription').value.trim();
                var productImage = document.getElementById('productImage').value.trim();

                var productNameError = document.getElementById('productNameError');
                var productPriceError = document.getElementById('productPriceError');
                var productQuantityError = document.getElementById('productQuantityError');
                var productDescriptionError = document.getElementById('productDescriptionError');
                const productImageError = document.getElementById('productImageError');

                var isValid = true;

                const allowedExtensions = /(\.jpg|\.jpeg|\.png|\.gif|\.svg)$/i;

                // Reset previous error messages
                productNameError.textContent = '';
                productPriceError.textContent = '';
                productQuantityError.textContent = '';
                productDescriptionError.textContent = '';

                if (productName === '') {
                    productNameError.textContent = 'Vui lòng nhập tên sản phẩm.';
                    isValid = false;
                }
                if (productPrice === '') {
                    productPriceError.textContent = 'Vui lòng nhập giá sản phẩm.';
                    isValid = false;
                } else if (isNaN(productPrice) || parseFloat(productPrice) <= 0) {
                    productPriceError.textContent = 'Giá sản phẩm phải là một số dương.';
                    isValid = false;
                }

                if (productQuantity === '') {
                    productQuantityError.textContent = 'Vui lòng nhập số lượng sản phẩm.';
                    isValid = false;
                } else if (isNaN(productQuantity) || parseInt(productQuantity) <= 0) {
                    productQuantityError.textContent = 'Số lượng sản phẩm phải là một số nguyên dương.';
                    isValid = false;
                }

                if (productDescription === '') {
                    productDescriptionError.textContent = 'Vui lòng nhập mô tả sản phẩm.';
                    isValid = false;
                }
                if (productImage === '') {
                    productImageError.textContent = 'Vui lòng chọn một ảnh cho sản phẩm.';
                    isValid = false;
                } else if (!allowedExtensions.exec(productImage)) {
                    productImageError.textContent = 'Định dạng ảnh không hợp lệ. Chỉ hỗ trợ các định dạng JPG, JPEG, PNG, GIF, SVG.';
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
                        notificationElement.textContent = 'Thêm Sản Phẩm thành công!';
                        notificationContainer.appendChild(notificationElement);
                        notificationContainer.style.display = 'block';
                        setTimeout(function () {
                            notificationContainer.style.display = 'none';
                            window.location.href = '<%= request.getContextPath() %>/data';
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
        <script>
            function filterAuthors() {
                const searchValue = document.getElementById('authorSearch').value.toLowerCase();
                const selectElement = document.getElementById('productAuthor');
                const options = selectElement.options;
                const suggestionBox = document.getElementById('suggestions');
                suggestionBox.innerHTML = ''; // Clear previous suggestions

                if (searchValue.length === 0) {
                    suggestionBox.style.display = 'none'; // Hide suggestions if input is empty
                    return;
                }

                for (let i = 0; i < options.length; i++) {
                    const option = options[i];
                    const authorName = option.text.toLowerCase().trim();
                    if (authorName.includes(searchValue)) {
                        const suggestionItem = document.createElement('div');
                        suggestionItem.className = 'suggestion-item';
                        suggestionItem.textContent = option.text;
                        suggestionItem.onclick = () => {
                            document.getElementById('authorSearch').value = option.text;
                            selectElement.value = option.value;
                            suggestionBox.innerHTML = '';
                            suggestionBox.style.display = 'none';
                        };
                        suggestionBox.appendChild(suggestionItem);
                    }
                }

                if (suggestionBox.hasChildNodes()) {
                    suggestionBox.style.display = 'block';
                } else {
                    suggestionBox.style.display = 'none';
                }
            }


            document.addEventListener('click', function (event) {
                if (!event.target.matches('#authorSearch')) {
                    document.getElementById('suggestions').style.display = 'none';
                }
            });
        </script>

    </body>

</html>
