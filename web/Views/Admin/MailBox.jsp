<%-- 
    Document   : MailBox.jsp
    Created on : Jun 18, 2024, 11:46:54 PM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Sales</title>
    <link rel="icon" href="img/logo.png" type="image/png">

    <link rel="stylesheet" href="css/bootstrap1.min.css" />
    <link rel="stylesheet" href="vendors/themefy_icon/themify-icons.css" />
    <link rel="stylesheet" href="vendors/niceselect/css/nice-select.css" />
    <link rel="stylesheet" href="vendors/owl_carousel/css/owl.carousel.css" />
    <link rel="stylesheet" href="vendors/gijgo/gijgo.min.css" />
    <link rel="stylesheet" href="vendors/font_awesome/css/all.min.css" />
    <link rel="stylesheet" href="vendors/tagsinput/tagsinput.css" />
    <link rel="stylesheet" href="vendors/datepicker/date-picker.css" />
    <link rel="stylesheet" href="vendors/scroll/scrollable.css" />
    <link rel="stylesheet" href="vendors/datatable/css/jquery.dataTables.min.css" />
    <link rel="stylesheet" href="vendors/datatable/css/responsive.dataTables.min.css" />
    <link rel="stylesheet" href="vendors/datatable/css/buttons.dataTables.min.css" />
    <link rel="stylesheet" href="vendors/text_editor/summernote-bs4.css" />
    <link rel="stylesheet" href="vendors/morris/morris.css">
    <link rel="stylesheet" href="vendors/material_icon/material-icons.css" />
    <link rel="stylesheet" href="vendors/calender_js/core/main.css">
    <link rel="stylesheet" href="vendors/calender_js/daygrid/main.css">
    <link rel="stylesheet" href="vendors/calender_js/timegrid/main.css">
    <link rel="stylesheet" href="vendors/calender_js/list/main.css">
    <link rel="stylesheet" href="css/metisMenu.css">
    <link rel="stylesheet" href="css/style1.css" />
    <link rel="stylesheet" href="css/colors/default.css" id="colorSkinCSS">
</head>

<body class="crm_body_bg">
    <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

    <section class="main_content dashboard_part large_header_bg">
        <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

        <div class="main_content_iner">
            <div class="container-fluid p-0">
                <div class="row justify-content-center">
                    <div class="col-12">
                        <div class="dashboard_header mb_50"></div>
                    </div>

                    <div class="col-md-12">
                        <div class="white_box QA_section mb_30">
                            <div class="white_box_tittle list_header">
                                <h4>Liên hệ qua Email với khách hàng</h4>
                                <div class="box_right d-flex lms_block">
                                    <div class="search-box">
                                        <input type="text" id="searchInput" class="form-control" placeholder="Tìm kiếm...">
                                    </div>
                                </div>
                            </div>
                            <div class="QA_table">
                                <table id="contactTable" class="table lms_table_active">
                                    <thead>
                                        <tr>
                                            <th scope="col"></th>
                                            <th scope="col">Tên khách hàng</th>
                                            <th scope="col">Email</th>
                                            <th scope="col">Số điện thoại</th>
                                            <th scope="col">Chủ đề</th>
                                            <th scope="col">Nội dung</th>
                                            <th scope="col">Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${contact}" var="c">
                                            <tr>
                                                <td></td>
                                                <td>${c.userName}</td>
                                                <td>${c.email}</td>
                                                <td>${c.phoneNumber}</td>
                                                <td>${c.topic}</td>
                                                <td>${c.message}</td>
                                                <td><a href="reply">Trả Lời</a></td>
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
                            <p>2020 © Influence - Designed by <a href="#"><i class="ti-heart"></i></a><a href="#"> Dashboard</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <div id="back-top" style="display: none;">
        <a title="Go to Top" href="#"><i class="ti-angle-up"></i></a>
    </div>

    <script src="js/jquery1-3.4.1.min.js"></script>
    <script src="js/popper1.min.js"></script>
    <script src="js/bootstrap1.min.js"></script>
    <script src="js/metisMenu.js"></script>
    <script src="js/custom.js"></script>

    <script>
        $(document).ready(function () {
            $('#searchInput').on('keyup', function () {
                var value = $(this).val().toLowerCase();
                $('#contactTable tbody tr').filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
                });
            });
        });
    </script>

</body>

</html>
