<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>News Management</title>
    <link rel="stylesheet" href="css/bootstrap1.min.css" />
    <link rel="stylesheet" href="vendors/themefy_icon/themify-icons.css" />
    <link rel="stylesheet" href="vendors/scroll/scrollable.css" />
    <link rel="stylesheet" href="vendors/font_awesome/css/all.min.css" />
    <link rel="stylesheet" href="vendors/datatable/css/jquery.dataTables.min.css" />
    <link rel="stylesheet" href="vendors/datatable/css/responsive.dataTables.min.css" />
    <link rel="stylesheet" href="vendors/datatable/css/buttons.dataTables.min.css" />
    <link rel="stylesheet" href="css/metisMenu.css">
    <link rel="stylesheet" href="css/style1.css" />
    <style>
        .form-container {
            width: 100%;
            max-width: 1200px;
            margin: 0 auto;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
        .table img {
            max-width: 100px;
            height: auto;
        }
        .add_button {
            margin-left: auto;
        }
        .custom_table {
            width: 100%;
            max-width: 1200px;
            margin: 20px auto;
        }
        .pagination {
            display: flex;
            justify-content: center;
            list-style: none;
            padding: 0;
        }
        .pagination li {
            margin: 0 5px;
        }
        .pagination a {
            text-decoration: none;
            padding: 8px 16px;
            border: 1px solid #ddd;
            color: #007bff;
        }
        .pagination a.active {
            background-color: #007bff;
            color: white;
            border: 1px solid #007bff;
        }
    </style>
</head>
<body class="crm_body_bg">
    <nav class="sidebar vertical-scroll ps-container ps-theme-default ps-active-y">
        <div class="logo d-flex justify-content-between">
            <a href="dash"><img src="images/anh456.png" alt="Logo"></a>
            <div class="sidebar_close_icon d-lg-none">
                <i class="ti-close"></i>
            </div>
        </div>
        <ul id="sidebar_menu">
            <li class="mm-active">
                <a class="has-arrow" href="#" aria-expanded="false">
                    <div class="icon_menu">
                        <img src="img/menu-icon/dashboard.svg" alt="Dashboard">
                    </div>
                    <span>Dashboard</span>
                </a>
                <ul>
                    <li><a class="active" href="index.html">Sales</a></li>
                </ul>
            </li>
            <li>
                <a class="has-arrow" href="#" aria-expanded="false">
                    <div class="icon_menu">
                        <img src="img/menu-icon/2.svg" alt="Apps">
                    </div>
                    <span>Apps</span>
                </a>
                <ul>
                    <li><a href="contactAdmin">Liên hệ</a></li>
                    <li><a href="chat.html">Chat</a></li>
                </ul>
            </li>
            <li>
                <a class="has-arrow" href="#" aria-expanded="false">
                    <div class="icon_menu">
                        <img src="img/menu-icon/8.svg" alt="Sale">
                    </div>
                    <span>Sale</span>
                </a>
                <ul>
                    <li><a href="image">Image Background</a></li>
                    <li><a href="discount">Discount</a></li>
                </ul>
            </li>
            <li>
                <a class="has-arrow" href="#" aria-expanded="false">
                    <div class="icon_menu">
                        <img src="img/menu-icon/11.svg" alt="Table">
                    </div>
                    <span>Table</span>
                </a>
                <ul>
                    <li><a href="data">Sản phẩm</a></li>
                    <li><a href="#">Thể Loại</a></li>
                    <li><a href="#">Tác Giả</a></li>
                </ul>
            </li>
            <li class="mm-active">
                <a class="has-arrow" href="#" aria-expanded="false">
                    <div class="icon_menu">
                        <img src="img/menu-icon/17.svg" alt="Up News">
                    </div>
                    <span>Up News</span>
                </a>
                <ul>
                    <li><a class="active" href="upnews">News</a></li>
                </ul>
            </li>
        </ul>
    </nav>

   <section class="main_content dashboard_part large_header_bg">
        <div class="container-fluid g-0">
            <div class="row">
                <div class="col-lg-12 p-0">
                    <div class="header_iner d-flex justify-content-between align-items-center">
                        <div class="sidebar_icon d-lg-none">
                            <i class="ti-menu"></i>
                        </div>
                        <div class="serach_field-area d-flex align-items-center">
                            <div class="search_inner">
                                <form action="data" method="GET">
                                    <div class="search_field">
                                        <input name="s" type="text" placeholder="Search here...">
                                    </div>
                                    <button type="submit"><img src="img/icon/icon_search.svg" alt="Search"></button>
                                </form>
                            </div>
                            <span class="f_s_14 f_w_400 ml_25 white_text text_white">Apps</span>
                        </div>
                        <div class="header_right d-flex justify-content-between align-items-center">
                            <div class="header_notification_warp d-flex align-items-center">
                                <li>
                                    <a class="bell_notification_clicker nav-link-notify" href="#"><img src="img/icon/bell.svg" alt="Notifications"></a>
                                    <div class="Menu_NOtification_Wrap">
                                        <div class="notification_Header">
                                            <h4>Notifications</h4>
                                        </div>
                                        <div class="Notification_body">
                                            <div class="single_notify d-flex align-items-center">
                                                <div class="notify_thumb">
                                                    <a href="#"><img src="img/staf/2.png" alt="Notification"></a>
                                                </div>
                                                <div class="notify_content">
                                                    <a href="#">
                                                        <h5>Cool Marketing</h5>
                                                    </a>
                                                    <p>Lorem ipsum dolor sit amet</p>
                                                </div>
                                            </div>
                                            <div class="single_notify d-flex align-items-center">
                                                <div class="notify_thumb">
                                                    <a href="#"><img src="img/staf/4.png" alt="Notification"></a>
                                                </div>
                                                <div class="notify_content">
                                                    <a href="#">
                                                        <h5>Awesome packages</h5>
                                                    </a>
                                                    <p>Lorem ipsum dolor sit amet</p>
                                                </div>
                                            </div>
                                            <div class="single_notify d-flex align-items-center">
                                                <div class="notify_thumb">
                                                    <a href="#"><img src="img/staf/3.png" alt="Notification"></a>
                                                </div>
                                                <div class="notify_content">
                                                    <a href="#">
                                                        <h5>What a packages</h5>
                                                    </a>
                                                    <p>Lorem ipsum dolor sit amet</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="nofity_footer">
                                            <div class="submit_button text-center pt_20">
                                                <a href="#" class="btn_1">See More</a>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <a class="CHATBOX_open nav-link-notify" href="#"><img src="img/icon/msg.svg" alt="Messages"></a>
                                </li>
                            </div>
                            <div class="profile_info">
                                <img src="img/client_img.png" alt="Profile">
                                <div class="profile_info_iner">
                                    <div class="profile_author_name">
                                        <p>Neurologist</p>
                                        <h5>Dr. Robar Smith</h5>
                                    </div>
                                    <div class="profile_info_details">
                                        <a href="#">My Profile</a>
                                        <a href="#">Settings</a>
                                        <a href="#">Log Out</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

          <div class="main_content_iner">
            <div class="container-fluid p-0">
                <div class="row justify-content-center">
                    <div class="col-lg-12">
                        <div class="white_card card_height_100 mb_30">
                            <div class="white_card_header">
                                <div class="box_header m-0">
                                    <div class="main-title">
                                        <h3 class="m-0">News Management</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="white_card_body">
                                <div class="QA_section">
                                    <div class="white_box_tittle list_header">
                                        <h4>Table</h4>
                                        <div class="box_right d-flex lms_block">
                                            <div class="serach_field_2">
                                                <div class="search_inner">
                                                    <form action="upnews" method="GET">
                                                        <div class="search_field">
                                                            <input name="s" type="text" placeholder="Search here...">
                                                        </div>
                                                        <button type="submit"><img src="img/icon/icon_search.svg" alt="Search"></button>
                                                    </form>
                                                </div>
                                            </div>
                                            <div class="add_button ms-2">
                                                <a href="addnews" class="btn_1">Add News</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="QA_table mb_30">
                                        <table class="table lms_table_active custom_table">
                                            <thead>
                                                <tr>
                                                    <th>ID</th>
                                                    <th>Title</th>
                                                    <th>Content</th>
                                                    <th>Date Upload</th>
                                                    <th>Topic</th>
                                                    <th>Source</th>
                                                    <th>Image 1</th>
                                                    <th>Image 2</th>
                                                    <th>Status</th> <!-- New Status column -->
                                                    <th>Actions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="news" items="${listNews}">
                                                    <tr>
                                                        <td>${news.newId}</td>
                                                        <td>${news.title}</td>
                                                        <td>${fn:substring(news.content, 0, 50)}...</td>
                                                        <td>${news.dateUpload}</td>
                                                        <td>${news.topic.topicName}</td>
                                                        <td>${news.source}</td>
                                                        <td><img src="${news.imgNews1}" alt="Image 1"></td>
                                                        <td><img src="${news.imgNews2}" alt="Image 2"></td>
                                                        <td>${news.status ? 'Active' : 'Inactive'}</td> <!-- Status value -->
                                                        <td>
                                                            <a href="fixnews?action=edit&id=${news.newId}" class="btn btn-primary btn-sm">Update</a>
                                                            <a href="fixnews?action=delete&id=${news.newId}" class="btn btn-danger btn-sm">Delete</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                        <!-- Pagination -->
                                        <nav class="py-5" aria-label="Page navigation">
                                            <ul class="pagination justify-content-center gap-4">
                                                <!-- Determine the range of pages to display -->
                                                <c:set var="start" value="${currentPage > 3 ? currentPage - 2 : 1}" />
                                                <c:set var="end" value="${currentPage > 3 ? currentPage + 2 : 5}" />
                                                <c:if test="${end > totalPages}">
                                                    <c:set var="end" value="${totalPages}" />
                                                    <c:set var="start" value="${totalPages - 4 > 0 ? totalPages - 4 : 1}" />
                                                </c:if>

                                                <!-- Previous Button -->
                                                <c:if test="${currentPage > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="upnews?page=${currentPage - 1}&s=${searchTerm}&sortOrder=${sortOrder}" aria-label="Previous">
                                                            <span aria-hidden="true">Previous</span>
                                                        </a>
                                                    </li>
                                                </c:if>

                                                <!-- Page Number Links -->
                                                <c:forEach begin="${start}" end="${end}" var="i">
                                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                        <a class="page-link" href="upnews?page=${i}&s=${searchTerm}&sortOrder=${sortOrder}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <!-- Next Button -->
                                                <c:if test="${currentPage < totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="upnews?page=${currentPage + 1}&s=${searchTerm}&sortOrder=${sortOrder}" aria-label="Next">
                                                            <span aria-hidden="true">Next</span>
                                                        </a>
                                                    </li>
                                                </c:if>
                                            </ul>
                                        </nav>
                                    </div>   
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12"></div>
                </div>
            </div>
        </div>

        <div class="footer_part">
            <div class="container">
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
        <a title="Go to Top" href="#">
            <i class="ti-angle-up"></i>
        </a>
    </div>

    <script src="js/jquery1-3.4.1.min.js"></script>
    <script src="js/popper1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/metisMenu.js"></script>
    <script src="vendors/datatable/js/jquery.dataTables.min.js"></script>
    <script src="vendors/datatable/js/dataTables.responsive.min.js"></script>
    <script src="vendors/datatable/js/dataTables.buttons.min.js"></script>
    <script src="vendors/datatable/js/buttons.flash.min.js"></script>
    <script src="vendors/datatable/js/jszip.min.js"></script>
    <script src="vendors/datatable/js/pdfmake.min.js"></script>
    <script src="vendors/datatable/js/vfs_fonts.js"></script>
    <script src="vendors/datatable/js/buttons.html5.min.js"></script>
    <script src="vendors/datatable/js/buttons.print.min.js"></script>
    <script src="vendors/scroll/perfect-scrollbar.min.js"></script>
    <script src="vendors/scroll/scrollable-custom.js"></script>
    <script src="js/custom.js"></script>
</body>
</html>