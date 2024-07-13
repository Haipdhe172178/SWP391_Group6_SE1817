<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>New Order</title>
        <link rel="stylesheet" href="css/bootstrap1.min.css" />
        <link rel="stylesheet" href="vendors/themefy_icon/themify-icons.css" />
        <link rel="stylesheet" href="vendors/niceselect/css/nice-select.css" />
        <link rel="stylesheet" href="vendors/owl_carousel/css/owl.carousel.css" />
        <link rel="stylesheet" href="vendors/gijgo/gijgo.min.css" />
        <link rel="stylesheet" href="vendors/font_awesome/css/all.min.css" />
        <link rel="stylesheet" href="vendors/tagsinput/tagsinput.css" />
        <link rel="stylesheet" href="vendors/datepicker/date-picker.css" />
        <link rel="stylesheet" href="vendors/vectormap-home/vectormap-2.0.2.css" />
        <link rel="stylesheet" href="vendors/scroll/scrollable.css" />
        <link rel="stylesheet" href="vendors/datatable/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/responsive.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/buttons.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/text_editor/summernote-bs4.css" />
        <link rel="stylesheet" href="vendors/morris/morris.css">
        <link rel="stylesheet" href="vendors/material_icon/material-icons.css" />
        <link rel="stylesheet" href="css/metisMenu.css">
        <link rel="stylesheet" href="css/style1.css" />
        <link rel="stylesheet" href="css/colors/default.css" id="colorSkinCSS">
        <style>
            .pagination a {
                color: #000;
                float: left;
                padding: 8px 16px;
                text-decoration: none;
                transition: background-color .3s;
                border: 1px solid #ddd;
                margin: 0 4px;
            }
            .pagination a.active {
                background-color: #4B0082;
                color: white;
                border: 1px solid #4B0082;
            }
            .pagination a:hover:not(.active) {
                background-color: #ddd;
            }
            .pagination a.disabled {
                pointer-events: none;
                color: #ccc;
                border-color: #ddd;
            }
            .table img {
                display: block;
                margin-left: auto;
                margin-right: auto;
            }
            .form-group label {
                font-weight: bold;
            }
        </style>
    </head>
    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>
            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                <div id="notification-container" class="notification-container"></div>
                <div id="back-top" style="display: none;">
                    <a title="Go to Top" href="#">
                        <i class="ti-angle-up"></i>
                    </a>
                </div>

                <div class="container mt-5">
                    <div class="card">
                        <div class="card-header">
                            <h3>Tạo Đơn hàng</h3>
                        </div>
                        <form action="neworder" method="post" onsubmit="return validateForm()">
                            <div class="card-body">
                                <h5>Sản phẩm đã chọn</h5>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Tên sản phẩm</th>
                                            <th>Ảnh Bìa Sách</th>
                                            <th>Giá tiền</th>
                                            <th>Số lượng</th>
                                            <th>Thành tiền</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${listsp}" var="ls" varStatus="status">
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${ls.product.name}</td>
                                            <td><img src="${ls.product.imgProduct}" alt="Product 1" width="50"></td>
                                            <td class="price">${ls.product.price} <input name="price${ls.product.productId}" value="${ls.product.price}" hidden/></td>
                                            <td>
                                                <input type="number" name="quantity${ls.product.productId}" class="quantity" value="1" min="0" max="${ls.product.quantity}" onchange="updateTotal(${ls.product.quantity})"/>
                                            </td>
                                            <td class="total-price"></td>
                                            <td>
