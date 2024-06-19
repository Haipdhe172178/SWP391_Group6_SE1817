<%-- 
    Document   : DataTable
    Created on : May 27, 2024, 10:09:46 AM
    Author     : huyca
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

    <head>

        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Sales</title>


        <link rel="stylesheet" href="css/bootstrap1.min.css" />

        <link rel="stylesheet" href="vendors/themefy_icon/themify-icons.css" />

        <link rel="stylesheet" href="vendors/scroll/scrollable.css" />

        <link rel="stylesheet" href="vendors/font_awesome/css/all.min.css" />

        <link rel="stylesheet" href="vendors/datatable/css/jquery.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/responsive.dataTables.min.css" />
        <link rel="stylesheet" href="vendors/datatable/css/buttons.dataTables.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

        <link rel="stylesheet" href="css/metisMenu.css">

        <link rel="stylesheet" href="css/style1.css" />

        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: center;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #ddd;
            }
            .status-approved {
                color: green;
            }
            .status-pending {
                color: orange;
            }
            .status-rejected {
                color: red;
            }
            .review-content {
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .rating {
                display: flex;
                align-items: center;
            }
            .star {
                width: 20px;
                height: 20px;
                fill: gold;
                margin-right: 2px;
            }
            .my-2 {
                margin-top: 0.5rem;
                margin-bottom: 0.5rem;
            }
            .me-2 {
                margin-right: 0.5rem;
            }
            .fs-6 {
                font-size: 1rem;
            }
            .text-black-50 {
                color: rgba(0, 0, 0, 0.5);
            }
            .text-warning {
                color: #ffc107;
            }
            /* Basic button styles */
            .btn {
                display: inline-block; /* Makes div behave like button */
                padding: 5px 10px; /* Smaller padding for smaller buttons */
                border-radius: 3px; /* Rounded corners */
                text-align: center; /* Center the text */
                margin-right: 5px; /* Small margin between buttons */
            }

            /* Input styles inside buttons */
            .btn input[type="submit"] {
                background: none; /* Remove default input background */
                border: none; /* Remove default input border */
                color: white; /* White text color */
                font-size: 14px; /* Font size */
                cursor: pointer; /* Pointer cursor */
                padding: 5px 10px; /* Padding inside the input */
            }

            /* Edit button styles */
            .edit-btn {
                background-color: #4CAF50; /* Green background */
            }

            .edit-btn a:hover {
                background-color: #45a049; /* Darker green on hover */
            }

            /* Delete button styles */
            .delete-btn {
                background-color: #f44336; /* Red background */
            }

            .delete-btn a:hover {
                background-color: #da190b; /* Darker red on hover */
            }

            /* Optional: Adding some spacing between the buttons */
            div.btn {
                margin-right: 5px; /* Margin between buttons */
            }

            .filterFeedback {
                display: flex;
                justify-content: flex-start;
                padding: 1px;
            }

            .filter {
                padding: 10px 20px;
                text-decoration: none;
                color: #555;
                border: 1px solid #ccc;
                border-radius: 5px 5px 0 0;
                transition: background-color 0.3s ease, color 0.3s ease;
            }

            .filter:hover {
                background-color: #e0e0e0;
                color: #333;
            }

            .filter.active {
                background-color: #f44336;
                color: white;
                border-color: #f44336;
            }
        </style>
    </head>
    <svg style="display: none;">
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-fill" viewBox="0 0 24 24">
        <path fill="currentColor"
              d="M9.153 5.408C10.42 3.136 11.053 2 12 2c.947 0 1.58 1.136 2.847 3.408l.328.588c.36.646.54.969.82 1.182c.28.213.63.292 1.33.45l.636.144c2.46.557 3.689.835 3.982 1.776c.292.94-.546 1.921-2.223 3.882l-.434.507c-.476.557-.715.836-.822 1.18c-.107.345-.071.717.001 1.46l.066.677c.253 2.617.38 3.925-.386 4.506c-.766.582-1.918.051-4.22-1.009l-.597-.274c-.654-.302-.981-.452-1.328-.452c-.347 0-.674.15-1.328.452l-.596.274c-2.303 1.06-3.455 1.59-4.22 1.01c-.767-.582-.64-1.89-.387-4.507l.066-.676c.072-.744.108-1.116 0-1.46c-.106-.345-.345-.624-.821-1.18l-.434-.508c-1.677-1.96-2.515-2.941-2.223-3.882c.293-.941 1.523-1.22 3.983-1.776l.636-.144c.699-.158 1.048-.237 1.329-.45c.28-.213.46-.536.82-1.182z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-empty" viewBox="0 0 16 16">
        <path
            d="M2.866 14.85c-.078.444.36.791.746.593l4.39-2.256 4.389 2.256c.386.198.824-.149.746-.592l-.83-4.73 3.522-3.356c.33-.314.16-.888-.282-.95l-4.898-.696L8.465.792a.513.513 0 0 0-.927 0L5.354 5.12l-4.898.696c-.441.062-.612.636-.283.95l3.523 3.356-.83 4.73zm4.905-2.767-3.686 1.894.694-3.957a.565.565 0 0 0-.163-.505L1.71 6.745l4.052-.576a.525.525 0 0 0 .393-.288L8 2.223l1.847 3.658a.525.525 0 0 0 .393.288l4.052.575-2.906 2.77a.565.565 0 0 0-.163.506l.694 3.957-3.686-1.894a.503.503 0 0 0-.461 0z" />
    </symbol>
    <symbol xmlns="http://www.w3.org/2000/svg" id="star-half" viewBox="0 0 16 16">
        <path
            d="M5.354 5.119 7.538.792A.516.516 0 0 1 8 .5c.183 0 .366.097.465.292l2.184 4.327 4.898.696A.537.537 0 0 1 16 6.32a.548.548 0 0 1-.17.445l-3.523 3.356.83 4.73c.078.443-.36.79-.746.592L8 13.187l-4.389 2.256a.52.52 0 0 1-.146.05c-.342.06-.668-.254-.6-.642l.83-4.73L.173 6.765a.55.55 0 0 1-.172-.403.58.58 0 0 1 .085-.302.513.513 0 0 1 .37-.245l4.898-.696zM8 12.027a.5.5 0 0 1 .232.056l3.686 1.894-.694-3.957a.565.565 0 0 1 .162-.505l2.907-2.77-4.052-.576a.525.525 0 0 1-.393-.288L8.001 2.223 8 2.226v9.8z" />
    </symbol>
    </svg>

    <body class="crm_body_bg">

        <jsp:include page="../../common/header_dashboard.jsp"></jsp:include>
            <div class="main_content_iner ">
                <div class="container-fluid p-0">
                    <div class="row justify-content-center">
                        <div class="col-lg-12">
                            <div class="white_card card_height_100 mb_30">
                                <div class="white_card_header">
                                    <div class="box_header m-0">
                                        <div class="main-title">
                                            <!--<h3 class="m-0">Sản phẩm</h3>-->
                                        </div>
                                    </div>
                                </div>
                                <div class="white_card_body">
                                    <div class="QA_section">
                                        <div class="white_box_tittle list_header">
                                            <div>
                                                <!--<h4>Đánh giá sản phẩm</h4>-->
                                                <label for="filter">Đánh giá</label>
                                                <select name="filter">
                                                    <option value="value">5 Sao</option>
                                                    <option value="value">4 Sao</option>
                                                    <option value="value">3 Sao</option>
                                                    <option value="value">2 Sao</option>
                                                    <option value="value">1 Sao</option>
                                                </select>
                                                
                                            </div>
                                            <div class="box_right d-flex lms_block">
                                                <div class="serach_field_2">
                                                    <div class="search_inner">
                                                        <form action="feedbacklist" method="GET">
                                                            <div class="search_field">
                                                                <input name="search" type="text" placeholder="Tìm kiếm....">
                                                            </div>
                                                            <button type="submit"> <img src="img/icon/icon_search.svg" alt> </button>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="QA_table mb_30">
                                            <div class="filterFeedback">
                                                <a href="feedbacklist?page=1&status=pending" class="filter  ${requestScope.status eq 'pending' ?'active':''}">Chờ duyệt</a>
                                            <a href="feedbacklist?page=1&status=accept" class="filter  ${requestScope.status eq 'accept' ?'active':''}" style="margin-left: 10px" >Đang hiển thị</a>
                                            <a href="feedbacklist?page=1&status=reject" class="filter  ${requestScope.status eq 'reject' ?'active':''}" style="margin-left: 10px" >Bị ẩn</a>
                                            <c:if test="${requestScope.status eq 'pending'}">
                                                <div style="margin-left: 30rem">
                                                    <input type="button" value="Chấp nhận tất cả" />
                                                    <input type="button" value="Từ chối tất cả" />
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.status eq 'accept'}">
                                                <div style="margin-left: 30rem">
                                                    <input type="button" value="Ẩn tất cả" />
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.status eq 'reject'}">
                                                <div style="margin-left: 30rem">
                                                    <input type="button" value="Hiển thị tất cả" />
                                                    <input type="button" value="Xóa tất cả" />
                                                </div>
                                            </c:if>
                                        </div>
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th scope="col" style="width: 10px;padding: 5px">STT</th>
                                                    <th scope="col">Tên tài khoản</th>
                                                    <th scope="col" style="width: 7px">Đánh giá</th>
                                                    <th scope="col" style="width: 300px">Bình luận</th>
                                                    <th scope="col">Sản phẩm</th>
                                                    <th scope="col">Ngày</th>
                                                    <th scope="col">Trạng thái</th>
                                                    <th scope="col"> </th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${requestScope.listFeedback}" var="feedback" varStatus="loop">
                                                    <tr>
                                                        <td>${loop.index+1}</td>
                                                        <td>${feedback.account.userName}</td>
                                                        <td>
                                                            <div class="review-content d-flex">
                                                                <div class="rating text-primary">
                                                                    <c:forEach begin="1" end="${feedback.rating}">
                                                                        <svg class="star star-fill">
                                                                        <use xlink:href="#star-fill" style="color: gold"></use>
                                                                        </svg>
                                                                    </c:forEach > 
                                                                    <c:forEach begin="1" end="${5-feedback.rating}">
                                                                        <svg class="star star-empty">
                                                                        <use xlink:href="#star-empty" style="stroke: gold"></use>
                                                                        </svg>
                                                                    </c:forEach> 
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>${feedback.comments}</td>
                                                        <td><img src="${feedback.product.imgProduct}" width="auto" height="110px" alt="alt"/></td>
                                                        <td>${feedback.feedbackDate}</td>
                                                        <c:if test="${feedback.status == 1}"><td class="status-approved">Đã duyệt</td></c:if>
                                                        <c:if test="${feedback.status == 0}"><td class="status-pending">Chờ duyệt</td></c:if>
                                                        <c:if test="${feedback.status == -1}"><td class="status-rejected">Bị ẩn</td></c:if>

                                                            <td>
                                                            <c:choose>
                                                                <c:when test="${feedback.status == 0}">
                                                                    <div class="btn edit-btn">
                                                                        <a href="#"><i class="fas fa-check" style="color: white"></i></a>
                                                                    </div>
                                                                    <div class="btn delete-btn">
                                                                        <a href="#"><i class="fas fa-ban" style="color: white"></i></a>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:if test="${feedback.status == 1}">
                                                                        <div class="btn edit-btn">
                                                                            <a href="#"><i class="fas fa-eye-slash" style="color: white"></i></a>
                                                                        </div>
                                                                    </c:if>
                                                                    <c:if test="${feedback.status == -1}">
                                                                        <div class="btn edit-btn">
                                                                            <a href="#"><i class="fas fa-eye" style="color: white"></i></a>
                                                                        </div>
                                                                        <div class="btn delete-btn">
                                                                            <a href="#"><i class="fas fa-trash-alt" style="color: white"></i></a>
                                                                        </div>
                                                                    </c:if>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                    <!--endPage,page,listFeedback-->
                                    <c:set var="total" value="${requestScope.endPage}" />
                                    <c:set var="page" value="${requestScope.page}" />
                                    <c:if test="${requestScope.listFeedback != null}">
                                        <nav class="pt-5 border-top" aria-label="Page navigation">
                                            <ul class="pagination justify-content-center gap-5">
                                                <li class="page-item">
                                                    <c:if test="${page > 1}">
                                                        <a class="page-link" href="feedbacklist?page=${page-1}" >   <   </a>
                                                    </c:if>
                                                </li>
                                                <c:if test="${total <= 5}">
                                                    <c:forEach begin="1" end="${total}" var="pageNum">
                                                        <li class="page-item ${page == pageNum ? 'active' : ''}">
                                                            <a class="page-link" href="feedbacklist?page=${pageNum}" ${page == pageNum ? 'style="background-color: #2d1967; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${total > 5}">
                                                    <li class="page-item ${page == 1 ? 'active' : ''}">
                                                        <a class="page-link" href="feedbacklist?page=1"  ${page == 1 ? 'style="background-color: #2d1967; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>1</a>
                                                    </li>
                                                    <c:if test="${page > 3}">
                                                        <li class="page-item disabled">
                                                            <span class="page-link">...</span>
                                                        </li>
                                                    </c:if>
                                                    <c:forEach begin="${page > 2 ? page - 1 : 2}" end="${page < total - 1 ? page + 1 : total - 1}" var="pageNum">
                                                        <li class="page-item ${page == pageNum ? 'active' : ''}">
                                                            <a class="page-link" href="feedbacklist?page=${pageNum}"  ${page == pageNum ? 'style="background-color: #2d1967; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${pageNum}</a>
                                                        </li>
                                                    </c:forEach>
                                                    <c:if test="${page < total - 2}">
                                                        <li class="page-item disabled">
                                                            <span class="page-link">...</span>
                                                        </li>
                                                    </c:if>
                                                    <li class="page-item ${page == total ? 'active' : ''}">
                                                        <a class="page-link" href="feedbacklist?page=${pageNum}" ${page == total ? 'style="background-color: #2d1967; padding: 8px 16px; border-radius: 10px; color: white"' : ''}>${total}</a>
                                                    </li>
                                                </c:if>
                                                <li class="page-item">
                                                    <c:if test="${page < total}">
                                                        <a class="page-link" href="feedbacklist?page=${page+1}" > > </a>
                                                    </c:if>
                                                </li>
                                            </ul>
                                        </nav>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                    </div>
                </div>
            </div>
        </div>
    </section>








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
