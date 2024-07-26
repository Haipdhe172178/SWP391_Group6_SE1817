<%-- 
    Document   : 
    Created on : June 30, 2024
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
        <title>Order Status</title>
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

    </head>

    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>
            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                <div class="main_content_iner overly_inner">
                    <div class="container-fluid p-0">
                        <div class="row">
                            <div class="col-lg-12 mb_20">
                                <div class="white_card card_height_100 mb_20">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Đơn hàng trạng thái: ${categoryName}</h3>
                                        </div>
                                    </div>
                                </div>

                                <div class="white_card_body QA_section">
                                    <div class="serach_field_2">
                                        <div class="search_inner">
                                            <form action="catep" method="GET">
                                                <div class="search_field">
                                                    <input name="s" type="text" placeholder="Tìm kiếm....">
                                                    <input name="categoryId" type="hidden" value="${categoryId}">
                                                </div>
                                                <button type="submit">
                                                    <img src="img/icon/icon_search.svg" alt="Search Icon">
                                                </button>
                                            </form>
                                        </div>

                                    </div>

                                    <div class="QA_table table-container scrollable-table">
                                        <table class="table lms_table_active2 p-0" id="orderTable">
                                            <thead>
                                                <tr>
                                                    <th>ID </th>
                                                    <th>Tên </th>
                                                    <th>Lượt mua
                                                        <a href="catep?categoryId=${categoryId}&sort=priceasc"><i class="fas fa-arrow-up"></i></a>
                                                        <a href="catep?categoryId=${categoryId}&sort=pricedesc"><i class="fas fa-arrow-down"></i></a>
                                                    </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="product" items="${ListA}">
                                                    <tr>
                                                        <td>${product.productId}</td>
                                                        <td>${product.name}</td>
                                                        <td>${product.totalQuantity}</td>                                                 
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <nav class="py-5" aria-label="Page navigation">
                                            <ul class="pagination justify-content-center gap-4">
                                                <!-- Xác định phạm vi các trang hiển thị -->
                                                <c:set var="start" value="${tag > 3 ? tag - 2 : 1}" />
                                                <c:set var="end" value="${tag > 3 ? tag + 2 : 5}" />
                                                <c:if test="${end > endP}">
                                                    <c:set var="end" value="${endP}" />
                                                    <c:set var="start" value="${endP - 4 > 0 ? endP - 4 : 1}" />
                                                </c:if>

                                                <!-- Nút Previous -->
                                                <c:if test="${tag > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="catep?categoryId=${categoryId}&index=${tag - 1}${query}" aria-label="Previous">
                                                            <span aria-hidden="true"><i class="fas fa-arrow-left"></i></span>
                                                        </a>
                                                    </li>
                                                </c:if>

                                                <!-- Vòng lặp để tạo các nút trang -->
                                                <c:forEach begin="${start}" end="${end}" var="i">
                                                    <li class="page-item ${tag == i ? 'active' : ''}">
                                                        <a class="page-link" href="catep?categoryId=${categoryId}&index=${i}${query}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <!-- Nút Next -->
                                                <c:if test="${tag < endP}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="catep?categoryId=${categoryId}&index=${tag + 1}${query}" aria-label="Next">
                                                            <span aria-hidden="true"><i class="fas fa-arrow-right"></i></span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                            <div>
                                                <a href="dash" class="btn btn-warning">Trở lại</a>
                                            </div>
                                        </nav>
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