<!--                                                <form action="remove" method="post">
                                                    <input type="hidden" name="id" value="${ls.product.productId}"/>
                                                    <input type="submit" value="Xóa"/>
                                                </form>-->
                                                    <a href="remove?id=${ls.product.productId}">Xoá</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <button class="btn btn-primary">
                                <a href="listsp" class="text-white">Chọn thêm sản phẩm</a>
                            </button>

                            <div class="mt-3">
                                <p>Tổng tiền sản phẩm: <span id="subtotal">0</span>đ</p>
                                <p>Phí vận chuyển: <span id="shipping-fee">20000</span>đ</p>
                                <p>Tổng: <span id="total" name="total" >0</span>đ</p>
                                <input id="total1" name="total" hidden=""/>
                            </div>

                            <script>
                                function updateTotal(quantityMax) {
                                    let subtotal = 0;
                                    document.querySelectorAll('tbody tr').forEach(row => {

                                        const price = parseFloat(row.querySelector('.price').textContent);
                                        const quantity = parseInt(row.querySelector('.quantity').value);
                                        
                                        if (quantity > quantityMax) {
                                            quantity = quantityMax;
                                            row.querySelector('.quantity').value = quantityMax;
                                            alert('Số lượng không được vượt quá ' + quantityMax);
                                        }
                                        
                                        if (quantity <= 0) {
                                            row.remove();
                                        } else {
                                            const totalPrice = price * quantity;
                                            row.querySelector('.total-price').textContent = totalPrice.toFixed(2);
                                            subtotal += totalPrice;
                                        }
                                    });

                                    const shippingFee = parseFloat(document.getElementById('shipping-fee').textContent);
                                    const total = subtotal + shippingFee;

                                    document.getElementById('subtotal').textContent = subtotal.toFixed(2);
                                    document.getElementById('total').textContent = total.toFixed(2);

                                    document.getElementById('total1').value = total;
                                }

                                document.addEventListener('DOMContentLoaded', updateTotal);
                            </script>

                            <h5>Chi tiết giao hàng</h5>

                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="fullName">Tên người nhận*</label>
                                    <input type="text" name="name" class="form-control" id="fullName" placeholder="Tên người nhận" required>
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="mobile">Số điện thoại*</label>
                                    <input type="text" name="phone" class="form-control" id="mobile" placeholder="Số điện thoại" required pattern="^\d{10,11}$">
                                </div>
                                <div class="form-group col-md-6">
                                    <label for="email">Email*</label>
                                    <input type="email" name="email" class="form-control" id="email" placeholder="Email" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="address">Địa Chỉ người nhận</label>
                                <input type="text" name="address" class="form-control" id="address" placeholder="Địa Chỉ người nhận">
                            </div>

                            <h5>Thanh toán</h5>
                            <div class="form-check">
                                <select name="payment" class="form-control">
                                    <option value="0">Chưa trả tiền</option>
                                    <option value="1">Đã trả tiền</option>
                                </select>
                            </div>

                            <h5>Order Status</h5>
                            <div class="form-check">
                                <select name="status" class="form-control">
                                    <option value="1">Chờ xác nhận</option>
                                    
                                    
                                </select>
                            </div>

                            <button type="submit" class="btn btn-primary mt-3">Submit</button>

                        </div>
                    </form>
                        <div><h2 style="color: red">${error}</h2></div>
                </div>
            </div>
            <script>
                function validateForm() {
                    var fullName = document.getElementById('fullName').value.trim();
                    var email = document.getElementById('email').value.trim();
                    var mobile = document.getElementById('mobile').value.trim();
                    var address = document.getElementById('address').value.trim();

                    if (fullName === '') {
                        alert('Vui lòng nhập Tên người nhận.');
                        return false;
                    }

                    // Check if there are only whitespaces in fullName
                    if (/^\s*$/.test(fullName)) {
                        alert('Tên người nhận không được chỉ gồm dấu cách.');
                        return false;
                    }

                    if (email === '') {
                        alert('Vui lòng nhập Email.');
                        return false;
                    }

                    // Regular expression for email validation
                    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                    if (!emailRegex.test(email)) {
                        alert('Email không hợp lệ. Vui lòng nhập lại.');
                        return false;
                    }

                    if (mobile === '') {
                        alert('Vui lòng nhập Số điện thoại.');
                        return false;
                    }

                    // Regular expression for Vietnamese phone numbers (including landline)
                    var mobileRegex = /(84|0[3|5|7|8|9])+([0-9]{8})\b/;
                    if (!mobileRegex.test(mobile)) {
                        alert('Số điện thoại không hợp lệ. Vui lòng nhập lại.');
                        return false;
                    }

                    if (address === '') {
                        alert('Vui lòng nhập Địa chỉ người nhận.');
                        return false;
                    }

                    // If all validations pass, return true to submit the form
                    return true;
                }
            </script>

            <script src="js/jquery1-3.4.1.min.js"></script>
            <script src="js/popper1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/metisMenu.js"></script>
            <script src="vendors/count_up/jquery.waypoints.min.js"></script>
            <script src="vendors/chartlist/Chart.min.js"></script>
            <script src="vendors/count_up/jquery.counterup.min.js"></script>
            <script src="vendors/niceselect/js/jquery.nice-select.min.js"></script>
            <script src="vendors/owl_carousel/js/owl.carousel.min.js"></script>
            <script src="vendors/datatable/js/jquery.dataTables.min.js"></script>
            <script src="vendors/datatable/js/dataTables.responsive.min.js"></script>
            <script src="vendors/datatable/js/dataTables.buttons.min.js"></script>
            <script src="vendors/datatable/js/buttons.flash.min.js"></script>
            <script src="vendors/datatable/js/jszip.min.js"></script>
            <script src="vendors/datatable/js/pdfmake.min.js"></script>
            <script src="vendors/datatable/js/vfs_fonts.js"></script>
            <script src="vendors/datatable/js/buttons.html5.min.js"></script>
            <script src="vendors/datatable/js/buttons.print.min.js"></script>
            <script src="vendors/datepicker/datepicker.js"></script>
            <script src="vendors/datepicker/datepicker.en.js"></script>
            <script src="vendors/datepicker/datepicker.custom.js"></script>
            <script src="js/chart.min.js"></script>
            <script src="vendors/chartjs/roundedBar.min.js"></script>
            <script src="vendors/progressbar/jquery.barfiller.js"></script>
            <script src="vendors/tagsinput/tagsinput.js"></script>
            <script src="vendors/text_editor/summernote-bs4.js"></script>
            <script src="vendors/am_chart/amcharts.js"></script>
            <script src="vendors/scroll/perfect-scrollbar.min.js"></script>
            <script src="vendors/scroll/scrollable-custom.js"></script>
            <script src="vendors/vectormap-home/vectormap-2.0.2.min.js"></script>
            <script src="vendors/vectormap-home/vectormap-world-mill-en.js"></script>
            <script src="vendors/apex_chart/apex-chart2.js"></script>
            <script src="vendors/apex_chart/apex_dashboard.js"></script>
            <script src="vendors/echart/echarts.min.js"></script>
            <script src="vendors/chart_am/core.js"></script>
            <script src="vendors/chart_am/charts.js"></script>
            <script src="vendors/chart_am/animated.js"></script>
            <script src="vendors/chart_am/kelly.js"></script>
            <script src="vendors/chart_am/chart-custom.js"></script>
            <script src="js/dashboard_init.js"></script>
            <script src="js/custom.js"></script>
    </body>
</html>
