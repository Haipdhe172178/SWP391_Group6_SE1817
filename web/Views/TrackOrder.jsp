
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" 
          content="IE=edge">
    <meta name="viewport" 
          content="width=device-width, initial-scale=1.0">
    <title>Order Tracker Bootstrap Template</title>

    <!-- Font-awesome CSS -->
    <link rel="stylesheet" href=
"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity=
"sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" 
          referrerpolicy="no-referrer" />

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href=
"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" 
          integrity=
"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" 
          crossorigin="anonymous">
</head>

<body class="d-flex flex-column overflow-auto
             h-100 bg-success text-dark">

    <div class="container h-50 px-4 
                py-5 mx-auto">
        <div class="card bg-light shadow-lg 
                    border border-dark 
                    rounded-lg py-3 px-5
                    my-5">
            <div class="row d-flex 
                        justify-content-between
                        mx-5 pt-3 my-3">
                <div class="container 
                            text-center">
                    <p class="h3 text-success
                              mb-3">
                     Theo dõi đơn hàng
                      </p>
                </div>
                <div class="d-flex">
                    <p class="h5 text-dark">
                      <i class="text-primary fa-solid
                                fa-cart-shopping 
                                fa-lg mr-1">
                      </i>
                        Mã đơn hàng : 
                      <span class="text-success 
                                   font-weight-bold">
                            <i class="text-secondary 
                                      fa-solid fa-hashtag
                                      mr-1">
                            </i>ZMJ82D9
                      </span>
                      </p>
                </div>
                <div class="d-flex flex-column 
                            text-sm-right h5">
                    <p class="mb-0 font-weight-bolder 
                              text-monospace">
                      Ngày đặt hàng:
                        <span class="badge badge-pill 
                                     badge-primary border
                                     border-secondary 
                                     text-light 
                                     font-weight-bold 
                                     px-2 py-2 shadow">
                          01/05/204
                          </span>
                    </p>
                    <p class="font-weight-bold 
                              text-monospace 
                              pt-3 ml-5">
                      Tổng tiền:
                        <span class="badge badge-pill 
                                     badge-danger border 
                                     border-secondary 
                                     text-light font-weight-bold
                                     mx-1 px-2 py-2 shadow">
                          23458039
                          </span>
                    </p>
                </div>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="container-fluid 
                                    p-2 align-items-center">
                            <div class="d-flex 
                                        justify-content-around">
                                <button class="btn bg-success 
                                               text-white 
                                               rounded-circle" 
                                        data-bs-toggle="tooltip"
                                        title="Order Confirmed">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="bg-success w-50 p-1
                                             mx-n1 rounded mt-auto
                                             mb-auto">
                                </span>
                                <button class="btn bg-success 
                                               text-white 
                                               rounded-circle" 
                                        data-bs-toggle="tooltip"
                                        title="Order Shipped">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="bg-success w-50 
                                             p-1 mx-n1 rounded
                                             mt-auto mb-auto">
                                </span>
                                <button class="btn bg-success 
                                               text-white 
                                               rounded-circle" 
                                        data-bs-toggle="tooltip"
                                        title="Out for delivery" 
                                        style="z-index:1">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                                <span class="bg-secondary w-50 p-1 
                                             mx-n1 rounded mt-auto 
                                             mb-auto">
                                </span>
                                <button class="btn bg-secondary 
                                               text-white rounded-circle" 
                                        data-bs-toggle="tooltip"
                                        title="Order Delivered">
                                    <i class="fa-solid fa-check"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row d-inline-flex 
                        justify-content-around 
                        my-3 py-4 mx-n2">
                <div class="row d-inline-flex 
                            align-items-center">
                    <i class="text-primary fa-solid 
                              fa-clipboard-check 
                              fa-2xl mx-4 mb-3">
                      </i>
                    <p class="text-dark font-weight-bolder 
                              py-1 px-1 mx-n2">
                      Đã đặt đơn hàng
                      </p>
                </div>
                <div class="row d-inline-flex
                            align-items-center">
                    <i class="text-warning fa-solid
                              fa-solid fa-boxes-packing
                              fa-2xl mx-4 mb-3">
                      </i>
                    <p class="text-dark  
                              font-weight-bolder
                              py-1 px-1 mx-n2">
                      Đã xác nhận
                      </p>
                </div>
                <div class="row d-inline-flex 
                            align-items-center">
                    <i class="text-info fa-solid 
                              fa-truck-arrow-right
                              fa-2xl mx-4 mb-3">
                      </i>
                    <p class="text-dark 
                              font-weight-bolder
                              py-1 px-1 mx-n2">
                      Đang vận chuyển
                      </p>
                </div>
                <div class="row d-inline-flex 
                            align-items-center">
                    <i class="text-success fa-solid
                              fa-house-chimney 
                              fa-2xl mx-4 mb-3">
                      </i>
                    <p class="text-dark font-weight-bolder
                              py-1 px-1 mx-n2">
                      Giao hàng thành công
                      </p>
                </div>
                <table class="table">
                <thead>
                <th style="color: #f86d72">Đơn hàng</th>
                    <tr>
                        <th>Sản phẩm</th>
                        <th>Số lượng</th>
                        <th>Đơn giá</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${orderDetails}">
                        <tr>
                            <td>${detail.orderCId}</td>
                            <td>
                                <a href="single?productID=${detail.product.productId}">
                                    <img src="${detail.product.imgProduct}" alt="${detail.product.name}" width="50" height="50" />
                                    ${detail.product.name}
                                </a>
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
</body>

</html>