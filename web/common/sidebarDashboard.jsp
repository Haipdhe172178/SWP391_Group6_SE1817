<%-- 
    Document   : sidebarDashboard
    Created on : Jun 28, 2024, 9:20:11 PM
    Author     : Hai Pham
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:choose>
    <c:when test="${sessionScope.account.roleId == 1}">
        <nav class="sidebar vertical-scroll  ps-container ps-theme-default ps-active-y">
            <div class="logo d-flex justify-content-between">
                <a href="dash"><img src="images/anh456.png" alt></a>
                <div class="sidebar_close_icon d-lg-none">
                    <i class="ti-close"></i>
                </div>
            </div>
            <ul id="sidebar_menu">
                <li class="mm-active">
                    <a class="has-arrow" href="dash" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/dashboard.svg" alt>
                        </div>
                        <span>Thống kê</span>
                    </a>                  
                </li>
                 <li class>
                    <a class="has-arrow" href="#" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/19.svg" alt>
                        </div>
                       <span>Thống kê số liệu</span>
                    </a>
                    <ul>
                         <li><a href="statisticsorder">Thống kê theo năm</a></li>   
                        <li><a href="statistics">Thống kê theo tháng</a></li>                       
                    </ul>
                </li>
                <li class>
                    <a class="has-arrow" href="contactAdmin" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/2.svg" alt>
                        </div>
                        <span>Liên hệ</span>
                    </a>
                </li>
                <li class>
                    <a class="has-arrow" href="discount" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/8.svg" alt>
                        </div>
                        <span>Mã giảm giá</span>
                    </a>
                   
                </li>       
                
                <li class>
                    <a class="has-arrow" href="#" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/11.svg" alt>
                        </div>
                        <span>Quản lý sản phẩm</span>
                    </a>
                    <ul>
                        <li><a href="data">Sản Phẩm</a></li>
                        <li><a href="category">Thể Loại</a></li>                    
                        <li><a href="author">Tác Giả</a></li>
                    </ul>
                </li>
                <li class>
                    <a class="has-arrow" href="upnews" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/18.svg" alt>
                        </div>
                        <span>Quản lý tin tức</span>
                    </a>
                </li>
                <li class>
                    <a class="has-arrow" href="#" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/17.svg" alt>
                        </div>
                        <span>Quản lý tài khoản</span>
                    </a>
                    <ul>
                        <li><a href="account">Người Dùng</a></li>
                        <li><a href="manages">Nhân Viên</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <nav class="sidebar vertical-scroll  ps-container ps-theme-default ps-active-y">
            <div class="logo d-flex justify-content-between">
                <a href="staffdashboard"><img src="images/anh456.png" alt></a>
                <div class="sidebar_close_icon d-lg-none">
                    <i class="ti-close"></i>
                </div>
            </div>
            <ul id="sidebar_menu">
                <li class>
                    <a class="has-arrow" href="staffdashboard?index=1" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/8.svg" alt>
                        </div>
                        <span>Đơn đặt hàng</span>
                    </a>
                </li>        
                <li class>
                    <a class="has-arrow" href="feedbacklist" aria-expanded="false">
                        <div class="icon_menu">
                            <img src="img/menu-icon/11.svg" alt>
                        </div>
                        <span>Đánh giá từ khách hàng</span>
                    </a>
                </li>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>
