<%-- 
    Document   : Dashboard
    Created on : May 27, 2024, 10:05:51 AM
    Author     : huyca
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Sales Dashboard</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
        <style>
            .view-all-btn {
                display: flex;
                justify-content: flex-end;
                padding-top: 10px;
            }

        </style>
    </head>

    <body class="crm_body_bg">

        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

                <div class="main_content_iner overly_inner">
                    <div class="container-fluid p-0">

                        <div class="row">
                            <div class="col-lg-4 card_height_100 mb_20">
                                <div class="white_card card_height_100 mb_20">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Số Lượng tài khoản khách hàng</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="white_card_body">
                                        <h2 class="crm_number" id="totalAccounts">${acc.size()}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 card_height_100 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Tổng số đơn hàng</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <h2 class="crm_number" id="totalOrders">${totalQuantity}</h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4 card_height_100 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Doanh thu</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <h2 class="crm_number" id="revenue">
                                        <fmt:formatNumber type="currency" currencySymbol="VND" value="${totalRevenue}"/>
                                    </h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-12 mb_20">
                        <div class="white_card card_height_100 mb_20">
                            <div class="white_card_header">
                                <div class="box_header m-0">
                                    <div class="main-title">
                                        <h3 class="m-0">Tỷ lệ trạng thái đơn hàng</h3>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table">
                                        <table class="table lms_table_active2 p-0">
                                            <thead>
                                                <tr>
                                                    <th>Trạng thái</th>
                                                    <th>Phần trăm</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="entry" items="${orderStatusPercentage}">
                                                    <tr>
                                                        <td>${entry.key}</td>
                                                        <td>
                                                            <div class="progress" style="height: 20px;">
                                                                <div class="progress-bar" role="progressbar" style="width: ${entry.value}%;" aria-valuenow="${entry.value}" aria-valuemin="0" aria-valuemax="100">${entry.value}%</div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                        <div class="col-lg-12 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Đơn hàng gần đây (Customer)</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table">
                                        <table class="table lms_table_active2 p-0">
                                            <thead>
                                                <tr>
                                                    <th>ID đơn hàng</th>
                                                    <th>Tên</th>
                                                    <th>Tổng giá</th>
                                                    <th>Ngày</th>
                                                    <th>Trạng Thái</th>
                                                    <th>Hành Động</th>
                                                </tr>
                                            </thead>
                                            <tbody id="recentOrders">
                                                <c:forEach var="order" items="${recentCustomerOrders}">
                                                    <tr>
                                                        <td>${order.orderDetails[0].orderCId}</td>
                                                        <td>${order.account.fullName}</td>
                                                        <td>${order.totalPrice}</td>
                                                        <td>${order.date}</td>
                                                        <td>${order.status.statusName}</td>
                                                        <td>
                                                            <a href="#" title="View"><i class="fas fa-eye"></i></a>                                                                
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="view-all-btn">
                                            <a href="" class="btn_2">Xem tất cả</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-12 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Đơn hàng gần đây (Guest)</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table">
                                        <table class="table lms_table_active2 p-0">
                                            <thead>
                                                <tr>
                                                    <th>ID đơn hàng</th>
                                                    <th>Tên khách hàng</th>
                                                    <th>Email</th>
                                                    <th>Số điện thoại</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Tổng giá</th>
                                                    <th>Ngày</th>
                                                    <th>Trạng Thái</th>
                                                    <th>Hành Động</th>
                                                </tr>
                                            </thead>
                                            <tbody id="recentGuestOrders">
                                                <c:forEach var="order" items="${recentGuestOrders}">
                                                    <tr>
                                                        <td>${order.orderDetails[0].orderGId}</td>
                                                        <td>${order.fullName}</td>
                                                        <td>${order.email}</td>
                                                        <td>${order.phoneNumber}</td>
                                                        <td>${order.address}</td>
                                                        <td>${order.totalPrice}</td>
                                                        <td>${order.date}</td>
                                                        <td>${order.status.statusName}</td>
                                                        <td>
                                                            <a href="#" title="View"><i class="fas fa-eye"></i></a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <div class="view-all-btn">
                                            <a href="" class="btn_2">Xem tất cả</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>               
                    <div class="row">
                        <div class="col-lg-5 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Sản phẩm bán chạy</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table">
                                        <table class="table lms_table_active2 p-0">
                                            <thead>
                                                <tr>
                                                    <th scope="col">Sản phẩm</th>
                                                    <th scope="col">Giá</th>                                                   
                                                    <th scope="col">Đã Bán</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${mostPurchasedProducts}" var="p">
                                                    <tr>
                                                        <td>
                                                            <div class="customer d-flex align-items-center">
                                                                <div class="thumb_34 mr_15 mt-0"><img class="img-fluid" src="${p.imgProduct}" alt></div>
                                                                <span class="f_s_14 f_w_400 color_text_1">${p.name}</span>
                                                            </div>
                                                        </td>
                                                        <td class="f_s_14 f_w_400 color_text_2">${p.price}</td>                                                 
                                                        <td class="f_s_14 f_w_400 color_text_4">${p.quantity}</td>                            
                                                    </tr>  
                                                </c:forEach>
                                            </tbody>

                                        </table>                                       
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-lg-7 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Top Người Mua Hàng Nhiều Nhất</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table scrollable-table">
                                        <table class="table lms_table_active2 p-0">
                                            <thead>
                                                <tr>
                                                    <th>Tên</th>
                                                    <th>Email</th>
                                                    <th>Số điện thoại</th>
                                                    <th>Địa chỉ</th>
                                                    <th>Tổng Chi Tiêu</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="buyer" items="${topBuyers}">
                                                    <tr>
                                                        <td>${buyer.fullName}</td>
                                                        <td>${buyer.email}</td>
                                                        <td>${buyer.phoneNumber}</td>
                                                        <td>${buyer.address}</td>
                                                        <td>${buyer.totalSpent}</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="footer_part">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="footer_iner text-center">
                                    <p>2024 © Influence - Designed by <a href="#"> <i class="ti-heart"></i> </a><a href="#"> Dashboard</a></p>
                                </div>
                            </div>
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

