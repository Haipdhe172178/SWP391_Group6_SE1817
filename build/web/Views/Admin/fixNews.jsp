<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Update News</title>
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
                                            <!-- More notifications here -->
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
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                        Update News
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty errorMessage}">
                            <div class="alert alert-danger" role="alert">
                                ${errorMessage}
                            </div>
                        </c:if>
                        <form action="fixnews" method="POST" class="row g-3" enctype="multipart/form-data">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="newsId" value="${news.newId}">

                            <div class="col-md-6">
                                <label for="title" class="form-label">Title</label>
                                <input type="text" class="form-control" id="title" name="title" value="${news.title}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="dateUpload" class="form-label">Date Upload</label>
                                <input type="date" class="form-control" id="dateUpload" name="dateUpload" value="${news.dateUpload}" required>
                            </div>
                            <div class="col-12">
                                <label for="content" class="form-label">Content</label>
                                <textarea class="form-control" id="content" name="content" rows="4" required>${news.content}</textarea>
                            </div>
                            <div class="col-md-6">
                                <label for="img1" class="form-label">Image 1</label>
                                <input type="file" class="form-control" id="img1" name="img1" required>
                                <c:if test="${not empty news.imgNews1}">
                                    <img src="${news.imgNews1}" alt="Image 1" style="max-width: 200px; max-height: 200px;">
                                </c:if>
                            </div>
                            <div class="col-md-6">
                                <label for="img2" class="form-label">Image 2</label>
                                <input type="file" class="form-control" id="img2" name="img2" required>
                                <c:if test="${not empty news.imgNews2}">
                                    <img src="${news.imgNews2}" alt="Image 2" style="max-width: 200px; max-height: 200px;">
                                </c:if>
                            </div>
                            <div class="col-md-6">
                                <label for="source" class="form-label">Source</label>
                                <input type="text" class="form-control" id="source" name="source" value="${news.source}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="topicId" class="form-label">Topic</label>
                                <select class="form-select" id="topicId" name="topicId" required>
                                    <c:forEach var="topic" items="${topics}">
                                        <option value="${topic.topicId}" ${topic.topicId == news.topic.topicId ? 'selected' : ''}>${topic.topicName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="status" class="form-label">Status</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="true" ${news.status ? 'selected' : ''}>Active</option>
                                    <option value="false" ${!news.status ? 'selected' : ''}>Inactive</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary">Update News</button>
                            </div>
                        </form>
                    </div>
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
