<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Add News</title>
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
        <script>
            function validateForm() {
                var title = document.getElementById("title").value.trim();
                var content = document.getElementById("content").value.trim();
                var source = document.getElementById("source").value.trim();

                if (title === "") {
                    alert("Title không được phép chứa toàn dấu cách.");
                    return false;
                }
                if (content === "") {
                    alert("Content không được phép chứa toàn dấu cách.");
                    return false;
                }
                if (source === "") {
                    alert("Source không được phép chứa toàn dấu cách.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body class="crm_body_bg">
        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">

            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

                <div class="main_content_iner">
                    <div class="container mt-5">
                        <div class="card">
                            <div class="card-header bg-primary text-white">
                                Add News
                            </div>
                            <div class="card-body">
                                <form action="AddNewsController" method="POST" enctype="multipart/form-data" class="row g-3" onsubmit="return validateForm()">
                                    <div class="col-md-6">
                                        <label for="title" class="form-label">Title</label>
                                        <input type="text" class="form-control" id="title" name="title" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="dateUpload" class="form-label">Date Upload</label>
                                        <input type="date" class="form-control" id="dateUpload" name="dateUpload" required>
                                    </div>
                                    <div class="col-12">
                                        <label for="content" class="form-label">Content</label>
                                        <textarea class="form-control" id="content" name="content" rows="4" required></textarea>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="img1" class="form-label">Image 1</label>
                                        <input type="file" class="form-control" id="img1" name="img1" accept="image/jpeg, image/png, image/gif, image/webp" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="img2" class="form-label">Image 2</label>
                                        <input type="file" class="form-control" id="img2" name="img2" accept="image/jpeg, image/png, image/gif, image/webp" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="source" class="form-label">Source</label>
                                        <input type="text" class="form-control" id="source" name="source" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="topicId" class="form-label">Topic</label>
                                        <select class="form-select" id="topicId" name="topicId" required>
                                        <c:forEach var="topic" items="${topics}">
                                            <option value="${topic.topicId}">${topic.topicName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label for="status" class="form-label">Status</label>
                                    <select class="form-select" id="status" name="status" required>
                                        <option value="true">Active</option>
                                        <option value="false">Inactive</option>
                                    </select>
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn btn-primary">Add News</button>
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
