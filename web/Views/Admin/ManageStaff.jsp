<%-- 
    Document   : Account
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
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

        <style>

            .active-row {
                opacity: 1;
            }

            .inactive-row {
                opacity: 0.5;
            }

        </style>
    </head>

    <body class="crm_body_bg">

        <jsp:include page="../../common/sidebarDashboard.jsp"></jsp:include>

            <section class="main_content dashboard_part large_header_bg">

            <jsp:include page="../../common/headerDashboard.jsp"></jsp:include>

                <div class="main_content_iner ">
                    <div class="container-fluid p-0">
                        <div class="row justify-content-center">
                            <div class="col-lg-12">
                                <div class="white_card card_height_100 mb_30">
                                    <div class="white_card_header">
                                        <div class="box_header m-0">
                                            <div class="main-title">
                                                <h3 class="m-0">Tài Khoản</h3>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="white_card_body">
                                        <div class="QA_section">
                                            <div class="white_box_tittle list_header">
                                                <h4></h4>
                                                <div class="box_right d-flex lms_block">
                                                    <div class="serach_field_2">
                                                        <div class="search_inner">
                                                            <form action="manages" method="GET">
                                                                <div class="search_field">
                                                                    <input name="s" type="text" placeholder="Tìm kiếm....">
                                                                </div>
                                                                <button type="submit"> <img src="img/icon/icon_search.svg" alt> </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                    <div class="add_button ms-2">
                                                        <a href="addac" class="btn_1">Thêm quản lý</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="QA_table mb_30">
                                                <form action="manages" method="Post" id="sortForm">
                                                    <input type="hidden" name="statusFilter" id="statusFilter" value="${param.statusFilter}">
                                            </form>
                                            <table class="table lms_table_active">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">ID</th>
                                                        <th scope="col">Tên</th>
                                                        <th scope="col">Vai trò</th>
                                                        <th scope="col">
                                                            <div class="dropdown">
                                                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="color:#57ccb7">
                                                                    Trạng thái
                                                                    <i class=""></i>
                                                                </a>
                                                                <div class="dropdown-menu">
                                                                    <a class="dropdown-item" href="manages">All</a>
                                                                    <a class="dropdown-item" href="manages?statusFilter=1">Active</a>
                                                                    <a class="dropdown-item" href="manages?statusFilter=0">Inactive</a>
                                                                </div>
                                                            </div>
                                                        </th>
                                                        <th scope="col">Hành Động</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${account}" var="ac">
                                                        <tr class="${ac.status == 1 ? 'active-row' : 'inactive-row'}">
                                                            <td>${ac.accountId}</td>
                                                            <td>${ac.fullName}</td>
                                                            <td>
                                                                <c:forEach items="${role}" var="ro">
                                                                    <c:if test="${ac.roleId == ro.roleId}">
                                                                        ${ro.roleName}
                                                                    </c:if>
                                                                </c:forEach>
                                                            </td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${ac.status == 1}">
                                                                        <span style="color: green;">Active</span>
                                                                    </c:when>
                                                                    <c:when test="${ac.status == 0}">
                                                                        <span style="color: red;">Inactive</span>
                                                                    </c:when>
                                                                </c:choose>
                                                            </td>
                                                            <td>
                                                                <a href="accdetail?id=${ac.accountId}" title="View"><i class="fas fa-eye"></i></a>
                                                                <a href="change?accountId=${ac.accountId}" title="Update"><i class="fas fa-edit"></i></a>
                                                                <a href="javascript:void(0);" title="Ẩn" onclick="return showConfirmModal('hide', ${ac.accountId}, 'staff');"><i class="fas fa-ban"></i></a>
                                                                <a href="javascript:void(0);" title="Hiện" onclick="return showConfirmModal('show', ${ac.accountId}, 'staff');"><i class="fas fa-check-circle"></i></a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <nav class="py-5" aria-label="Page navigation">
                                            <ul class="pagination justify-content-center gap-4">
                                                <!-- Xác định phạm vi các trang hiển thị -->
                                                <c:set var="start" value="${tag > 3 ? tag - 2 : 1}" />
                                                <c:set var="end" value="${tag > 3 ? tag + 2 : 5}" />
                                                <c:if test="${end > endP}">
                                                    <c:set var="end" value="${endP}" />
                                                    <c:set var="start" value="${endP - 4 > 0 ? endP - 4 : 1}" />
                                                </c:if>

                                                <!-- Nút Previous -->
                                                <c:if test="${tag > 1}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="manages?index=${tag - 1}${query}" aria-label="Previous">
                                                            <span aria-hidden="true">Previous</span>
                                                        </a>
                                                    </li>
                                                </c:if>

                                                <!-- Vòng lặp để tạo các nút trang -->
                                                <c:forEach begin="${start}" end="${end}" var="i">
                                                    <li class="page-item ${tag == i ? 'active' : ''}">
                                                        <a class="page-link" href="manages?index=${i}${query}">${i}</a>
                                                    </li>
                                                </c:forEach>

                                                <!-- Nút Next -->
                                                <c:if test="${tag < endP}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="manages?index=${tag + 1}${query}" aria-label="Next">
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
                        <div class="col-12">
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmationModalLabel">Xác nhận hành động</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <p id="confirmationMessage"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                            <button type="button" class="btn btn-primary" id="confirmButton">Xác nhận</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer_part">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="footer_iner text-center">
                                <p>2020 © Influence - Designed by <a href="#"> <i class="ti-heart"></i> </a><a href="#"> Dashboard</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <div class="CHAT_MESSAGE_POPUPBOX">
            <div class="CHAT_POPUP_HEADER">
                <div class="MSEESAGE_CHATBOX_CLOSE">
                    <svg width="12" height="12" viewBox="0 0 12 12" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M7.09939 5.98831L11.772 10.661C12.076 10.965 12.076 11.4564 11.772 11.7603C11.468 12.0643 10.9766 12.0643 10.6726 11.7603L5.99994 7.08762L1.32737 11.7603C1.02329 12.0643 0.532002 12.0643 0.228062 11.7603C-0.0760207 11.4564 -0.0760207 10.965 0.228062 10.661L4.90063 5.98831L0.228062 1.3156C-0.0760207 1.01166 -0.0760207 0.520226 0.228062 0.216286C0.379534 0.0646715 0.578697 -0.0114918 0.777717 -0.0114918C0.976738 -0.0114918 1.17576 0.0646715 1.32737 0.216286L5.99994 4.889L10.6726 0.216286C10.8243 0.0646715 11.0233 -0.0114918 11.2223 -0.0114918C11.4213 -0.0114918 11.6203 0.0646715 11.772 0.216286C12.076 0.520226 12.076 1.01166 11.772 1.3156L7.09939 5.98831Z" fill="white" />
                    </svg>
                </div>
                <h3>Chat with us</h3>
                <div class="Chat_Listed_member">
                    <ul>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/1.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/2.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/3.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/4.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <img src="img/staf/5.png" alt>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <div class="member_thumb">
                                    <div class="more_member_count">
                                        <span>90+</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="CHAT_POPUP_BODY">
                <p class="mesaged_send_date">
                    Sunday, 12 January
                </p>
                <div class="CHATING_SENDER">
                    <div class="SMS_thumb">
                        <img src="img/staf/1.png" alt>
                    </div>
                    <div class="SEND_SMS_VIEW">
                        <P>Hi! Welcome . How can I help you?</P>
                    </div>
                </div>
                <div class="CHATING_SENDER CHATING_RECEIVEr">
                    <div class="SEND_SMS_VIEW">
                        <P>Hello</P>
                    </div>
                    <div class="SMS_thumb">
                        <img src="img/staf/1.png" alt>
                    </div>
                </div>
            </div>
            <div class="CHAT_POPUP_BOTTOM">
                <div class="chat_input_box d-flex align-items-center">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Write your message" aria-label="Recipient's username" aria-describedby="basic-addon2">
                        <div class="input-group-append">
                            <button class="btn " type="button">

                                <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M18.7821 3.21895C14.4908 -1.07281 7.50882 -1.07281 3.2183 3.21792C-1.07304 7.50864 -1.07263 14.4908 3.21872 18.7824C7.50882 23.0729 14.4908 23.0729 18.7817 18.7815C23.0726 14.4908 23.0724 7.50906 18.7821 3.21895ZM17.5813 17.5815C13.9525 21.2103 8.04773 21.2108 4.41871 17.5819C0.78907 13.9525 0.789485 8.04714 4.41871 4.41832C8.04752 0.789719 13.9521 0.789304 17.5817 4.41874C21.2105 8.04755 21.2101 13.9529 17.5813 17.5815ZM6.89503 8.02162C6.89503 7.31138 7.47107 6.73534 8.18131 6.73534C8.89135 6.73534 9.46739 7.31117 9.46739 8.02162C9.46739 8.73228 8.89135 9.30811 8.18131 9.30811C7.47107 9.30811 6.89503 8.73228 6.89503 8.02162ZM12.7274 8.02162C12.7274 7.31138 13.3038 6.73534 14.0141 6.73534C14.7241 6.73534 15.3002 7.31117 15.3002 8.02162C15.3002 8.73228 14.7243 9.30811 14.0141 9.30811C13.3038 9.30811 12.7274 8.73228 12.7274 8.02162ZM15.7683 13.2898C14.9712 15.1332 13.1043 16.3243 11.0126 16.3243C8.8758 16.3243 6.99792 15.1272 6.22834 13.2744C6.09642 12.9573 6.24681 12.593 6.56438 12.4611C6.64238 12.4289 6.72328 12.4136 6.80293 12.4136C7.04687 12.4136 7.27836 12.5577 7.37772 12.7973C7.95376 14.1842 9.38048 15.0799 11.0126 15.0799C12.6077 15.0799 14.0261 14.1836 14.626 12.7959C14.7625 12.4804 15.1288 12.335 15.4441 12.4717C15.7594 12.6084 15.9048 12.9745 15.7683 13.2898Z" fill="#707DB7" />
                                </svg>

                            </button>
                            <button class="btn" type="button">

                                <svg width="22" height="22" viewBox="0 0 22 22" fill="none" xmlns="http://www.w3.org/2000/svg">
                                <path d="M11 0.289062C4.92455 0.289062 0 5.08402 0 10.9996C0 16.9152 4.92455 21.7101 11 21.7101C17.0755 21.7101 22 16.9145 22 10.9996C22 5.08472 17.0755 0.289062 11 0.289062ZM11 20.3713C5.68423 20.3713 1.375 16.1755 1.375 10.9996C1.375 5.82371 5.68423 1.62788 11 1.62788C16.3158 1.62788 20.625 5.82371 20.625 10.9996C20.625 16.1755 16.3158 20.3713 11 20.3713ZM15.125 10.3302H11.6875V6.98314C11.6875 6.61363 11.3795 6.31373 11 6.31373C10.6205 6.31373 10.3125 6.61363 10.3125 6.98314V10.3302H6.875C6.4955 10.3302 6.1875 10.6301 6.1875 10.9996C6.1875 11.3691 6.4955 11.669 6.875 11.669H10.3125V15.016C10.3125 15.3855 10.6205 15.6854 11 15.6854C11.3795 15.6854 11.6875 15.3855 11.6875 15.016V11.669H15.125C15.5045 11.669 15.8125 11.3691 15.8125 10.9996C15.8125 10.6301 15.5045 10.3302 15.125 10.3302Z" fill="#707DB7" />
                                </svg>

                                <input type="file">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

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
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script>
                                                                    function filterStatus(status) {
                                                                        const statusFilter = document.getElementById('statusFilter');
                                                                        statusFilter.value = status;
                                                                        document.getElementById('sortForm').submit();
                                                                    }
                                                                    var actionToPerform = '';
                                                                    var accountIdToUse = '';
                                                                    var typeToUse = '';

                                                                    function showConfirmModal(action, accountId, type) {
                                                                        actionToPerform = action;
                                                                        accountIdToUse = accountId;
                                                                        typeToUse = type;

                                                                        var message = action === 'hide' ? 'Bạn có chắc chắn muốn ẩn tài khoản này không?' : 'Bạn có chắc chắn muốn hiện tài khoản này không?';
                                                                        document.getElementById('confirmationMessage').textContent = message;

                                                                        $('#confirmationModal').modal('show');

                                                                        return false;
                                                                    }

                                                                    document.getElementById('confirmButton').onclick = function () {

                                                                        window.location.href = 'active?action=' + actionToPerform + 'acc&accountId=' + accountIdToUse + '&type=' + typeToUse;
                                                                    };
        </script>
    </body>

</html>
