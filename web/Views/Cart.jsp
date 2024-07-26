<%-- 
    Document   : Cart.jsp
    Created on : May 15, 2024, 11:03:49 PM
    Author     : huyca
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="Models.Cart" %>
<%@ page import="Models.Item" %>
<%@ page import="Models.Product" %>
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
        <style>
            .cart-cross-outline {
                background: none;
                border: none;
                cursor: pointer;
                padding: 0;
                outline: none;
            }

            .cart-cross-outline svg {
                fill: none;
                stroke: #ff0000;
                transition: transform 0.3s ease;
            }

            .cart-cross-outline:hover svg {
                transform: scale(1.1);
            }

        </style>
    </head>

    <body>
        <jsp:include page="../common/header.jsp"></jsp:include>
            <!-- Cart -->
            <!-- sprite.svg -->
        <c:choose>
            <c:when test="${requestScope.cart.items.size() == 0}">
                <div class="container">
                    <center>
                        <div style="margin: 6rem">
                            <img src="images/cartEmpty.png" alt="alt" style="width: 20rem"/>
                            <h4>Bạn chưa có sản phẩm nào trong giỏ hàng.</h4>
                            <a href="shop" class="btn" style="margin-top: 2rem">Tiếp tục mua sắm</a>
                        </div>
                    </center>
                </div>
            </c:when>

            <c:otherwise>
                <form id="cartForm" method="post">
                    <input hidden name="url" value="cart" />
                    <div class="container">
                        <div class="row">
                            <div class="cart-table">
                                <div class="cart-header border-bottom border-top">
                                    <div class="row d-flex text-capitalize">
                                        <h4 class="col-lg-1 py-3 m-0">
                                            <input type="checkbox" id="selectAllItems"/>
                                        </h4>
                                        <h4 class="col-lg-4 py-3 m-0">Sản phẩm</h4>
                                        <h4 class="col-lg-3 py-3 m-0">Số lượng</h4>
                                        <h4 class="col-lg-4 py-3 m-0">Tổng phụ</h4>
                                    </div>
                                </div>
                                <c:forEach items="${requestScope.cart.items}" var="item">
                                    <div class="cart-item border-bottom padding-small" data-product-id="${item.product.productId}">
                                        <div class="row align-items-center">
                                            <div class="col-lg-1 col-md-1">
                                                <!--Chọn sản phẩm-->
                                                <input type="checkbox" class="product-select" data-quantity="${item.quantity}" data-price="${item.quantity * item.price}" name="selectedItem" value="${item.product.productId},${item.quantity}"/>
                                            </div>
                                            <div class="col-lg-4 col-md-3">
                                                <div class="cart-info d-flex gap-2 flex-wrap align-items-center">
                                                    <div class="col-lg-5">
                                                        <div class="card-image">
                                                            <img src="${pageContext.request.contextPath}/${item.product.imgProduct}" alt="${item.product.name}" class="img-fluid border rounded-3" style="height: 8rem; width: auto">
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-4">
                                                        <div class="card-detail">
                                                            <h5 class="mt-2" style="font-size: 16px"><a href="single?productID=${item.product.productId}">${item.product.name}</a></h5>
                                                            <div class="card-price">
                                                                <span class="price text-primary fw-bold mb-2 fs-5" data-currency-usd="${item.price}">
                                                                    <fmt:formatNumber value="${item.price}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0"/>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-6 col-md-7">
                                                <div class="row d-flex">
                                                    <div class="col-md-6">
                                                        <div class="product-quantity my-2">
                                                            <div class="input-group product-qty align-items-center" style="max-width: 150px;">
                                                                <span class="input-group-btn">
                                                                    <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-left-minus" data-type="minus" data-field="">
                                                                        <svg width="16" height="16">
                                                                        <use xlink:href="#minus"></use>
                                                                        </svg>
                                                                    </button>
                                                                </span>
                                                                <input type="text" id="quantity-${item.product.productId}" name="quantity" class="form-control bg-white shadow border rounded-3 py-2 mx-2 input-number text-center" value="${item.quantity}" min="1" max="100" required data-price="${item.product.price}" data-stock="${item.product.getQuantity()}>
                                                                       <span class="input-group-btn">
                                                                <button type="button" class="bg-white shadow border rounded-3 fw-light quantity-right-plus" data-type="plus" data-field="">
                                                                    <svg width="16" height="16">
                                                                    <use xlink:href="#plus"></use>
                                                                    </svg>
                                                                </button>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div class="total-price">
                                                            <fmt:setLocale value="vi_VN" />
                                                            <fmt:setBundle basename="resources.application" />
                                                            <fmt:formatNumber var="totalPrice" value="${item.quantity * item.price}" type="currency" currencySymbol="₫" groupingUsed="true" maxFractionDigits="0" />
                                                            <span class="money fs-2 fw-light text-primary" id="total-price-${item.product.productId}">${totalPrice}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-1 col-md-2">
                                                <!-- Xoa gio hang -->
                                                <input type="hidden" name="productId" value="${item.product.productId}">
                                                <button type="button" class="cart-cross-outline update-cart-btn">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="38" height="38" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x">
                                                    <line x1="18" y1="6" x2="6" y2="18"></line>
                                                    <line x1="6" y1="6" x2="18" y2="18"></line>
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                            <div class="cart-totals padding-medium pb-0">
                                <h3 class="mb-3">Tổng thanh toán</h3>
                                <div class="total-price pb-3">
                                    <table cellspacing="0" class="table text-capitalize">
                                        <tr class="order-total pt-2 pb-2 border-bottom">
                                            <th>Tổng</th>
                                            <td data-title="Total">
                                                <span class="price-amount amount text-primary ps-5 fw-light">
                                                    <bdi>
                                                        <span id="selected-total"></span>
                                                    </bdi>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr class="order-total pt-2 pb-2 border-bottom">
                                            <th>Tổng số lượng</th>
                                            <td data-title="Total Quantity">
                                                <span class="price-amount amount text-primary ps-5 fw-light">
                                                    <bdi>
                                                        <span id="selected-quantity">0</span>
                                                    </bdi>
                                                </span>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div class="button-wrap d-flex flex-wrap gap-3">
                                    <a href="shop" class="btn">Tiếp tục mua sắm</a>
                                    <button type="button" class="btn checkout-btn"> Thanh toán</button>
                                    <input type="hidden" name="action" value="cartToCheckout">
                                </div>
                            </div>           
                        </div>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const updateCart = (productId, quantity) => {
                    fetch('/cart', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: `productId=${productId}&quantity=${quantity}`
                    })
                            .then(response => response.json())
                            .then(data => {
                                if (data.success) {
                                    document.querySelector(`#total-price-${productId}`).textContent = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(quantity * price);
                                    document.querySelector('#selected-total').textContent = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(data.cartTotal);
                                    document.querySelector('#selected-quantity').textContent = data.totalQuantity;
                                } else {
                                    alert('Cập nhật giỏ hàng thất bại!');
                                }
                            })
                            .catch(error => console.error('Error:', error));
                };

                document.querySelectorAll('.quantity-left-minus, .quantity-right-plus').forEach(button => {
                    button.addEventListener('click', function () {
                        const input = this.closest('.product-qty').querySelector('.input-number');
                        let quantity = parseInt(input.value);
                        const stock = parseInt(input.dataset.stock);
                        const productId = this.closest('.cart-item').dataset.productId;

                        if (this.classList.contains('quantity-left-minus') && quantity > 1) {
                            quantity--;
                        } else if (this.classList.contains('quantity-right-plus')) {
                            if (quantity < stock) {
                                quantity++;
                            } else {
                                alert('Số lượng có sẵn không đủ.');
                                return; 
                            }
                        }

                        input.value = quantity;

                        const price = parseInt(input.dataset.price);
                        const totalPriceElement = this.closest('.cart-item').querySelector('.total-price span');

                        totalPriceElement.textContent = new Intl.NumberFormat('vi-VN', {style: 'currency', currency: 'VND'}).format(quantity * price);

                        updateCart(productId, quantity);
                        document.querySelector('#cartForm').submit();
                    });
                });
            });

            function removeItem(productId) {
                fetch('/cart', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded',
                    },
                    body: `action=remove&productId=${productId}`
                }).then(response => {
                    if (response.ok) {
                        location.reload();
                    } else {
                        console.error('Failed to remove item');
                    }
                }).catch(error => {
                    console.error('Error:', error);
                });
            }
        </script>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const productSelects = document.querySelectorAll('.product-select');
                const selectedTotal = document.getElementById('selected-total');
                const selectedQuantity = document.getElementById('selected-quantity');
                const selectAllItems = document.getElementById('selectAllItems');

                function updateTotals() {
                    let totalAmount = 0;
                    let totalQuantity = 0;

                    productSelects.forEach(function (checkbox) {
                        if (checkbox.checked) {
                            totalAmount += parseFloat(checkbox.dataset.price);
                            totalQuantity += parseInt(checkbox.dataset.quantity, 10);
                        }
                    });

                    selectedTotal.textContent = totalAmount.toLocaleString('vi-VN', {style: 'currency', currency: 'VND'}).replace('VND', '₫');
                    selectedQuantity.textContent = totalQuantity;
                }

                productSelects.forEach(function (checkbox) {
                    checkbox.addEventListener('change', updateTotals);
                });

                // Initial calculation in case some checkboxes are pre-checked
                updateTotals();

                // Handle selectAllItems checkbox click
                selectAllItems.addEventListener('click', function () {
                    const isChecked = selectAllItems.checked;
                    productSelects.forEach(function (checkbox) {
                        checkbox.checked = isChecked;
                    });
                    updateTotals(); // Update totals after changing the state of all checkboxes
                });

                // Listen for clicks on each product checkbox to check if all are selected
                productSelects.forEach(function (checkbox) {
                    checkbox.addEventListener('click', function () {
                        const allChecked = Array.from(productSelects).every(cb => cb.checked);
                        selectAllItems.checked = allChecked;
                    });
                });
            });
        </script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                const cartForm = document.getElementById('cartForm');

                // Xử lý sự kiện khi nút "Cập nhật giỏ hàng" được nhấn
                cartForm.querySelector('.update-cart-btn').addEventListener('click', function () {
                    // Thực hiện các thao tác cập nhật giỏ hàng tại đây
                    cartForm.action = 'deletecart';
                    cartForm.submit();
                });

                // Xử lý sự kiện khi nút "Thanh toán" được nhấn
                cartForm.querySelector('.checkout-btn').addEventListener('click', function () {
                    const selectedItems = cartForm.querySelectorAll('.product-select');
                    let hasSelected = false;

                    for (let i = 0; i < selectedItems.length; i++) {
                        if (selectedItems[i].checked) {
                            hasSelected = true;
                            break;
                        }
                    }

                    if (!hasSelected) {
                        alert('Bạn cần chọn ít nhất một sản phẩm để thanh toán.');
                        return;
                    }

                    // Nếu có sản phẩm được chọn, tiếp tục submit form để đi đến trang thanh toán
                    cartForm.action = 'check'; // Đổi action của form sang trang thanh toán
                    cartForm.submit();
                });
            });
        </script>
        <jsp:include page="../common/footer.jsp"></jsp:include>

        <script src="js/jquery-1.11.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
        <script type="text/javascript" src="js/script.js"></script>
    </body>
</html>
