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
    <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

    <section class="main_content dashboard_part large_header_bg">
        <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

        <div class="main_content_iner">
            <div class="container mt-5">
                <div class="card">
                    <div class="card-header bg-primary text-white">
                         Cập Nhật Tin Tức
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
                                <label for="title" class="form-label">Tiêu Đề</label>
                                <input type="text" class="form-control" id="title" name="title" value="${news.title}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="dateUpload" class="form-label">Ngày Đăng Tải</label>
                                <input type="date" class="form-control" id="dateUpload" name="dateUpload" value="${news.dateUpload}" required>
                            </div>
                            <div class="col-12">
                                <label for="content" class="form-label">Nội Dung</label>
                                <textarea class="form-control" id="content" name="content" rows="4" required>${news.content}</textarea>
                            </div>
                            <div class="col-md-6">
                                <label for="img" class="form-label">Ảnh</label>
                                <input type="file" class="form-control" id="img" name="img">
                                <c:if test="${not empty news.imgNews}">
                                    <img src="${news.imgNews}" alt="Image" style="max-width: 200px; max-height: 200px;">
                                    <input type="hidden" name="currentImg" value="${news.imgNews}">
                                </c:if>
                            </div>
                            <div class="col-md-6">
                                <label for="source" class="form-label">Nguồn</label>
                                <input type="text" class="form-control" id="source" name="source" value="${news.source}" required>
                            </div>
                            <div class="col-md-6">
                                <label for="topicId" class="form-label">Chủ đề</label>
                                <select class="form-select" id="topicId" name="topicId" required>
                                    <c:forEach var="topic" items="${topics}">
                                        <option value="${topic.topicId}" ${topic.topicId == news.topic.topicId ? 'selected' : ''}>${topic.topicName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label for="status" class="form-label">Trạng thái</label>
                                <select class="form-select" id="status" name="status" required>
                                    <option value="true" ${news.status ? 'selected' : ''}>Hoạt động</option>
                                    <option value="false" ${!news.status ? 'selected' : ''}>Không hoạt động</option>
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
