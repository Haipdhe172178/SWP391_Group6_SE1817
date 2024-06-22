<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đặt lại mật khẩu</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <style type="text/css">
        .form-gap {
            padding-top: 70px;
        }
        .form-label {
            font-weight: bold;
            margin-bottom: 5px;
              text-align: left; /* Để căn lề trái */
            display: block;
        }
        .input-group-addon {
            min-width: 42px;
            text-align: center;
        }
    </style>
    <script>
        $(document).ready(function(){
            $('#reset-form').on('submit', function(event) {
                var password = $('input[name="password"]').val();
                var confPassword = $('input[name="confPassword"]').val();
                var regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;

                if (!regex.test(password)) {
                    event.preventDefault();
                    alert('Mật khẩu phải bao gồm ít nhất 8 ký tự, chứa cả chữ cái và chữ số.');
                    return false;
                }

                if (password !== confPassword) {
                    event.preventDefault();
                    alert('Mật khẩu và mật khẩu xác nhận không khớp.');
                    return false;
                }
            });
        });
    </script>
</head>
<body class="bg-info">
    <div class="form-gap"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">
                            <h3><i class="fa fa-lock fa-4x"></i></h3>
                            <h2 class="text-center">Đặt lại mật khẩu</h2>
                            <c:if test="${not empty status}">
                                <div class="alert alert-info">
                                    <c:choose>
                                        <c:when test="${status == 'resetSuccess'}">
                                            Đặt lại mật khẩu thành công. Bây giờ bạn có thể đăng nhập bằng mật khẩu mới của mình.
                                        </c:when>
                                        <c:when test="${status == 'resetFailed'}">
                                            Đặt lại mật khẩu không thành công. Vui lòng thử lại.
                                        </c:when>
                                        <c:when test="${status == 'passwordMismatch'}">
                                            Mật khẩu không khớp. Vui lòng thử lại.
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:if>
                            <div class="panel-body">
                                <form id="reset-form" action="newPassword" role="form" autocomplete="off" class="form" method="post">
                                    <div class="form-group">
                                        <label for="password" class="form-label">Nhập mật khẩu: </label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-key color-blue"></i></span>
                                            <input type="password" id="password" name="password" placeholder="Mật khẩu mới" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="confPassword" class="form-label">Xác nhận lại mật khẩu: </label>
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-key color-blue"></i></span>
                                            <input type="password" id="confPassword" name="confPassword" placeholder="Nhập lại mật khẩu mới" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Đặt mật khẩu" type="submit">
                                    </div>
                                </form>
                            </div>
                            <h5>Bạn chưa có tài khoản? <a href="register" class="text-danger">Đăng ký!</a></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
