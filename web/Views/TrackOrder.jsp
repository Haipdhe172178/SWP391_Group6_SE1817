<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Tracker Bootstrap Template</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
</head>


    <div class="container h-50 px-4 py-5 mx-auto">
        <div class="card bg-light shadow-lg border border-dark rounded-lg py-3 px-5 my-5">
            <div class="row d-flex justify-content-between mx-5 pt-3 my-3">
                <div class="container text-center">
                    <h3 style="color: #f86d72"> Theo dõi đơn hàng </h3>
                </div>
                
                <div class="d-flex">
                    <p class="h5 text-dark">
                        <i class="text-primary fa-solid fa-cart-shopping fa-lg mr-1"></i>
                        Mã đơn hàng : 
                        <span class="text-success font-weight-bold">
                            <i class="text-secondary fa-solid fa-hashtag mr-1"></i>${order.orderDetails[0].orderCId}
                        </span>
                    </p>
                </div>
                <div class="d-flex flex-column text-sm-right h5">
                    <p class="mb-0 font-weight-bolder text-monospace">
                        Ngày đặt hàng:
                        <span class="badge badge-pill badge-primary border border-secondary text-light font-weight-bold px-2 py-2 shadow" >
                            <script>
                                var orderDate = new Date('${requestScope.order.date}');
                                var formattedDate = orderDate.getDate() + '-' + (orderDate.getMonth() + 1) + '-' + orderDate.getFullYear();
                                document.write(formattedDate);
                            </script>
                        </span>
                    </p>
                    <p class="font-weight-bold text-monospace pt-3 ml-5">
                        Tổng tiền:
                        <span class="badge badge-pill badge-danger border border-secondary text-light font-weight-bold mx-1 px-2 py-2 shadow">
                            <fmt:formatNumber value="${requestScope.order.totalPrice} " type="number" minFractionDigits="0" maxFractionDigits="0" /> VND
                        </span>
                    </p>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="container-fluid p-2 align-items-center">
                            <div class="d-flex justify-content-around">
                                <c:set var="statusId" value="${statusOrder.statusId}" />
                                
                                <button class="btn <c:if test="${statusId >= 1}">bg-success</c:if><c:if test="${statusId < 1}">bg-secondary</c:if> text-white rounded-circle" 
                                        data-bs-toggle="tooltip" title="Order Confirmed">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="<c:if test="${statusId > 1}">bg-success</c:if><c:if test="${statusId <= 1}">bg-secondary</c:if> w-50 p-1 mx-n1 rounded mt-auto mb-auto"></span>
                                
                                <button class="btn <c:if test="${statusId >= 2}">bg-success</c:if><c:if test="${statusId < 2}">bg-secondary</c:if> text-white rounded-circle" 
                                        data-bs-toggle="tooltip" title="Order Shipped">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="<c:if test="${statusId > 2}">bg-success</c:if><c:if test="${statusId <= 2}">bg-secondary</c:if> w-50 p-1 mx-n1 rounded mt-auto mb-auto"></span>
                                
                                <button class="btn <c:if test="${statusId >= 3}">bg-success</c:if><c:if test="${statusId < 3}">bg-secondary</c:if> text-white rounded-circle" 
                                        data-bs-toggle="tooltip" title="Out for delivery">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="<c:if test="${statusId > 3}">bg-success</c:if><c:if test="${statusId <= 3}">bg-secondary</c:if> w-50 p-1 mx-n1 rounded mt-auto mb-auto"></span>
                                
                                <button class="btn <c:if test="${statusId >= 4}">bg-success</c:if><c:if test="${statusId < 4}">bg-secondary</c:if> text-white rounded-circle" 
                                        data-bs-toggle="tooltip" title="Order Delivered">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row d-inline-flex justify-content-around my-3 py-4 mx-n2">
                <div class="row d-inline-flex align-items-center">
                    <i class="text-primary fa-solid fa-clipboard-check fa-2xl mx-4 mb-3"></i>
                    <p class="text-dark font-weight-bolder py-1 px-1 mx-n2">Đã đặt đơn hàng</p>
                </div>
                <div class="row d-inline-flex align-items-center">
                    <i class="text-warning fa-solid fa-solid fa-boxes-packing fa-2xl mx-4 mb-3"></i>
                    <p class="text-dark font-weight-bolder py-1 px-1 mx-n2">Đã xác nhận</p>
                </div>
                <div class="row d-inline-flex align-items-center">
                    <i class="text-info fa-solid fa-truck-arrow-right fa-2xl mx-4 mb-3"></i>
                    <p class="text-dark font-weight-bolder py-1 px-1 mx-n2">Đang vận chuyển</p>
                </div>
                <div class="row d-inline-flex align-items-center">
                    <i class="text-success fa-solid fa-house-chimney fa-2xl mx-4 mb-3"></i>
                    <p class="text-dark font-weight-bolder py-1 px-1 mx-n2">Giao hàng thành công</p>
                </div>
                <div class="container text-center">
                    <h3 style="color: #f86d72"> Đơn Hàng </h3>
                </div>
                <table class="table">
                    <thead>
                            <th>Sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Đơn giá</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="detail" items="${orderDetails}">
                            <tr>
                                <td>
                                    <img src="${detail.product.imgProduct}" alt="${detail.product.name}" width="50" height="50" />
                                    ${detail.product.name}
                                </td>
                                <td>${detail.quantity}</td>
                                <td>
                                    <fmt:formatNumber value="${detail.unitPrice}" type="number" minFractionDigits="0" maxFractionDigits="0" /> VND
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pzjw8f+ua7Kw1TIq/T6P03TEiu6p0knwIbO8Hf2zUlfFscrIV+8zOJ5C1qI0d+Yd"
            crossorigin="anonymous"></script>
</body>

</html>
