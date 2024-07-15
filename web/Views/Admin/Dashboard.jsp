<%-- 
    Document   : Dashboard
    Created on : May 27, 2024, 10:05:51 AM
    Author     : huyca
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.OrderStatus" %>
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
            .scrollable-table {
                max-height: 500px;
                overflow-y: auto;
            }
            .table-container {
                width: 100%;
                display: block;
            }
            .view-all-btn {
                display: flex;
                justify-content: flex-end;
                padding-top: 10px;
            }

            .progress-bar {
                transition: width 0.6s ease;
            }

            .progress-bar-striped {
                background-image: linear-gradient(45deg, rgba(255, 255, 255, .15) 25%, transparent 25%, transparent 50%, rgba(255, 255, 255, .15) 50%, rgba(255, 255, 255, .15) 75%, transparent 75%, transparent);
                background-size: 1rem 1rem;
            }

            .progress-bar-animated {
                animation: progress-bar-stripes 1s linear infinite;
            }

            @keyframes progress-bar-stripes {
                from {
                    background-position: 1rem 0;
                }

                to {
                    background-position: 0 0;
                }
            }

            .progress-bar.confirmation {
                background-color: #17a2b8;
            }

            .progress-bar.verified {
                background-color: #28a745;
            }

            .progress-bar.shipping {
                background-color: #ffc107;
            }

            .progress-bar.completed {
                background-color: #007bff;
            }

            .progress-bar.cancelled {
                background-color: #dc3545;
            }

            .QA_table table tbody tr td {
                padding: 8px 10px;
            }
            .apply-filter-button {
                background-color: #28a745;
                color: white;
                border: none;
                padding: 10px 20px;
                cursor: pointer;
                border-radius: 5px;
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
                                                <h3 class="m-0">
                                                    <i class="fas fa-shopping-cart"></i> 
                                                    Tổng số đơn hàng
                                                </h3>
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
                                            <h3 class="m-0">
                                                <i class="fas fa-coins"></i> 
                                                Doanh thu
                                            </h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <h2 class="crm_number" id="revenue">
                                        <fmt:formatNumber value="${totalRevenue}" type="currency" currencySymbol="" minFractionDigits="0" maxFractionDigits="0"/> VND                                      
                                    </h2>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Trạng thái đơn hàng</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_chart">
                                        <canvas id="orderStatusChart"></canvas>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 mb_20">
                            <div class="white_card card_height_100 mb_20">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Đơn hàng</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <!-- Filters Section -->
                                    <div class="filter-section">
                                        <!-- Filter by Name Dropdown -->
                                        <label for="nameFilter">Tên:</label>
                                        <select id="nameFilter">
                                            <option value="all">Tất Cả</option>
                                            <c:forEach var="order" items="${recentOrders}">
                                                <option value="${order.fullName}">${order.fullName}</option>
                                            </c:forEach>
                                        </select>

                                        <!-- Date Range Filters -->
                                        <label for="monthFilter">Tháng:</label>
                                        <input type="month" id="monthFilter">

                                        <!-- Apply Filter Button -->
                                        <button id="applyFilter" class="apply-filter-button">Lọc</button>
                                    </div>

                                    <!-- Chart Canvas -->
                                    <canvas id="orderChart" width="400" height="200"></canvas>
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
                                            <h3 class="m-0">Lượt mua sản phẩm</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="QA_table table-container scrollable-table">
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
                                                        <td class="f_s_14 f_w_400 color_text_2">
                                                            <fmt:formatNumber value="${p.price}" type="number" minFractionDigits="0" maxFractionDigits="0"/> VND
                                                        </td>
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
                                            <h3 class="m-0">Người Mua Hàng Nhiều Nhất</h3>
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
                                                        <td>
                                                            <fmt:formatNumber value="${buyer.totalSpent}" type="number" minFractionDigits="0" maxFractionDigits="0"/> VND
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
        <script>
            $(document).ready(function () {
                $("th[data-sort]").click(function () {
                    var column = $(this).data("sort");
                    var order = $(this).hasClass("asc") ? "desc" : "asc";
                    $("th[data-sort]").removeClass("asc desc");
                    $(this).addClass(order);
                    sortTable(column, order);
                });
                function sortTable(column, order) {
                    var rows = $("#orderTable tbody tr").get();
                    rows.sort(function (rowA, rowB) {
                        var valueA = $(rowA).find("td:eq(" + $("th[data-sort='" + column + "']").index() + ")").text();
                        var valueB = $(rowB).find("td:eq(" + $("th[data-sort='" + column + "']").index() + ")").text();
                        if (column === "totalPrice") {
                            valueA = parseFloat(valueA.replace(/[^\d.-]/g, ''));
                            valueB = parseFloat(valueB.replace(/[^\d.-]/g, ''));
                        } else if (column === "date") {
                            valueA = new Date(valueA);
                            valueB = new Date(valueB);
                        }

                        if (order === "asc") {
                            return valueA > valueB ? 1 : -1;
                        } else {
                            return valueA < valueB ? 1 : -1;
                        }
                    });
                    $.each(rows, function (index, row) {
                        $("#orderTable tbody").append(row);
                    });
                }
            });
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var ctx = document.getElementById('orderChart').getContext('2d');
                var recentOrders = [
            <c:forEach var="order" items="${recentOrders}">
                    {
                        fullName: "${order.fullName}",
                        totalPrice: ${order.totalPrice},
                        date: "${order.date}"
                    },
            </c:forEach>
                ];
                console.log('Initial recentOrders:', recentOrders);
                var orderChart;
                function updateChart(selectedName, selectedMonth) {
                    var filteredLabels = [];
                    var filteredData = [];
                    recentOrders.forEach(function (order) {
                        var orderMonth = order.date.substring(0, 7);
                        if ((selectedName === 'all' || order.fullName === selectedName) && (selectedMonth === '' || orderMonth === selectedMonth)) {
                            filteredLabels.push(order.date);
                            filteredData.push(order.totalPrice);
                        }
                    });
                    orderChart.data.labels = filteredLabels;
                    orderChart.data.datasets[0].data = filteredData;
                    orderChart.update();
                    console.log('Filtered labels:', filteredLabels);
                    console.log('Filtered data:', filteredData);
                }

                var orderData = {
                    labels: recentOrders.map(order => order.date),
                    datasets: [{
                            label: 'Tổng giá (VND)',
                            data: recentOrders.map(order => order.totalPrice),
                            fill: false,
                            backgroundColor: 'rgba(75, 192, 192, 0.2)',
                            borderColor: 'rgba(75, 192, 192, 1)',
                            borderWidth: 1
                        }]
                };
                orderChart = new Chart(ctx, {
                    type: 'bar',
                    data: orderData,
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
                document.getElementById('applyFilter').addEventListener('click', function () {
                    var selectedName = document.getElementById('nameFilter').value;
                    var selectedMonth = document.getElementById('monthFilter').value;
                    updateChart(selectedName, selectedMonth);
                });
            });
        </script>
        <% 
    List<OrderStatus> orderStatusCounts = (List<OrderStatus>) request.getAttribute("orderStatusCounts"); 
        %>
        <script>
            // Dữ liệu từ servlet
            var orderStatusCounts = JSON.parse('<%= new com.google.gson.Gson().toJson(orderStatusCounts) %>');

            // Tạo nhãn và dữ liệu cho biểu đồ
            var labels = [];
            var data = [];

            orderStatusCounts.forEach(function (status) {
                labels.push(status.statusName);
                data.push(status.pendingCount);
            });

            // Khởi tạo biểu đồ Chart.js
            var ctx = document.getElementById('orderStatusChart').getContext('2d');
            var orderStatusChart = new Chart(ctx, {
                type: 'pie', // Đổi từ 'bar' thành 'pie'
                data: {
                    labels: labels,
                    datasets: [{
                            label: 'Số lượng đơn hàng',
                            data: data,
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(255, 206, 86, 0.2)',
                                'rgba(75, 192, 192, 0.2)',
                                'rgba(153, 102, 255, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(255, 206, 86, 1)',
                                'rgba(75, 192, 192, 1)',
                                'rgba(153, 102, 255, 1)'
                            ],
                            borderWidth: 2
                        }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        tooltip: {
                            callbacks: {
                                label: function (tooltipItem) {
                                    return labels[tooltipItem.dataIndex] + ': ' + data[tooltipItem.dataIndex];
                                }
                            }
                        }
                    },
                    onClick: function (evt, item) {
                        if (item.length > 0) {
                            var elementIndex = item[0]._index; 
                            var statusId = orderStatusCounts[elementIndex].statusId; 
                            window.location.href = 'orderstatus?status=' + statusId; 
                        }
                    }

                }
            });
        </script>
    </body>

</html>

