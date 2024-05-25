<%-- 
    Document   : Profile
    Created on : May 25, 2024, 5:49:46 PM
    Author     : Hai Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <meta charset='utf-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <title>Profile</title>
        <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css' rel='stylesheet'>
        <link href='#' rel='stylesheet'>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="format-detection" content="telephone=no">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="author" content="">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap" rel="stylesheet">

        <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
        <style>

            .add-experience:hover {
                background: #f86d72;
                color: #fff;
                cursor: pointer;
                border: solid 1px #BA68C8
            }

            .profile{
                border-radius: 2em;
                box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.3),
                    -3px -3px 6px rgba(255, 255, 255, 0.5);
            }

        </style>
    </head>
    <jsp:include page="../common/header.jsp"></jsp:include>
        <body className='snippet-body'>
            <div class="container rounded bg-white mt-5 mb-5" style="width: 60%">
                <div class="row profile">
                    <div class="col-md-4 border-right">
                        <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">Edogaru</span><span class="text-black-50">edogaru@mail.com.my</span><span> </span></div>
                    </div>
                    <div class="col-md-8" >

                        <!-- Profile -->
                        <div class="p-3 py-5" id="profile">
                            <form action="profile" method="post">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="text-right">Profile Settings</h4>
                                    <span class="border px-3 p-1 add-experience"><i class="fa fa-plus"></i>&nbsp;Edit Profile</span>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-6 inputprofile"><label class="labels">Full Name</label><input type="text" class="form-control" placeholder="full name" value="" disabled></div>
                                    <div class="col-md-6 inputprofile"><label class="labels">User Name</label><input type="text" class="form-control" value="" placeholder="username" disabled></div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-6 inputprofile"><label class="labels">Gender</label><input type="text" class="form-control" placeholder="gender" value="" disabled></div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12 inputprofile"><label class="labels">Phone Number</label><input type="text" class="form-control" placeholder="enter phone number" value="" disabled></div>
                                    <div class="col-md-12 inputprofile"><label class="labels">Address</label><input type="text" class="form-control" placeholder="enter address" value="" disabled></div>
                                    <div class="col-md-12 "><label class="labels">Email</label><input type="text" class="form-control" placeholder="enter email" value="" disabled></div>
                                </div>
                                <div class="mt-5 text-center">
                                    <button class="btn btn-primary profile-button buttonprofile" type="submit" style="display: none">Save Profile</button>
                                    <button class="btn btn-secondary profile-button buttonprofile" type="button" style="display: none">Change Password</button>
                                </div>
                            </form>
                        </div>

                        <!-- Change Password -->
                        <div class="p-3 py-5" id="changepass"style="display: none">
                            <form action="changePassword" method="post">
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <h4 class="text-right">Change Password</h4>
                                </div>
                                <div class="row mt-2">
                                    <div class="col-md-12"><label class="labels">Current Password</label><input type="text" class="form-control" placeholder="enter current password" value="" ></div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12"><label class="labels">New Password</label><input type="text" class="form-control" placeholder="enter new password" value="" ></div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12"><label class="labels">Confirm New Password</label><input type="text" class="form-control" placeholder="confirm enter new password" value="" ></div>
                                </div>
                                <div class="mt-5 text-center">
                                    <button class="btn btn-primary profile-button" type="submit">Submit</button>
                                    <button class="btn btn-secondary profile-button" type="button">Cancel</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="../common/footer.jsp"></jsp:include>
<script type='text/javascript' src='https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js'></script>
<script type='text/javascript' src='#'></script>
<script type='text/javascript' src='#'></script>
<script type='text/javascript' src='#'></script>
<script type='text/javascript'>#</script>
<script type='text/javascript'>
    var myLink = document.querySelector('a[href="#"]');
    myLink.addEventListener('click', function (e) {
        e.preventDefault();
    });


</script>
<script>
    // Hàm để ẩn phần tử có id là "profile" và hiển thị phần tử có id là "changepass"
    function showChangePassword() {
        document.getElementById('profile').style.display = 'none';
        document.getElementById('changepass').style.display = '';
    }

    // Hàm để ẩn phần tử có id là "changepass" và hiển thị phần tử có id là "profile"
    function showProfile() {
        document.getElementById('profile').style.display = '';
        document.getElementById('changepass').style.display = 'none';
    }

    // Sự kiện click cho nút "Change Password"
    document.querySelector('.btn.btn-secondary.profile-button').addEventListener('click', function () {
        showChangePassword();
    });

    // Sự kiện click cho nút "Cancel" trong form "Change Password"
    document.querySelector('.p-3.py-5#changepass .btn.btn-secondary.profile-button').addEventListener('click', function () {
        showProfile();
    });
</script>
<script>
    // Sự kiện click cho nút "Edit Profile"
    document.querySelector('.add-experience').addEventListener('click', function () {
        // Lấy tất cả các phần tử có class là "inputprofile"
        const profileElements = document.querySelectorAll('.inputprofile input');

        // Duyệt qua từng phần tử và xóa thuộc tính disabled
        profileElements.forEach(function (element) {
            element.disabled = false;
        });

        // Hiển thị nút "Save Profile" và "Change Password"
        document.querySelectorAll('.buttonprofile').forEach(function (button) {
            button.style.display = '';
        });
    });
</script>


</body>
</html>
