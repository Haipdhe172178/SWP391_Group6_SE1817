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
        <title>Sales</title>


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
                border: 1px solid #ddd; /* Gray border */
                margin: 0 4px; /* Add some space between links */
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
        </style>
    </head>

    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

                <div id="notification-container" class="notification-container"></div>
               
            <div class="main_content_iner ">
                <div class="container-fluid p-0">
                    <div class="row justify-content-center">
                        <div class="col-lg-12">
                            <div class="white_card card_height_100 mb_30">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <!--<h3 class="m-0">Sản phẩm</h3>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <div class="QA_section">
                                        <div class="white_box_tittle list_header">
                                            <!--FILTER BY Status-->
                                                   <div class="cbo_filter">
                                                                                            <form action="staffdashboard" method="get" id="filterForm">
                                                                                                <label for="filter">Trạng thái:</label>
                                                                                                <select name="status" onchange="document.getElementById('filterForm').submit()">
                                                                                                    <option value="" ${status eq null ? 'selected': ''}>Tất cả</option>
                                                                                                    <option value="1" ${status eq '1' ? 'selected': ''}>Chờ xác nhận</option>
                                                                                                    <option value="2" ${status eq '2' ? 'selected': ''}>Đã xác nhận</option>
                                                                                                    <option value="3" ${status eq '3' ? 'selected': ''}>Chờ giao hàng</option>
                                                                                                    <option value="4" ${status eq '4' ? 'selected': ''}>Hoàn thành</option>
                                                                                                    <option value="5" ${status eq '5' ? 'selected': ''}>Đã hủy</option>
                                                                                                </select>
                                                                                                <input type="hidden" name="status" value="${requestScope.status}">
                                                                                                <input type="hidden" name="search" value="${requestScope.search}">
                                                                                            </form>
                                                                                        </div>
                                            <!--FILTER BY RATING-->

                                            <!--SEARCH BY PRODUCT NAME-->
                                            <!--                                            <div class="box_right d-flex lms_block">
                                                                                            <div class="serach_field_2">
                                                                                                <div class="search_inner">
                                                                                                    <form action="feedbacklist" method="GET">
                                                                                                        <input type="hidden" name="status" value="${requestScope.status}">
                                                                                                        <input type="hidden" name="filter" value="${requestScope.filter}">
                                                                                                        <div class="search_field">
                                                                                                            <input name="search" type="text" placeholder="Tìm kiếm...." value="${requestScope.searchResult}">
                                                                                                        </div>
                                                                                                        <button type="submit"> <img src="img/icon/icon_search.svg" alt> </button>
                                                                                                    </form>
                                                                                                </div>
                                                                                            </div>
                                                                                        </div>-->
                                        </div>
                                        <div class="QA_table mb_30">
                                            <!--                                      <div class="filterFeedback">
                                                                                            <a href="feedbacklist?page=1&status=pending&search=${requestScope.searchResult}&filter=${requestScope.filter}" class="filter  ${requestScope.status eq 'pending' ?'active':''}">Chờ duyệt (${requestScope.totalPending})</a>
                                                                                            <a href="feedbacklist?page=1&status=accept&search=${requestScope.searchResult}&filter=${requestScope.filter}" class="filter  ${requestScope.status eq 'accept' ?'active':''}" style="margin-left: 10px" >Đang hiển thị (${requestScope.totalAccept})</a>
                                                                                            <a href="feedbacklist?page=1&status=reject&search=${requestScope.searchResult}&filter=${requestScope.filter}" class="filter  ${requestScope.status eq 'reject' ?'active':''}" style="margin-left: 10px" >Bị ẩn (${requestScope.totalReject})</a>
                                                                                        </div>-->
                                            <a href="neworder">Tạo đơn hàng</a>
                                            <table class="table lms_table_active">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" style="width: 10px;padding: 5px">STT</th>
                                                        <th scope="col" style="width: 10px">Tên người đặt</th>
                                                        <th scope="col" style="width: 5px">Số điện thoại người Đặt</th>
                                                        <th scope="col" style="width: 250px">Tống số tiền</th>
                                                        <th scope="col" style="width: 200px">Ngày Đặt</th>
                                                        
                                                        <th scope="col">Trạng thái</th>
                                                         <th scope="col">Hoạt động</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <%
                                                   int count=1;
                                                    %>
                                                    <c:forEach items="${list}" var="l" varStatus="loop">
                                                        <tr>
                                                            <td>${l.stt}</td>
                                                            <td>${l.fullName}</td>
                                                            <td style="width: 100px">
                                                                ${l.phoneNumber}
                                                            </td>
                                                            <td>
                                                                
                                               
                                        <fmt:formatNumber value="${l.totalPrice} " type="currency" currencySymbol="" minFractionDigits="0" maxFractionDigits="0"/> VND
                                              
                                                            
                                                            
                                                            
                                                            </td>

                                                            <td>
                                                                ${l.date}
                                                            </td>
                                                            <td style="font-size: 12px">

                                                                  <c:choose>

                                                                    <c:when test="${l.status == 1}">
                                                                        <p style="color: yellow"> Chờ xác nhận</p>
                                                                    </c:when>
                                                                    <c:when test="${l.status == 2}">
                                                                         <p style="color: yellowgreen">Đã xác nhận</p>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 3}">
                                                                          <p style="color: greenyellow"> Chờ giao hàng</p>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 4}">
                                                                         <p style="color: green"> Hoàn thành</p>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 5}">
                                                                         <p style="color: red"> Đã hủy</p>
                                                                    </c:when>
                                                                </c:choose>
                                                           
                                                            </td>
                                                            


                                                            <td>
                                                               <c:choose>

                                                                    <c:when test="${l.status == 1}">
                                                                          <a style="color: yellowgreen" href="#" >Xem chi tiết/Cập Nhật</a>
                                                                    </c:when>
                                                                    <c:when test="${l.status == 2}">
                                                                         <a style="color: yellowgreen" href="#" >Xem chi tiết/Cập Nhật</a>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 3}">
                                                                         <a style="color: yellowgreen" href="#" >Xem chi tiết/Cập Nhật</a>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 4}">
                                                                             <a href="viewdetail?id=${l.getOrderID()}&acid=${l.accountID}" >Xem chi tiết</a>
                                                                    </c:when>
                                                                         <c:when test="${l.status == 5}">
                                                                       <a href="#" >Xem chi tiết</a>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>

                                        </div>
                                        <!--endPage,page,listFeedback-->
                               
                                                <form method="get" action="staffdashboard">
                                                    <nav class="pagination" aria-label="Page navigation">
                                                        <a href="staffdashboard?index=${tag - 1}" class="${tag == 1 ? 'disabled' : ''}">Trước</a>
                                                        <c:forEach begin="1" end="${endP}" var="i">
                                                            <a href="staffdashboard?index=${i}" class="${tag == i ? 'active' : ''}">${i}</a>
                                                        </c:forEach>
                                                        <a href="staffdashboard?index=${tag + 1}" class="${tag == endP ? 'disabled' : ''}">Sau</a>
                                                    </nav>
                                                </form>  
                                           
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
