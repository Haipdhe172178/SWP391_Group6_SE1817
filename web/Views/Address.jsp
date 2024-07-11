<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

    <head>
    <title>Bookly - Trang quản lý địa chỉ giao hàng</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="author" content="">
    <meta name="keywords" content="">
    <meta name="description" content="">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">

    <!-- Swiper CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />

    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link
        href="https://fonts.googleapis.com/css2?family=Nunito:ital,wght@0,200..1000;1,200..1000&display=swap"
        rel="stylesheet">

    <!-- Inline CSS for Page Specific Styling -->
    <style>
        .filter-box {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
            background-color: white;
            margin: 20px 0;
        }

        .header-section {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .header-section h2 {
            margin: 0;
            font-size: 20px;
        }

        .btn-add {
            margin-left: 20px;
        }

        .section-title h2 {
            font-size: 20px;
            /* Match the font size of "Manage Shipping Addresses" */
        }

        .modal-dialog {
            max-width: 800px;
            /* Make the modal larger */
            margin: auto;
            /* Center the modal horizontally */
        }

        .modal-content {
            border-radius: 5px;
        }

        button[type="submit"] {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            background-color: #f86d72;
            color: #fff;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #e85b5e;
        }

        .separator {
            border-top: 2px solid #ddd;
            margin: 20px 0;
        }

        .table td,
        .table th {
            vertical-align: middle;
        }
    </style>
</head>

<body>
    <jsp:include page="../common/header.jsp"></jsp:include>

    <div class="container mt-5">
        <div class="filter-box">
            <div class="header-section">
                <h2>Địa chỉ của tôi</h2>
                <button type="button" class="btn btn-primary btn-add" data-bs-toggle="modal"
                    data-bs-target="#addAddressModal">
                    Thêm Địa Chỉ Mới
                </button>
            </div>
            <div class="separator"></div>
            <div class="modal fade" id="addAddressModal" tabindex="-1" aria-labelledby="addAddressModalLabel"
                aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="addAddressModalLabel">Thêm Địa Chỉ Mới</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="address" method="post" id="addAddressForm">
                                <input type="hidden" id="action" name="action" value="save">
                                <input type="hidden" id="accID" name="accID" value="${sessionScope.account.accountId}">
                                <input type="hidden" id="addressID" name="addressID" value="">
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="phoneNumber" class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"
                                            required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-4">
                                        <label for="city" class="form-label">Tỉnh thành</label>
                                        <select class="form-select form-select-sm mb-3" id="city" name="city"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn tỉnh thành</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="district" class="form-label">Quận huyện</label>
                                        <select class="form-select form-select-sm mb-3" id="district" name="district"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn quận huyện</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="ward" class="form-label">Phường xã</label>
                                        <select class="form-select form-select-sm" id="ward" name="ward"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn phường xã</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="detailedAddress" class="form-label">Địa chỉ cụ thể</label>
                                        <input type="text" class="form-control" id="detailedAddress"
                                            name="detailedAddress" required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="address" class="form-label">Toàn bộ địa chỉ</label>
                                        <input type="text" class="form-control" id="address" name="address" readonly
                                            required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col text-end">
                                        <button type="submit" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="submit" class="btn btn-primary">Lưu địa chỉ</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" id="updateAddressModal" tabindex="-1" aria-labelledby="updateAddressModalLabel"
                aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="updateAddressModalLabel">Cập nhật địa chỉ</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="address" method="post" id="updateAddressForm">
                                <input type="hidden" id="updateAction" name="action" value="update">
                                <input type="hidden" id="updateAccID" name="accID" value="${sessionScope.account.accountId}">
                                <input type="hidden" id="updateAddressID" name="addressID" value="">
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="updatePhoneNumber" class="form-label">Số điện thoại</label>
                                        <input type="text" class="form-control" id="updatePhoneNumber" name="phoneNumber"
                                            required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-md-4">
                                        <label for="updateCity" class="form-label">Tỉnh thành</label>
                                        <select class="form-select form-select-sm mb-3" id="updateCity" name="city"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn tỉnh thành</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="updateDistrict" class="form-label">Quận huyện</label>
                                        <select class="form-select form-select-sm mb-3" id="updateDistrict" name="district"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn quận huyện</option>
                                        </select>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="updateWard" class="form-label">Phường xã</label>
                                        <select class="form-select form-select-sm" id="updateWard" name="ward"
                                            aria-label=".form-select-sm" required>
                                            <option value="" selected>Chọn phường xã</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="updateDetailedAddress" class="form-label">Địa chỉ cụ thể</label>
                                        <input type="text" class="form-control" id="updateDetailedAddress"
                                            name="detailedAddress" required>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <div class="col-12">
                                        <label for="updateAddress" class="form-label">Toàn bộ địa chỉ</label>
                                        <input type="text" class="form-control" id="updateAddress" name="address" readonly
                                            required>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col text-end">
                                        <button type="submit" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                        <button type="submit" class="btn btn-primary">Lưu địa chỉ</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <h2 class="section-title mt-5">Địa chỉ</h2>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Toàn bộ địa chỉ</th>
                        <th>Số điện thoại</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="address" items="${addresses}">
                        <tr>
                            <td>${address.getAddress()}</td>
                            <td>${address.getPhoneNumber()}</td>
                            <td>
                                <button type="submit" class="btn btn-warning" data-bs-toggle="modal"
                                    data-bs-target="#updateAddressModal"
                                    onclick="openUpdateModal(${address.getAddressID()}, '${address.getAddress()}',
                                            '${address.getPhoneNumber()}', ${address.isDefault()})">
                                    Cập nhật
                                </button>
                                <form action="address" method="post" style="display:inline;" onsubmit="confirmDelete(event)">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="addressID" value="${address.getAddressID()}">
                                    <button type="submit" class="btn btn-danger">Xóa</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>

    <script>
        var cities = document.getElementById("city");
        var districts = document.getElementById("district");
        var wards = document.getElementById("ward");
        var updateCities = document.getElementById("updateCity");
        var updateDistricts = document.getElementById("updateDistrict");
        var updateWards = document.getElementById("updateWard");
        var apiUrl = "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json";

        function openAddModal() {
            document.getElementById('action').value = 'save';
            document.getElementById('addressID').value = '';
            document.getElementById('address').value = '';
            document.getElementById('detailedAddress').value = '';
            document.getElementById('phoneNumber').value = '';
            document.getElementById('isDefault').checked = false;
        }

        function openUpdateModal(addressID, address, phoneNumber, isDefault) {
            document.getElementById('updateAction').value = 'update';
            document.getElementById('updateAddressID').value = addressID;
            document.getElementById('updateAddress').value = address;
            document.getElementById('updateDetailedAddress').value = address.split(', ')[0];
            document.getElementById('updatePhoneNumber').value = phoneNumber;
            document.getElementById('updateIsDefault').checked = isDefault;
        }

        function fetchCities() {
            axios.get(apiUrl)
                .then(function (response) {
                    renderCities(response.data);
                })
                .catch(function (error) {
                    console.error("Lỗi khi lấy dữ liệu các Tỉnh thành", error);
                });
        }

        function renderCities(data) {
            cities.innerHTML = '<option value="" selected>Chọn tỉnh thành</option>';
            updateCities.innerHTML = '<option value="" selected>Chọn tỉnh thành</option>';
            if (data && data.length > 0) {
                data.forEach(function (city) {
                    var option = document.createElement('option');
                    option.value = city.Id;
                    option.textContent = city.Name;
                    cities.appendChild(option);

                    var updateOption = document.createElement('option');
                    updateOption.value = city.Id;
                    updateOption.textContent = city.Name;
                    updateCities.appendChild(updateOption);
                });
            } else {
                console.error("Không có dữ liệu Tỉnh/Thành phố.");
            }
        }

        function fetchDistricts(cityId, isUpdate = false) {
            axios.get(apiUrl)
                .then(function (response) {
                    renderDistricts(response.data, cityId, isUpdate);
                })
                .catch(function (error) {
                    console.error("Lỗi khi lấy dữ liệu Quận huyện", error);
                });
        }

        function renderDistricts(data, cityId, isUpdate) {
            var districtElement = isUpdate ? updateDistricts : districts;
            districtElement.innerHTML = '<option value="" selected>Chọn quận huyện</option>';
            var city = data.find(function (c) {
                return c.Id == cityId;
            });
            if (city && city.Districts && city.Districts.length > 0) {
                city.Districts.forEach(function (district) {
                    var option = document.createElement('option');
                    option.value = district.Id;
                    option.textContent = district.Name;
                    districtElement.appendChild(option);
                });
            } else {
                console.error("Không có dữ liệu Quận/Huyện cho Tỉnh/Thành phố đã chọn.");
            }
        }

        function fetchWards(cityId, districtId, isUpdate = false) {
            axios.get(apiUrl)
                .then(function (response) {
                    renderWards(response.data, cityId, districtId, isUpdate);
                })
                .catch(function (error) {
                    console.error("Lỗi khi lấy dữ liệu Phường xã", error);
                });
        }

        function renderWards(data, cityId, districtId, isUpdate) {
            var wardElement = isUpdate ? updateWards : wards;
            wardElement.innerHTML = '<option value="" selected>Chọn phường xã</option>';
            var city = data.find(function (c) {
                return c.Id == cityId;
            });
            if (city && city.Districts && city.Districts.length > 0) {
                var district = city.Districts.find(function (d) {
                    return d.Id == districtId;
                });
                if (district && district.Wards && district.Wards.length > 0) {
                    district.Wards.forEach(function (ward) {
                        var option = document.createElement('option');
                        option.value = ward.Id;
                        option.textContent = ward.Name;
                        wardElement.appendChild(option);
                    });
                } else {
                    console.error("Không có dữ liệu Phường/Xã cho Quận/Huyện đã chọn.");
                }
            } else {
                console.error("Không có dữ liệu Quận/Huyện cho Tỉnh/Thành phố đã chọn.");
            }
        }

        cities.addEventListener('change', function () {
            var cityId = this.value;
            if (cityId) {
                fetchDistricts(cityId);
            } else {
                districts.innerHTML = '<option value="" selected>Chọn quận huyện</option>';
                wards.innerHTML = '<option value="" selected>Chọn phường xã</option>';
            }
        });

        districts.addEventListener('change', function () {
            var cityId = cities.value;
            var districtId = this.value;
            if (districtId) {
                fetchWards(cityId, districtId);
            } else {
                wards.innerHTML = '<option value="" selected>Chọn phường xã</option>';
            }
        });

        updateCities.addEventListener('change', function () {
            var cityId = this.value;
            if (cityId) {
                fetchDistricts(cityId, true);
            } else {
                updateDistricts.innerHTML = '<option value="" selected>Chọn quận huyện</option>';
                updateWards.innerHTML = '<option value="" selected>Chọn phường xã</option>';
            }
        });

        updateDistricts.addEventListener('change', function () {
            var cityId = updateCities.value;
            var districtId = this.value;
            if (districtId) {
                fetchWards(cityId, districtId, true);
            } else {
                updateWards.innerHTML = '<option value="" selected>Chọn phường xã</option>';
            }
        });

        wards.addEventListener('change', updateFullAddress);
        document.getElementById('detailedAddress').addEventListener('input', updateFullAddress);

        updateWards.addEventListener('change', updateFullAddressUpdate);
        document.getElementById('updateDetailedAddress').addEventListener('input', updateFullAddressUpdate);

        function updateFullAddress() {
            var detailedAddress = document.getElementById('detailedAddress').value.trim();
            var province = cities.options[cities.selectedIndex].text || '';
            var district = districts.options[districts.selectedIndex].text || '';
            var ward = wards.options[wards.selectedIndex].text || '';

            var fullAddress = [detailedAddress, ward, district, province].filter(Boolean).join(', ');
            document.getElementById('address').value = fullAddress;
        }

        function updateFullAddressUpdate() {
            var detailedAddress = document.getElementById('updateDetailedAddress').value.trim();
            var province = updateCities.options[updateCities.selectedIndex].text || '';
            var district = updateDistricts.options[updateDistricts.selectedIndex].text || '';
            var ward = updateWards.options[updateWards.selectedIndex].text || '';

            var fullAddress = [detailedAddress, ward, district, province].filter(Boolean).join(', ');
            document.getElementById('updateAddress').value = fullAddress;
        }

        function confirmDelete(event) {
            if (!confirm("Bạn có muốn xóa địa chỉ này không?")) {
                event.preventDefault();
            }
        }

        function resetAddModal() {
            document.getElementById('city').value = '';
            document.getElementById('district').innerHTML = '<option value="" selected>Chọn quận huyện</option>';
            document.getElementById('ward').innerHTML = '<option value="" selected>Chọn phường xã</option>';
            document.getElementById('address').value = '';
            document.getElementById('detailedAddress').value = '';
            document.getElementById('phoneNumber').value = '';
            document.getElementById('isDefault').checked = false;
        }

        function resetUpdateModal() {
            document.getElementById('updateCity').value = '';
            document.getElementById('updateDistrict').innerHTML = '<option value="" selected>Chọn quận huyện</option>';
            document.getElementById('updateWard').innerHTML = '<option value="" selected>Chọn phường xã</option>';
            document.getElementById('updateAddress').value = '';
            document.getElementById('updateDetailedAddress').value = '';
            document.getElementById('updatePhoneNumber').value = '';
            document.getElementById('updateIsDefault').checked = false;
        }

        document.getElementById('addAddressModal').addEventListener('hidden.bs.modal', function (event) {
            resetAddModal();
        });

        document.getElementById('updateAddressModal').addEventListener('hidden.bs.modal', function (event) {
            resetUpdateModal();
        });

        function validateAddressForm(form) {
            var address = form.address.value.trim();
            var detailedAddress = form.detailedAddress.value.trim();
            var phoneNumber = form.phoneNumber.value.trim();
            var phoneNumberPattern = /^[0-9]{10,11}$/;

            if (!detailedAddress || !detailedAddress.replace(/\s/g, '').length) {
                alert("Địa chỉ chi tiết không được chứa toàn khoảng trắng.");
                return false;
            }

            if (!phoneNumberPattern.test(phoneNumber)) {
                alert("Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại gồm 10 hoặc 11 chữ số.");
                return false;
            }

            return true;
        }

        document.getElementById('addAddressForm').addEventListener('submit', function (event) {
            if (!validateAddressForm(this)) {
                event.preventDefault();
            }
        });

        document.getElementById('updateAddressForm').addEventListener('submit', function (event) {
            if (!validateAddressForm(this)) {
                event.preventDefault();
            }
        });

        fetchCities();
    </script>

