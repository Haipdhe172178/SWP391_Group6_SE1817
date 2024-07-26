<%-- 
    Document   : Statistics
    Created on : June 30, 2024
    Author     : huyca
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="zxx">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Statistics</title>
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

            .year-form {
                margin-bottom: 20px;
                padding: 10px;
                background-color: #f7f7f7;
                border: 1px solid #ddd;
                border-radius: 5px;
            }

            .year-form label {
                margin-right: 10px;
                font-weight: bold;
            }

            .year-form select {
                padding: 5px;
                font-size: 14px;
            }
        </style>
    </head>
    <body class="crm_body_bg">

        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>
            <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>
                <div class="main_content_iner overly_inner">
                    <div class="row mb-30">
                        <div class="row">

                            <div class="row">
                                <div class="col-lg-3 mb-30">
                                    <form id="yearForm1" class="year-form" method="get" action="statisticsorder" onsubmit="return validateDateRange()">
                                        <div class="form-group">
                                            <label for="year1">Chọn năm:</label>
                                            <select id="year1" name="year" onchange="document.getElementById('yearForm1').submit()" class="form-control">
                                                <option value="2023" <%= request.getAttribute("selectedYear") != null && request.getAttribute("selectedYear").equals(2023) ? "selected" : "" %>>2023</option>
                                            <option value="2024" <%= request.getAttribute("selectedYear") != null && request.getAttribute("selectedYear").equals(2024) ? "selected" : "" %>>2024</option>
                                            <option value="2025" <%= request.getAttribute("selectedYear") != null && request.getAttribute("selectedYear").equals(2025) ? "selected" : "" %>>2025</option>
                                        </select>
                                    </div>
                                </form>
                            </div>
                            <div class="col-lg-9">
                                <div class="row">
                                    <div class="col-lg-6 mb-30">
                                        <div class="white_card card_height_100 mb-30">
                                            <div class="white_card_header">
                                                <div class="box_header m-0">
                                                    <div class="main-title">
                                                        <h3 class="m-0">Biểu đồ doanh thu theo tháng</h3>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="white_card_body">
                                                <div class="chart-container">
                                                    <canvas id="monthlyRevenueChart"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-6 mb-30">
                                        <div class="white_card card_height_100 mb-30">
                                            <div class="white_card_header">
                                                <div class="box_header m-0">
                                                    <div class="main-title">
                                                        <h3 class="m-0">Biểu đồ đơn hàng theo tháng</h3>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="white_card_body">
                                                <div class="chart-container">
                                                    <canvas id="revenueChart"></canvas>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>


        <script>
                                                function updateMonthlyCharts() {
                                                    var ctxMonth = document.getElementById('monthlyRevenueChart').getContext('2d');
                                                    var monthlyOrderData = <%= new Gson().toJson((int[]) request.getAttribute("monthlyRevenueData")) %>;
                                                    var revenueChart = new Chart(ctxMonth, {
                                                        type: 'pie',
                                                        data: {
                                                            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                                                            datasets: [{
                                                                    label: 'Tổng số doanh thu',
                                                                    data: monthlyOrderData,
                                                                    backgroundColor: [
                                                                        'rgba(255, 99, 132, 0.6)',
                                                                        'rgba(54, 162, 235, 0.6)',
                                                                        'rgba(255, 206, 86, 0.6)',
                                                                        'rgba(75, 192, 192, 0.6)',
                                                                        'rgba(153, 102, 255, 0.6)',
                                                                        'rgba(255, 159, 64, 0.6)',
                                                                        'rgba(199, 199, 199, 0.6)',
                                                                        'rgba(83, 102, 255, 0.6)',
                                                                        'rgba(255, 99, 132, 0.6)',
                                                                        'rgba(54, 162, 235, 0.6)',
                                                                        'rgba(255, 206, 86, 0.6)',
                                                                        'rgba(75, 192, 192, 0.6)'
                                                                    ],
                                                                    borderColor: 'rgba(255, 255, 255, 1)',
                                                                    borderWidth: 1
                                                                }]
                                                        },
                                                        options: {
                                                            scales: {
                                                                y: {
                                                                    beginAtZero: true
                                                                }
                                                            }
                                                        }
                                                    });
                                                }

                                                function updateMonthlyChart() {
                                                    var ctxMonth = document.getElementById('revenueChart').getContext('2d');
                                                    var monthlyOrderData = <%= new Gson().toJson((int[]) request.getAttribute("monthlyOrderData")) %>;

                                                    var revenueChart = new Chart(ctxMonth, {
                                                        type: 'pie',
                                                        data: {
                                                            labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12'],
                                                            datasets: [{
                                                                    label: 'Tổng đơn đặt hàng',
                                                                    data: monthlyOrderData,
                                                                    backgroundColor: [
                                                                        'rgba(255, 99, 132, 0.6)',
                                                                        'rgba(54, 162, 235, 0.6)',
                                                                        'rgba(255, 206, 86, 0.6)',
                                                                        'rgba(75, 192, 192, 0.6)',
                                                                        'rgba(153, 102, 255, 0.6)',
                                                                        'rgba(255, 159, 64, 0.6)',
                                                                        'rgba(199, 199, 199, 0.6)',
                                                                        'rgba(83, 102, 255, 0.6)',
                                                                        'rgba(255, 99, 132, 0.6)',
                                                                        'rgba(54, 162, 235, 0.6)',
                                                                        'rgba(255, 206, 86, 0.6)',
                                                                        'rgba(75, 192, 192, 0.6)'
                                                                    ],
                                                                    borderColor: 'rgba(255, 255, 255, 1)',
                                                                    borderWidth: 1
                                                                }]
                                                        },
                                                        options: {
                                                            scales: {
                                                                y: {
                                                                    beginAtZero: true
                                                                }
                                                            }
                                                        }
                                                    });
                                                }

                                                window.onload = function () {
                                                    updateMonthlyCharts();
                                                    updateMonthlyChart();
                                                };

                                                function updateEndDateMin() {
                                                    const startDate = document.getElementById('startDate').value;
                                                    const endDate = document.getElementById('endDate');


                                                    endDate.min = startDate;


                                                    if (endDate.value === startDate) {
                                                        const nextDay = new Date(startDate);
                                                        nextDay.setDate(nextDay.getDate() + 1);
                                                        endDate.valueAsDate = nextDay;
                                                    }
                                                }

                                                document.addEventListener('DOMContentLoaded', (event) => {
                                                   
                                                    updateEndDateMin();
                                                });


        </script>
    </body>
</html>
