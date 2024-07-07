<%-- 
    Document   : OrderStatus
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
        <style>
            .scrollable-table {
                max-height: 600px;
                overflow-y: auto;
            }
            .table-container {
                width: 100%;
                display: block;
            }
            .search-box {
                margin-bottom: 20px;
                display: flex;
                justify-content: flex-end;
            }

            .search-box input {
                width: 300px;
                height: 35px;
                padding: 5px 10px;
                border-radius: 5px;
                border: 1px solid #ced4da;
                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
            }

            .search-box input:focus {
                outline: none;
                border-color: #80bdff;
                box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
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
                            <div class="col-lg-12 mb_20">
                                <div class="white_card card_height_100 mb_20">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Đơn hàng trạng thái: ${statusName}</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body QA_section">
                                    <div class="search-box">
                                        <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm...">
                                    </div>                                   
                                    <div class="QA_table table-container scrollable-table">
                                        <table class="table lms_table_active2 p-0" id="orderTable">
                                            <thead>
                                                <tr>
                                                    <th>ID đơn hàng</th>
                                                    <th>Tên khách hàng</th>
                                                    <th>Email</th>
                                                    <th>Số điện thoại</th>
                                                    <th>Địa chỉ</th>
                                                    <th data-sort="totalPrice" style="cursor: pointer; color: black">
                                                        Tổng giá <i class="fas fa-sort" style="margin-left: 5px;"></i>
                                                    </th>
                                                    <th data-sort="date" style="cursor: pointer; color: black">
                                                        Ngày <i class="fas fa-sort" style="margin-left: 5px;"></i>
                                                    </th>

                                                    <th>Trạng thái</th>
                                                    <th>Loại</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach var="order" items="${recentOrders}">
                                                    <tr>
                                                        <td>${order.orderId}</td>
                                                        <td>${order.fullName}</td>
                                                        <td>${order.email != null ? order.email : 'N/A'}</td>
                                                        <td>${order.phoneNumber != null ? order.phoneNumber : 'N/A'}</td>
                                                        <td>${order.address != null ? order.address : 'N/A'}</td>
                                                        <td>
                                                            <fmt:formatNumber value="${order.totalPrice}" type="number" minFractionDigits="0" maxFractionDigits="0"/> VND
                                                        </td>
                                                        <td><fmt:formatDate value="${order.date}" pattern="yyyy-MM-dd"/></td>
                                                        <td>${order.statusName}</td>
                                                        <td>${order.orderType}</td>
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
            document.addEventListener('DOMContentLoaded', function () {
                const searchInput = document.getElementById('searchInput');
                searchInput.addEventListener('keyup', function () {
                    const filter = searchInput.value.toLowerCase();
                    const rows = document.querySelectorAll('#orderTable tbody tr');
                    rows.forEach(row => {
                        const cells = row.getElementsByTagName('td');
                        let match = false;
                        for (let i = 0; i < cells.length; i++) {
                            if (cells[i].textContent.toLowerCase().includes(filter)) {
                                match = true;
                                break;
                            }
                        }
                        row.style.display = match ? '' : 'none';
                    });
                });
            });

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
    </body>

</html>
