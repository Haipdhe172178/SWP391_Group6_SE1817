<%-- 
    Document   : Statistics
    Created on : June 30, 2024
    Author     : huyca
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
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
            .chart-container {
                width: 100%;
                height: 400px;
            }
            .year-select {
                display: flex;
                align-items: center;
            }

            .year-select label,
            .year-select select {
                margin-right: 10px;
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
                            <div class="white_card card_height_100 mb_30">
                                <div class="white_card_header">
                                    <form id="dateRangeForm" method="get" action="statistics" style="display: inline-block;">
                                                <div class="main-title" style="display: inline-block;">
                                                    <label for="startDate" style="display: inline-block; margin-left: 10px; margin-right: 5px;">Từ ngày:</label>
                                                    <input type="date" id="startDate" name="startDate" style="display: inline-block;" value="<%= request.getAttribute("startDate") %>" onchange="updateEndDateMin()">
                                                <label for="endDate" style="display: inline-block; margin-left: 10px; margin-right: 5px;">Đến ngày:</label>
                                                <input type="date" id="endDate" name="endDate" style="display: inline-block;" value="<%= request.getAttribute("endDate") %>">
                                               <button type="submit" class="btn btn-success">Lọc</button>
                                            </div>
                                        </form>
                                </div>                              
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-6 mb_30">
                                <div class="white_card card_height_100 mb_30">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Biểu đồ doanh thu ngày</h3>
                                            </div>                                   
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <div class="chart-container">
                                        <canvas id="revenueChart"></canvas>
                                        <div><h4 class="crm_number" id="revenue">
                                       Tổng doanh thu: <fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="" minFractionDigits="0" maxFractionDigits="0"/> VND
                                    </h4></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                         <div class="col-lg-6 mb_30">
                                <div class="white_card card_height_100 mb_30">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Biểu đồ đơn hàng theo ngày</h3>
                                            </div>                                          
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <div class="chart-container">
                                        <canvas id="revenueChartOrder"></canvas>
                                        <div><h4>Tổng số đơn đặt hàng: ${totalOrders}</h4></div>
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
                                                    function updateDailyChart() {
                                                        var ctxDay = document.getElementById('revenueChart').getContext('2d');
                                                        var dailyOrder = <%= new Gson().toJson((int[]) request.getAttribute("dailyRevenues")) %>;
                                                        var revenueChartDay = new Chart(ctxDay, {
                                                            type: 'bar',
                                                            data: {
                                                                labels: Array.from({length: dailyOrder.length}, (_, i) => i + 1),
                                                                datasets: [{
                                                                        label: 'Doanh thu',
                                                                        data: dailyOrder,
                                                                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                                        borderColor: 'rgba(75, 192, 192, 1)',
                                                                        borderWidth: 1
                                                                    }]
                                                            },
                                                            options: {
                                                                responsive: true,
                                                                plugins: {
                                                                    legend: {
                                                                        display: true,
                                                                        labels: {
                                                                            color: 'rgb(255, 99, 132)',
                                                                            font: {
                                                                                size: 14
                                                                            }
                                                                        }
                                                                    },
                                                                    tooltip: {
                                                                        enabled: true,
                                                                        backgroundColor: 'rgba(0,0,0,0.7)',
                                                                        titleFont: {
                                                                            size: 16
                                                                        },
                                                                        bodyFont: {
                                                                            size: 14
                                                                        }
                                                                    }
                                                                },
                                                                scales: {
                                                                    x: {
                                                                        title: {
                                                                            display: true,
                                                                            text: '',
                                                                            color: '#191919',
                                                                            font: {
                                                                                size: 16,
                                                                                weight: 'bold'
                                                                            }
                                                                        },
                                                                        ticks: {
                                                                            color: '#191919'
                                                                        }
                                                                    },
                                                                    y: {
                                                                        beginAtZero: true,
                                                                        title: {
                                                                            display: true,
                                                                            text: 'Doanh thu',
                                                                            color: '#191919',
                                                                            font: {
                                                                                size: 16,
                                                                                weight: 'bold'
                                                                            }
                                                                        },
                                                                        ticks: {
                                                                            color: '#191919'
                                                                        }
                                                                    }
                                                                },
                                                                animation: {
                                                                    duration: 2000,
                                                                    easing: 'easeInOutBounce'
                                                                }
                                                            }
                                                        });
                                                    }
                                                   
                                                  function updateDailyCharts() {
                                                        var ctxDay = document.getElementById('revenueChartOrder').getContext('2d');
                                                        var dailyOrder = <%= new Gson().toJson((int[]) request.getAttribute("dailyOrder")) %>;

                                                        var revenueChartOrder = new Chart(ctxDay, {
                                                            type: 'bar',
                                                            data: {
                                                                labels: Array.from({length: dailyOrder.length}, (_, i) => i + 1),
                                                                datasets: [{
                                                                        label: 'Tổng số đơn đặt hàng' ,
                                                                        data: dailyOrder,
                                                                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                                        borderColor: 'rgba(75, 192, 192, 1)',
                                                                        borderWidth: 1
                                                                    }]
                                                            },
                                                            options: {
                                                                responsive: true,
                                                                plugins: {
                                                                    legend: {
                                                                        display: true,
                                                                        labels: {
                                                                            color: '#191919',
                                                                            font: {
                                                                                size: 14
                                                                            }
                                                                        }
                                                                    },
                                                                    tooltip: {
                                                                        enabled: true,
                                                                        backgroundColor: 'rgba(0,0,0,0.7)',
                                                                        titleFont: {
                                                                            size: 16,
                                                                            color: '#fff'
                                                                        },
                                                                        bodyFont: {
                                                                            size: 14,
                                                                            color: '#fff'
                                                                        },
                                                                        callbacks: {
                                                                            label: function (tooltipItem) {
                                                                                return 'Đơn đặt hàng: ' + tooltipItem.raw;
                                                                            }
                                                                        }
                                                                    }
                                                                },
                                                                scales: {
                                                                    x: {
                                                                        title: {
                                                                            display: true,
                                                                            text: '',
                                                                            color: '#191919',
                                                                            font: {
                                                                                size: 16,
                                                                                weight: 'bold'
                                                                            }
                                                                        },
                                                                        ticks: {
                                                                            color: '#191919'
                                                                        }
                                                                    },
                                                                    y: {
                                                                        beginAtZero: true,
                                                                        title: {
                                                                            display: true,
                                                                            text: 'Đơn đặt hàng',
                                                                            color: '#191919',
                                                                            font: {
                                                                                size: 16,
                                                                                weight: 'bold'
                                                                            }
                                                                        },
                                                                        ticks: {
                                                                            color: '#191919'
                                                                        }
                                                                    }
                                                                },
                                                                animation: {
                                                                    duration: 2000,
                                                                    easing: 'easeInOutBounce'
                                                                }
                                                            }
                                                        });
                                                    }

                                                    window.onload = function () {
                                                        updateDailyChart();
                                                        updateDailyCharts();
                                                    };
                                                    function updateEndDateMin() {
                                                        const startDate = document.getElementById('startDate').value;
                                                        const endDate = document.getElementById('endDate');

                                                        endDate.min = startDate;


                                                        if (endDate.valueAsDate <= new Date(startDate)) {
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