</body>








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
                                <!--                            <div class="social-links">
                                                                <ul class="d-flex list-unstyled">
                                                                    <li>
                                                                        <a href="#">
                                                                            <svg class="facebook">
                                                                            <use xlink:href="#facebook" />
                                                                            </svg>
                                                                        </a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">
                                                                            <svg class="instagram">
                                                                            <use xlink:href="#instagram" />
                                                                            </svg>
                                                                        </a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">
                                                                            <svg class="twitter">
                                                                            <use xlink:href="#twitter" />
                                                                            </svg>
                                                                        </a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">
                                                                            <svg class="linkedin">
                                                                            <use xlink:href="#linkedin" />
                                                                            </svg>
                                                                        </a>
                                                                    </li>
                                                                    <li>
                                                                        <a href="#">
                                                                            <svg class="youtube">
                                                                            <use xlink:href="#youtube" />
                                                                            </svg>
                                                                        </a>
                                                                    </li>
                                                                </ul>
                                                            </div>-->
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
    <script>
        function limitCheckboxSelection(checkbox) {
            var checkboxes = document.getElementsByName(checkbox.name);
            checkboxes.forEach(function (currentCheckbox) {
                if (currentCheckbox !== checkbox) {
                    currentCheckbox.checked = false;
                }
            });
        }

    </script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script type="text/javascript" src="js/script.js"></script>
</body>

</body>
</html>
