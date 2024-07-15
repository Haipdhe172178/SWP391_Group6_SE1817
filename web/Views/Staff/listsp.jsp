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
                                        <h3 class="m-0">Sản phẩm</h3>
                                    </div>
                                </div>
                            </div>
                            <div class="white_card_body">
                                <div class="QA_section">
                                    <div class="white_box_tittle list_header">
                                     <button type="button" class="btn btn-primary">
                            <a href="neworder" style="color:white; text-decoration:none;">Tiếp tục mua hàng</a>
                                           </button>
                                        <div class="box_right d-flex lms_block">
                                              
                                             <div class="serach_field_2">
                                           
                                                <div class="search_inner">
                                                    <form action="listsp" method="GET">
                                                        <div class="search_field">
                                                            <input name="s" type="text" placeholder="Tìm kiếm....">
                                                        </div>
                                                        <button type="submit"> <img src="img/icon/icon_search.svg" alt> </button>
                                                    </form>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="QA_table mb_30">

                                        <table class="table lms_table_active ">
                                            <thead>
                                                <tr>
                                                    <th scope="col">ID</th>
                                                    <th scope="col">Tên</th>
                                                    <th scope="col">Giá</th>
                                                    <th scope="col">Số lượng</th>
                                                    <th scope="col">Mô tả sản phẩm</th>
                                                    <th scope="col">Thể loại</th>
                                                    <th scope="col">Tác giả</th>
                                                    <th scope="col">Ảnh</th>
                                                    <th scope="col">Độ tuổi</th>

                                                    <th scope="col">Hành động</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${product}" var="p">
                                                    <tr>
                                                        <td>${p.productId}</td>
                                                        <td>${p.name}</td>
                                                        <td>${p.price}</td>
                                                        <td>${p.quantity}</td>
                                                        <td>${fn:substring(p.description, 0, 50)}...</td>
                                                        <td>${p.category.categoryName}</td>
                                                        <td>${p.author.authorName}</td>
                                                        <td><img src="${p.imgProduct}" alt="Product Image" style="width:100px;height:auto;"></td>
                                                        <td>${p.oage.age}</td>

                                                        <td>
                                                            <a href="makeOrder?id=${p.productId}&amount=${p.quantity}" title="Add"><i class="fas fa-add"></i></a>

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
                                                    <a class="page-link" href="listsp?index=${tag - 1}${query}" aria-label="Previous">
                                                        <span aria-hidden="true">Previous</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <!-- Vòng lặp để tạo các nút trang -->
                                            <c:forEach begin="${start}" end="${end}" var="i">
                                                <li class="page-item ${tag == i ? 'active' : ''}">
                                                    <a class="page-link" href="listsp?index=${i}${query}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <!-- Nút Next -->
                                            <c:if test="${tag < endP}">
                                                <li class="page-item">
                                                    <a class="page-link" href="listsp?index=${tag + 1}${query}" aria-label="Next">
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
                <div class="col-12">
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
                    <path d="M7.09939 5.98831L11.772 10.661C12.076 10.965 12.076 11.4564 11.772 11.7603C11.468 12.0643 10.9766 12.0643 10.6726 11.7603L5.99994 7.08762L1.32737 11.7603C1.02329 12.0643 0.532002 12.0643 0.228062 11.7603C-0.0760207 11.4564 -0.0760207 10.965 0.228062 10.661L4.90063 5.98831L0.228062 1.3156C-0.0760207 1.01166 -0.0760207 0.520226 0.228062 0.216286C0.379534 0.0646715 0.578697 -0.0114918 0.777717 -0.0114918C0.976738 -0.0114918 1.17576 0.0646715 1.32737 0.216286L5.99994 4.889L10.6726 0.216286C10.8242 0.0646715 11.0233 -0.0114918 11.2223 -0.0114918C11.4213 -0.0114918 11.6205 0.0646715 11.772 0.216286C12.076 0.520226 12.076 1.01166 11.772 1.3156L7.09939 5.98831Z" fill="#4C5369" />
                </svg>
            </div>
            <div class="CHAT_POPUP_TITLE">
                <h4>Đây là một dự án thu hút sự chú ý</h4>
                <p>giá trị</p>
            </div>
        </div>
        <div class="CHAT_POPUP_MESSAGEBOX">
            <ul>
                <li>
                    <div class="MESSAGE_BOX_RIGHT">
                        <p> Mọi tinh hoa tinh hoa nhu cầu thông báo xây dựng kết nối hướng về 101 công phượng , cầu thủ Việt đá lựa chọn pháp sẽ thi đấu vòng chung sẽ dùng năm tiến cũng được qua tâm sàn lưới một số tin cậy được phép xây dựng chiếc xe của bài đấu này là về xây dựng người sẽ mình hầu lên sự cũng chức năng đóng góp và tổn tại mà nhiều khác biệt này.</p>
                    </div>
                </li>
                <li>
                    <div class="MESSAGE_BOX_LEFT">
                        <p>Một văn bản một sự sẽ được tối ưu hóa sự không còn là nổi phần sự giao tiếp đại học được dự đoán có phòng họp từ nghiệm quan trọng với đồng hồ vừa tìm được với những hạnh phúc của bạn có thể và phân phối cạnh tranh tuyển dụng của cuộc họp và với những điều dụng</p>
                    </div>
                </li>
                <li>
                    <div class="MESSAGE_BOX_RIGHT">
                        <p>Một lượng một dự án với một chính hình ảnh hồi âm về thương mại cũng là một nghiệm ngữ cảm ứng chân thành xem nhận xét đã xác minh với ý kiến từ từ biên soạn là nhân loại nhất để khám phá chúng ta cần thiết xây dựng xây dựng và sự việc cũng chính</p>
                    </div>
                </li>
                <li>
                    <div class="MESSAGE_BOX_LEFT">
                        <p> Là vụ việc đẹp xây dựng của lịch ưng thứ của bạn ở đây nội dung gì lý do nhiều lúc nào giữ tâm đặc cách của cảm ơn phúc mạng và cạnh cùng trong việc phát sinh ngành phổ biến. Khái niệm đã chạy cuộc sống để tôi mình sẽ khám</p>
                    </div>
                </li>
            </ul>
        </div>
        <div class="CHAT_POPUP_FOOTER">
            <input type="text" placeholder="Ngữ cảnh" />
            <button>Gửi</button>
        </div>
    </div>

    <script src="js/vendor/modernizr-3.11.2.min.js"></script>

    <script src="js/vendor/jquery-3.5.1.min.js"></script>

    <script src="js/popper.min.js"></script>

    <script src="js/bootstrap1.min.js"></script>

    <script src="js/plugins.js"></script>

    <script src="js/main.js"></script>
</body>

</html>
