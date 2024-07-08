<%-- 
    Document   : Checkout.jsp
    Created on : May 15, 2024, 11:09:07 PM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <title>Bookly - Bookstore eCommerce Website Template</title>
        <meta charset="utf-8">
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
        <script src="./js/jquery-1.11.3.min.js"></script>
        <style>
            .form-control{
                height: 60px;
                width: 100%;
            }
            .form-select-sm{
                font-size: 20px;
            }
            .address-container {
                display: flex;
                justify-content: space-between;
                align-items: center;
                gap: 10px;
            }
            .edit-link {
                margin-left: 4rem;
            }
            .add-address {
                display: flex;
                align-items: center;
                cursor: pointer;
                margin-top: 20px;
            }
            .add-address i {
                margin-right: 5px;
            }

        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <form id="formCheckout" action="processCheckout" method="post">
                <section class="checkout-wrap padding-large p-1">
                    <div class="container">
                        <div class="row d-flex flex-wrap">
                            <div class="col-lg-8">
                            <c:choose>
                                <c:when test="${sessionScope.account == null}">
                                    <!--for guest-->
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <h4 class="mb-3">Địa chỉ nhận hàng</h4>
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="fname">Họ và tên</label>
                                            <input type="text" id="fname" name="fullname" class="form-control mt-1 mb-4 ps-3" required pattern="[A-Za-zÀ-ỹà-ỹ\s]+" 
                                                   oninput="validateInput('name')">
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="city">Tỉnh thành</label>
                                            <select class="form-select form-select-sm form-control mt-1 mb-4" id="city" aria-label=".form-select-sm" name="city" required>
                                                <option value="" selected>Chọn tỉnh thành</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="email">Email</label>
                                            <input type="text" id="email" name="email" class="form-control mt-1 ps-3 mb-3" required oninput="validateInput('email')">
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="district">Quận huyện</label>
                                            <select class="form-select form-select-sm form-control mt-1 mb-4" id="district" aria-label=".form-select-sm" name="district" required>
                                                <option value="" selected>Chọn quận huyện</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="phone">Số điện thoại</label>
                                            <input type="text" id="phone" name="phone" class="form-control mt-1 ps-3 mb-4" required oninput="validateInput('phone')">
                                        </div>
                                        <div class="col-lg-6">
                                            <label for="ward">Phường xã</label>
                                            <select class="form-select form-select-sm form-control mt-1 mb-4" id="ward" aria-label=".form-select-sm" name="ward" required>
                                                <option value="" selected>Chọn phường xã</option>
                                            </select>
                                        </div>
                                        <div class="col-lg-12">
                                            <label for="address">Địa chỉ</label>
                                            <input type="text" id="address" name="address" class="form-control mt-1 ps-3 mb-4" required oninput="validateInput('address')">
                                        </div>
                                        <input type="hidden" id="fulladdress" name="fulladdress" class="form-control mt-1 ps-3 mb-4" readonly>
                                    </div>

                                    <script>
                                        function updateFullAddressGuest() {
                                            var city = document.getElementById('city').options[document.getElementById('city').selectedIndex].text || '';
                                            var district = document.getElementById('district').options[document.getElementById('district').selectedIndex].text || '';
                                            var ward = document.getElementById('ward').options[document.getElementById('ward').selectedIndex].text || '';
                                            var detailedAddress = document.getElementById('address').value.trim();

                                            var fullAddress = [detailedAddress, ward, district, city].filter(Boolean).join(', ');
                                            document.getElementById('fulladdress').value = fullAddress;
                                        }

                                        document.getElementById('ward').addEventListener('change', updateFullAddressGuest);
                                        document.getElementById('address').addEventListener('input', updateFullAddressGuest);

                                        // Kiểm tra hợp lệ giá trị nhập vào
                                        function validateInput(input) {
                                            switch (input) {
                                                case 'name':
                                                    var fullNameInput = document.getElementById('fname');
                                                    var fullName = fullNameInput.value.trim();

                                                    if (fullName.length === 0) {
                                                        fullNameInput.setCustomValidity('Vui lòng điền đầy đủ họ và tên');
                                                    } else {
                                                        fullNameInput.setCustomValidity('');
                                                    }
                                                    break;
                                                case 'email':
                                                    var emailInput = document.getElementById('email');
                                                    var email = emailInput.value.trim();
                                                    var pattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

                                                    if (email.length === 0) {
                                                        emailInput.setCustomValidity('Vui lòng điền email');
                                                    } else if (!pattern.test(email)) {
                                                        emailInput.setCustomValidity('Địa chỉ email không hợp lệ');
                                                    } else {
                                                        emailInput.setCustomValidity('');
                                                    }
                                                    break;
                                                case 'phone':
                                                    var phoneInput = document.getElementById('phone');
                                                    var phone = phoneInput.value.trim();
                                                    var pattern = /^(0|\+84)(\d{9})$/;
                                                    if (phone.length === 0) {
                                                        phoneInput.setCustomValidity('Vui lòng điền số điện thoại');
                                                    } else if (!pattern.test(phone)) {
                                                        phoneInput.setCustomValidity('Số điện thoại không hợp lệ');
                                                    } else {
                                                        phoneInput.setCustomValidity('');
                                                    }
                                                    break;
                                                case 'address':
                                                    var addressInput = document.getElementById('address');
                                                    var address = addressInput.value.trim();
                                                    var pattern = /^[A-Za-zÀ-ỹà-ỹ0-9\s,.'-]{3,}$/;

                                                    if (address.length === 0) {
                                                        addressInput.setCustomValidity('Vui lòng điền địa chỉ');
                                                    } else if (!pattern.test(address)) {
                                                        addressInput.setCustomValidity('Địa chỉ không hợp lệ');
                                                    } else {
                                                        addressInput.setCustomValidity('');
                                                    }
                                                    break;
                                                default:
                                                    break;
                                            }
                                        }
                                    </script>

                                    <!--for guest-->
                                </c:when>
                                <c:otherwise>
                                    <!--for customer login with account-->
                                    <div class="container">
                                        <div class="col-lg-12 border-bottom">
                                            <h4 class="mb-3">Địa chỉ nhận hàng</h4>
                                        </div>
                                        <div id="addressShipping" style="max-height: 300px;
                                             overflow-y: auto;
                                             overflow-x: hidden;">
                                            <c:forEach items="${requestScope.listAddress}" var="a">
                                                <label class="list-group-item d-flex align-items-center gap-2 border-0 mt-3">
                                                    <input class="form-check-input flex-shrink-0" type="radio" name="address" value="${a.addressID}" required>
                                                    <div class="d-flex flex-grow-1 justify-content-between">
                                                        <p class="mb-1 address-container">${a.address} | ${a.phoneNumber}</p>
                                                        <a href="#" class="edit-link" data-bs-toggle="modal" data-bs-target="#addressModal" data-title="Sửa địa chỉ" onclick="openUpdateModal(${a.addressID}, '${a.address}', '${a.phoneNumber}')">Sửa</a>
                                                        <input type="hidden" id="phoneValue" value="${a.phoneNumber}">
                                                    </div>
                                                </label>
                                            </c:forEach>                                      
                                        </div>
                                        <div class="add-address">
                                            <i class="fas fa-plus-circle"></i>
                                            <a href="#" data-bs-toggle="modal" data-bs-target="#addressModal" data-title="Thêm địa chỉ mới" style="font-weight: bold" onclick="openAddModal()"> + Thêm địa chỉ giao hàng</a>
                                        </div>

                                        <!-- Include Bootstrap JS (Make sure to include it after jQuery if you are using it) -->
                                        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gyb84FfNYHpEMG5dKg6i1Q1M6lK9OL7AO9EEN7o8yI5iw8C49Y" crossorigin="anonymous"></script>
                                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGiajD+AK47E2crK7tmeS+Zg+77QeRGTf3Qw5D7/Y3Pck5n6FB0vPb9jjlE" crossorigin="anonymous"></script>
                                    </div>
                                    <!--for customer login with account-->
                                </c:otherwise>
                            </c:choose>
                            <div class="row">
                                <div class="list-group mt-4 col-lg-6">
                                    <h4>Phương thức thanh toán</h4>
                                    <label class="list-group-item d-flex gap-2 border-0">
                                        <input class="form-check-input flex-shrink-0" type="radio" name="paymentMethod"
                                               id="listGroupRadios2" value="VNPAY" required>
                                        <img src="${pageContext.request.contextPath}/img/Payment/vnpay.png" height="35px" width="auto"/>
                                        <span>
                                            <p class="mb-1">VNPay</p>
                                        </span>
                                    </label>
                                    <label class="list-group-item d-flex gap-2 border-0">
                                        <input class="form-check-input flex-shrink-0" type="radio" name="paymentMethod"
                                               id="listGroupRadios3" value="COD" required>
                                        <img src="${pageContext.request.contextPath}/img/Payment/cod.png" height="40px" width="auto"/>
                                        <span>
                                            <p class="mb-1">Thanh toán bằng tiền mặt khi nhận hàng</p>
                                        </span>
                                    </label>
                                </div>
                                <c:if test="${sessionScope.account != null}">
                                    <div class="mt-4 col-lg-5">
                                        <h4>Mã giảm giá</h4>
                                        <div class="input-group mt-1 mb-4">
                                            <input type="text" id="discountInput" class="form-control ps-3" name="discount" placeholder="Nhập mã giảm giá">
                                            <div class="input-group-append">
                                                <button class="btn btn-dark" type="button" onclick="applyDiscount()">Áp dụng</button>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <div class="col-lg-1"></div>
                            </div>
                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                            <script>
                                                //APPLY DISCOUNT
                                                function applyDiscount() {
                                                    var discountCode = document.getElementById('discountInput').value.trim();
                                                    var totalPrice = document.getElementById('valueTotalPrice').value.trim();
                                                    if (discountCode !== '') {
                                                        $.ajax({
                                                            url: "/SWP391/applyDiscount",
                                                            type: "get", //send it through get method
                                                            data: {discount_code: discountCode, total_price: totalPrice},
                                                            success: function (data) {
                                                                //Do Something
                                                                var result = document.getElementById('totalPriceContent');
                                                                result.innerHTML = data;
                                                            },
                                                            error: function (xhr) {
                                                                //Do Something to handle error
                                                                var result = document.getElementById('totalPriceContent');
                                                                alert("Mã giảm giá không tồn tại");
                                                            }
                                                        });
                                                    } else {
                                                        alert("Bạn chưa nhập mã giảm giá");
                                                    }
                                                }
                            </script>
                        </div>

                        <div class="col-lg-4">
                            <h4 class="mb-3">Đơn hàng (${listItem.size()} sản phẩm)</h4>
                            <ul class="list-group mb-3 border" style="max-height: 320px; /* Đặt chiều cao tối đa cho thẻ ul */
                                overflow-y: auto; /* Kích hoạt thanh cuộn dọc */
                                overflow-x: hidden; /* Ẩn thanh cuộn ngang nếu không cần thiết */
                                border: 1px solid #ccc; /* Tùy chọn: thêm viền để dễ thấy */
                                padding: 0;
                                list-style: none;">
                                <c:forEach items="${requestScope.listItem}" var="item">
                                    <input type="hidden" name="items" value="${item.product.productId},${item.quantity}">
                                    <li class="d-flex justify-content-between lh-sm p-3 border-bottom">
                                        <div class="d-flex align-items-center">
                                            <img src="${pageContext.request.contextPath}/${item.product.imgProduct}" width="50" height="auto" alt="Thermometer"/>
                                            <div class="ml-3" style="margin-left: 10px; width: 65%">
                                                <h6 class="my-0" style="font-size: 13px">${item.product.name}</h6><span>Số lượng: ${item.quantity}</span>
                                            </div>
                                        </div>
                                        <fmt:setLocale value="vi_VN" />
                                        <fmt:setBundle basename="resources.application" />
                                        <fmt:formatNumber var="totalPrice" value="${item.quantity * item.price}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0" />
                                        <span>${totalPrice}</span>
                                    </li>
                                </c:forEach>
                            </ul>
                            <div id="totalPriceContent" class="total-price pb-3">
                                <table cellspacing="0" class="table text-capitalize">
                                    <tbody>
                                        <tr class="subtotal pt-2 pb-2 border-top border-bottom">
                                            <th>Tạm tính</th>
                                            <td data-title="Subtotal">
                                                <span class="price-amount amount text-primary ps-5 fw-light">
                                                    <bdi>
                                                        <span class="price-currency-symbol">
                                                            <fmt:formatNumber value="${totalAmount}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0" />
                                                        </span>
                                                    </bdi>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr class="order-total pt-2 pb-2 border-bottom">
                                            <th>Phí giao hàng</th>
                                            <td data-title="Ship">
                                                <span class="price-amount amount text-primary ps-5 fw-light">
                                                    <bdi>
                                                        <span class="price-currency-symbol">
                                                            <fmt:formatNumber value="${20000}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0" />
                                                        </span>
                                                    </bdi>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr class="order-total pt-2 pb-2 border-bottom" id="totalPriceID">
                                            <th style="font-weight: bold">Tổng cộng</th>
                                            <td data-title="Total">
                                                <span class="price-amount amount text-primary ps-5 fw-light">
                                                    <bdi>
                                                        <span class="price-currency-symbol" style="font-weight: bold; font-size: 22px">
                                                            <fmt:formatNumber value="${totalAmount + 20000}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0" />
                                                            <input type="hidden" id="valueTotalPrice" name="totalPrice" value="${totalAmount + 20000}">
                                                        </span>
                                                </span>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="button-wrap mt-3">
                                <button type="submit" class="btn">ĐẶT HÀNG</button>
                                <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
                                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </form>

        <!-- Modal -->
        <form action="shippingAddress" id="modalAddressForm" method="post">
            <c:forEach items="${requestScope.listItem}" var="item">
                <input type="hidden" name="items" value="${item.product.productId},${item.quantity}">
            </c:forEach>
            <div class="modal fade" id="addressModal" tabindex="-1" aria-labelledby="addressModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="modalTitle">Thêm địa chỉ mới</h4>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <label for="phone">Số điện thoại</label>
                                    <input type="text" id="mphone" name="phone" class="form-control mt-1 ps-3 mb-4" required>
                                </div>
                            </div>
                            <div class="row">
                                <!-- Tỉnh thành, Quận huyện, Phường xã -->
                                <div class="col-lg-4">
                                    <label for="city">Tỉnh thành</label>
                                    <select class="form-select form-select-sm form-control mt-1 mb-4" id="mcity" name="city" aria-label=".form-select-sm" required>
                                        <option value="" selected>Chọn tỉnh thành</option>
                                    </select>
                                </div>
                                <div class="col-lg-4">
                                    <label for="district">Quận huyện</label>
                                    <select class="form-select form-select-sm form-control mt-1 mb-4" id="mdistrict" name="district" aria-label=".form-select-sm" required>
                                        <option value="" selected>Chọn quận huyện</option>
                                    </select>
                                </div>
                                <div class="col-lg-4">
                                    <label for="ward">Phường xã</label>
                                    <select class="form-select form-select-sm form-control mt-1 mb-4" id="mward" name="ward" aria-label=".form-select-sm" required>
                                        <option value="" selected>Chọn phường xã</option>
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <!-- Địa chỉ -->
                                <div class="col-lg-12">
                                    <label for="address">Địa chỉ cụ thể</label>
                                    <input type="text" id="maddress" name="address" class="form-control mt-1 ps-3 mb-4" required>
                                </div>
                                <div class="col-lg-12">
                                    <label for="address">Toàn bộ địa chỉ</label>
                                    <input type="text" id="mfulladdress" name="fulladdress" class="form-control mt-1 ps-3 mb-4" readonly>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-dark" data-bs-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Lưu địa chỉ</button>
                            <input type="hidden" name="action" id="actionModal">
                            <input type="hidden" name="addressID" id="updateAddressID">                          
                            <input type="hidden" id="accID" name="accID" value="${sessionScope.account.accountId}">
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script>
                                                var cities = document.getElementById("mcity");
                                                var districts = document.getElementById("mdistrict");
                                                var wards = document.getElementById("mward");
                                                var address = document.getElementById("maddress");
                                                var phone = document.getElementById("mphone");
                                                function openAddModal() {
                                                    document.getElementById('modalTitle').innerText = 'Thêm địa chỉ mới';

                                                    document.getElementById('actionModal').value = 'add';
                                                    document.getElementById('updateAddressID').value = '';
                                                    document.getElementById('mfulladdress').value = '';
                                                    document.getElementById('maddress').value = '';
                                                    document.getElementById('mphone').value = '';
                                                }

                                                function openUpdateModal(addressID, address, phoneNumber) {
                                                    document.getElementById('modalTitle').innerText = 'Cập nhật địa chỉ';

                                                    document.getElementById('actionModal').value = 'update';
                                                    document.getElementById('updateAddressID').value = addressID;
                                                    document.getElementById('mfulladdress').value = address;
                                                    document.getElementById('maddress').value = address.split(', ').slice(-1)[0];
                                                    document.getElementById('mphone').value = phoneNumber;
                                                }

                                                function updateFullAddress() {
                                                    var city = cities.options[cities.selectedIndex].text || '';
                                                    var district = districts.options[districts.selectedIndex].text || '';
                                                    var ward = wards.options[wards.selectedIndex].text || '';
                                                    var detailedAddress = document.getElementById('maddress').value.trim();

                                                    var fullAddress = [detailedAddress, ward, district, city].filter(Boolean).join(', ');
                                                    document.getElementById('mfulladdress').value = fullAddress;
                                                }

                                                wards.addEventListener('change', updateFullAddress);
                                                document.getElementById('maddress').addEventListener('input', updateFullAddress);

                                                function resetModal() {
                                                    document.getElementById('city').value = '';
                                                    document.getElementById('district').innerHTML = '<option value="" selected>Chọn Quận/Huyện</option>';
                                                    document.getElementById('mward').innerHTML = '<option value="" selected>Chọn Phường/Xã</option>';
                                                }

                                                document.getElementById('addressModal').addEventListener('hidden.bs.modal', function (event) {
                                                    resetModal();
                                                });

                                                document.getElementById('modalAddressForm').addEventListener('submit', function (event) {
                                                    // Prevent form submission initially
                                                    event.preventDefault();

                                                    // Get form elements
                                                    const phone = document.getElementById('mphone').value.trim();
                                                    const address = document.getElementById('maddress').value.trim();
                                                    var phoneNumberPattern = /^[0-9]{10,11}$/;

                                                    // Check if all fields are filled and not just whitespace
                                                    if (phone === '' || address === '') {
                                                        alert('Vui lòng nhập đầy đủ các thông tin!');
                                                        return;
                                                    }
                                                    if (!phoneNumberPattern.test(phone)) {
                                                        alert('Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại gồm 10 hoặc 11 chữ số.');
                                                        return;

                                                    }
                                                    // Check if fields contain only whitespace
                                                    if (!/\S/.test(phone) || !/\S/.test(address)) {
                                                        alert('Các trường không được chứa khoảng trắng!');
                                                        return;
                                                    }

                                                    // Submit the form if all validations pass
                                                    this.submit();
                                                });


        </script>
        <footer id="footer" class="padding-large">
            <div class="container">
                <div class="row">
                    <div class="footer-top-area">
                        <div class="row d-flex flex-wrap justify-content-between">
                            <div class="col-lg-3 col-sm-6 pb-3">
                                <div class="footer-menu">
                                    <img src="images/anh456.png" alt="logo" class="img-fluid mb-2">
                                    <p>
                                        "Tôi đọc lòi cả mắt và vẫn không đọc được tới một nửa... người ta càng đọc nhiều, người ta càng thấy còn nhiều điều cần phải đọc.” John Adams</p>
                                </div>
                            </div>
                            <div class="col-lg-2 col-sm-6 pb-3">
                                <div class="footer-menu text-capitalize">
                                    <h5 class="widget-title pb-2">Trang chính</h5>
                                    <ul class="menu-list list-unstyled text-capitalize">
                                        <li class="menu-item mb-1">
                                            <a href="home">Trang chủ</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="about">Giới thiệu</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="shop">Sản phẩm</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="blog">Tin tức</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="contact">Liên hệ</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6 pb-3">
                                <div class="footer-menu text-capitalize">
                                    <h5 class="widget-title pb-2">Trợ giúp & Thông tin Trợ giúp</h5>
                                    <ul class="menu-list list-unstyled">
                                        <li class="menu-item mb-1">
                                            <a href="#">Theo dõi đơn hàng của bạn</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="#">Chính sách hoàn trả</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="#">Vận chuyển + Giao hàng</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="#">Liên hệ chúng tôi</a>
                                        </li>
                                        <li class="menu-item mb-1">
                                            <a href="#">Câu hỏi thường gặp</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-lg-3 col-sm-6 pb-3">
                                <div class="footer-menu contact-item">
                                    <h5 class="widget-title text-capitalize pb-2">Liên hệ chúng tôi</h5>
                                    <p>Bạn có bất kỳ thắc mắc hoặc gợi ý nào không? <a href="mailto:" class="text-decoration-underline">shopbook88@gmail.com</a></p>
                                    <p>Nếu bạn cần hỗ trợ? Chỉ cần gọi cho chúng tôi. <a href="#" class="text-decoration-underline">+84 38 272
                                            0127</a></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <hr>
        <div id="footer-bottom" class="mb-2">
            <div class="container">
                <div class="d-flex flex-wrap justify-content-between">
                    <div class="ship-and-payment d-flex gap-md-5 flex-wrap">
                        <div class="shipping d-flex">
                            <p>Giao hàng nhanh</p>
                            <div class="card-wrap ps-2">
        <!--                        <img src="${pageContext.request.contextPath}/images/dhl.png" alt="visa">
                                <img src="${pageContext.request.contextPath}/images/shippingcard.png" alt="mastercard">-->
                            </div>
                        </div>
                        <div class="payment-method d-flex">

                            <p>Thanh toán trực tiếp hoặc qua các thẻ</p>

                            <div class="card-wrap ps-2">
                                <img src="${pageContext.request.contextPath}/images/visa.jpg" alt="visa">
                                <img src="${pageContext.request.contextPath}/images/mastercard.jpg" alt="mastercard">
                                <img src="${pageContext.request.contextPath}/images/paypal.jpg" alt="paypal">
                            </div>
                        </div>
                    </div>
                    <div class="copyright">
                        <p>ShopBook88 <a href="home" target="_blank">mang lại thế giới cho bạn</a>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
        <script>
                                                function setupAddressDropdowns(cityId, districtId, wardId) {
                                                    var cities = document.getElementById(cityId);
                                                    var districts = document.getElementById(districtId);
                                                    var wards = document.getElementById(wardId);

                                                    var parameter = {
                                                        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
                                                        method: "GET",
                                                        responseType: "json",
                                                    };

                                                    axios(parameter)
                                                            .then(function (response) {
                                                                renderCity(response.data, cities, districts, wards);
                                                            })
                                                            .catch(function (error) {
                                                                console.error("Error fetching data:", error);
                                                            });
                                                }

                                                function renderCity(data, cities, districts, wards) {
                                                    for (const city of data) {
                                                        cities.options[cities.options.length] = new Option(city.Name, city.Id);
                                                    }

                                                    cities.onchange = function () {
                                                        districts.length = 1;
                                                        wards.length = 1;
                                                        if (this.value !== "") {
                                                            const selectedCity = data.find(c => c.Id === this.value);
                                                            if (selectedCity) {
                                                                for (const district of selectedCity.Districts) {
                                                                    districts.options[districts.options.length] = new Option(district.Name, district.Id);
                                                                }
                                                            }
                                                        }
                                                    };

                                                    districts.onchange = function () {
                                                        wards.length = 1;
                                                        const selectedCity = data.find(c => c.Id === cities.value);
                                                        if (selectedCity && this.value !== "") {
                                                            const selectedDistrict = selectedCity.Districts.find(d => d.Id === this.value);
                                                            if (selectedDistrict) {
                                                                for (const ward of selectedDistrict.Wards) {
                                                                    wards.options[wards.options.length] = new Option(ward.Name, ward.Id);
                                                                }
                                                            }
                                                        }
                                                    };
                                                }

                                                setupAddressDropdowns("city", "district", "ward");

                                                setupAddressDropdowns("mcity", "mdistrict", "mward");

        </script>
        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>

</html>
