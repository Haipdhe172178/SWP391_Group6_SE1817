<%-- 
    Document   : Dashboard
    Created on : May 27, 2024, 10:05:51 AM
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
    </head>

    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                <div class="container-fluid g-0">
                    <div class="row">
                        <div class="col-lg-12 p-0">
                            <div class="header_iner d-flex justify-content-between align-items-center">
                                <div class="sidebar_icon d-lg-none">
                                    <i class="ti-menu"></i>
                                </div>
                                <div class="serach_field-area d-flex align-items-center">
                                    <div class="search_inner">
                                        <!--                            <form action="#">
                                                                        <div class="search_field">
                                                                            <input type="text" placeholder="Search here...">
                                                                        </div>
                                                                        <button type="submit"> <img src="img/icon/icon_search.svg" alt> </button>
                                                                    </form>-->
                                        <h3 style="margin-left: 20px"></h3>
                                    </div>
                                    <span class="f_s_14 f_w_400 ml_25 white_text text_white">Apps</span>
                                </div>
                                <div class="header_right d-flex justify-content-between align-items-center">
                                    <div class="profile_info">
                                        <img src="${sessionScope.account.imgAccount}">
                                    <div class="profile_info_iner">
                                        <div class="profile_author_name">
                                            <p>Nhân viên</p>
                                            <h5>${sessionScope.account.fullName}</h5>
                                        </div>
                                        <div class="profile_info_details">
                                            <a href="logout">Đăng xuất </a>
                                        </div>
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
