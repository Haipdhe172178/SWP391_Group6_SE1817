
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container-fluid g-0">
    <div class="row">
        <div class="col-lg-12 p-0">
            <div class="header_iner d-flex justify-content-between align-items-center">
                <div class="sidebar_icon d-lg-none">
                    <i class="ti-menu"></i>
                </div>
                <div class="serach_field-area d-flex align-items-center">
                </div>
                <div class="header_left d-flex justify-content-between align-items-center">
                    <div class="profile_info">
                        <img src="${sessionScope.account.imgAccount}">
                        <div class="profile_info_iner">
                            <div class="profile_author_name">
                                <p>${sessionScope.account.roleId == 1 ? 'Admin' : 'Nhân viên'}</p>
                                <h5>${sessionScope.account.fullName}</h5>
                            </div>
                            <div class="profile_info_details">
                                <a href="logout">Đăng xuất</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>