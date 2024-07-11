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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            .filter_button {
                margin-left: 10px;
                align-self: flex-end;
            }
        </style>
    </head>
    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

        <section class="main_content dashboard_part large_header_bg">
            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

            <div class="main_content_iner">
                <div class="container-fluid p-0">
                    <div class="row justify-content-center">
                        <div class="col-lg-12">
                            <div class="white_card card_height_100 mb_30">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <h3 class="m-0">Quản lý tin tức</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <div class="QA_section">
                                        <div class="white_box_tittle list_header">
                                            <h4>Tin tức</h4>
                                            <div class="box_right d-flex lms_block align-items-center">
                                                <!-- Search Field -->
                                                <div class="serach_field_2">
                                                    <div class="search_inner">
                                                        <form action="upnews" method="GET" class="d-flex align-items-center">
                                                            <div class="search_field me-2">
                                                                <input name="s" type="text" class="form-control" placeholder="Tìm kiếm ngay..." value="${searchTerm}">
                                                            </div>
                                                            <button type="submit" class="btn btn-primary"><i class="fas fa-search"></i></button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- Filter Fields -->
                                            <div class="box_right d-flex lms_block align-items-center mt-3">
                                                <form id="filterForm" action="upnews" method="GET" class="d-flex align-items-center">
                                                    <div class="filter_field d-flex me-2">
                                                        <select name="status" class="form-select me-2" onchange="document.getElementById('filterForm').submit();">
                                                            <option value="">Tất cả trạng thái</option>
                                                            <option value="true" ${status == 'true' ? 'selected' : ''}>Hoạt động</option>
                                                            <option value="false" ${status == 'false' ? 'selected' : ''}>Không hoạt động</option>
                                                        </select>
                                                        <select name="topicId" class="form-select" onchange="document.getElementById('filterForm').submit();">
                                                            <option value="-1">Tất cả chủ đề</option>
                                                            <c:forEach var="topic" items="${topics}">
                                                                <option value="${topic.topicId}" ${topicId == topic.topicId ? 'selected' : ''}>${topic.topicName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <!-- Add Button -->
                                                    <div class="filter_button ms-2">
                                                        <a href="addnews" class="btn_1 btn btn-success">Thêm Tin Tức</a>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                        <div class="QA_table mb_30">
                                            <table class="table lms_table_active custom_table">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Tiêu Đề</th>
                                                        <th>Nội Dung</th>
                                                        <th>Ngày Đăng Tải</th>
                                                        <th>Chủ Đề</th>
                                                        <th>Nguồn</th>
                                                        <th>Ảnh</th>
                                                        <th>Trạng Thái</th>
                                                        <th>Hành Động</th>
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
                                                        <td><img src="${news.imgNews}" alt="Image 1"></td>
                                                        <td>${news.status ? 'Hoạt động' : 'Không hoạt động'}</td>
                                                        <td>
                                                            <a href="fixnews?action=edit&id=${news.newId}" title="Update"><i class="fas fa-edit"></i></a>
                                                            <a href="fixnews?action=delete&id=${news.newId}" title="Delete" onclick="return confirm('Bạn có muốn xóa tin tức này không?');"><i class="fas fa-trash-alt"></i></a>
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
                                                            <a class="page-link" href="upnews?page=${currentPage - 1}&s=${searchTerm}&status=${status}&topicId=${topicId}&sortOrder=${sortOrder}" aria-label="Previous">
                                                                <span aria-hidden="true">Previous</span>
                                                            </a>
                                                        </li>
                                                    </c:if>

                                                    <!-- Page Number Links -->
                                                    <c:forEach begin="${start}" end="${end}" var="i">
                                                        <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                            <a class="page-link" href="upnews?page=${i}&s=${searchTerm}&status=${status}&topicId=${topicId}&sortOrder=${sortOrder}">${i}</a>
                                                        </li>
                                                    </c:forEach>

                                                    <!-- Next Button -->
                                                    <c:if test="${currentPage < totalPages}">
                                                        <li class="page-item">
                                                            <a class="page-link" href="upnews?page=${currentPage + 1}&s=${searchTerm}&status=${status}&topicId=${topicId}&sortOrder=${sortOrder}" aria-label="Next">
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
