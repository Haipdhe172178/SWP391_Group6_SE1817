<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <style type="text/css">
        .form-gap {
            padding-top: 70px;
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
                            <h2 class="text-center">Reset Password</h2>
                            <c:if test="${not empty status}">
                                <div class="alert alert-info">
                                    <c:choose>
                                        <c:when test="${status == 'resetSuccess'}">
                                            Password reset successfully. You can now log in with your new password.
                                        </c:when>
                                        <c:when test="${status == 'resetFailed'}">
                                            Password reset failed. Please try again.
                                        </c:when>
                                        <c:when test="${status == 'passwordMismatch'}">
                                            Passwords do not match. Please try again.
                                        </c:when>
                                    </c:choose>
                                </div>
                            </c:if>
                            <div class="panel-body">
                                <form id="reset-form" action="newPassword" role="form" autocomplete="off" class="form" method="post">
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-key color-blue"></i></span>
                                            <input type="password" name="password" placeholder="New Password" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="fa fa-key color-blue"></i></span>
                                            <input type="password" name="confPassword" placeholder="Confirm New Password" class="form-control" required="required">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset" type="submit">
                                    </div>
                                </form>
                            </div>
                            <h5>Don't have an Account? <a href="#" class="text-danger">Register Now!</a></h5>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
