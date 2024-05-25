<%-- 
    Document   : footer
    Created on : May 25, 2024, 6:46:43 PM
    Author     : Hai Pham
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                                        <a href="#">Home</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">About</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Shop</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Blogs</a>
                                    </li>
                                    <li class="menu-item mb-1">
                                        <a href="#">Contact</a>
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
</html>
