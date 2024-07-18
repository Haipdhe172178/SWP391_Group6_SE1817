package SendEmail;

import DAL.OrderDao;
import DAL.ProductDao;
import Models.OrderDetailGuest;
import Models.OrderGuest;
import Models.Product;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void sendEmail(String to, String subject, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("shopbooksb88@gmail.com", "vplzvaciejmiqgll");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("shopbooksb88@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);

            System.out.println("Email sent successfully to " + to);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sendEmailConfirm(int orderGID) {
        OrderDao od = new OrderDao();
        OrderGuest orderGuest = od.getOrderGuestByID(orderGID);
        ProductDao pDao = new ProductDao();
        List<OrderDetailGuest> listOd = orderGuest.getOrderDetails();

        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<body>");
        content.append("<h1>Xác nhận đơn hàng #" + orderGID + "</h1>");
        content.append("<p>Cám ơn bạn đã mua hàng! Đơn hàng của bạn đã được nhận và sẽ sớm được gửi đi.</p>");

        // Thêm thông tin chi tiết đơn hàng vào email
        content.append("<h2>Thông tin chi tiết đơn hàng:</h2>");
        content.append("<table border='1' cellpadding='5' cellspacing='0'>");
        content.append("<tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th></tr>");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Thay đổi các giá trị dưới đây bằng thông tin thực tế từ đơn hàng của bạn
        for (OrderDetailGuest og : listOd) {
            Product p = pDao.getProductById(og.getProductId());
            content.append("<tr><td>" + p.getName() + "</td><td>" + currencyFormat.format(og.getUnitPrice()) + " </td><td>" + og.getQuantity() + "</td><td>" + currencyFormat.format(og.getUnitPrice() * og.getQuantity()) + " </td></tr>");
        }

        // Tổng tiền và thông tin khách hàng
        content.append("<tr><td colspan='3' style='text-align:right'>Tổng tiền:</td><td>" + currencyFormat.format(orderGuest.getTotalPrice()) + " </td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Họ và tên khách hàng:</td><td>" + orderGuest.getFullName() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Email khách hàng:</td><td>" + orderGuest.getEmail() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Số điện thoại khách hàng:</td><td>" + orderGuest.getPhoneNumber() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Địa chỉ giao hàng:</td><td>" + orderGuest.getAddress() + "</td></tr>");

        content.append("</table>");
        content.append("</body>");
        content.append("</html>");

        // Gửi email
        return content.toString();
    }
     public static String sendEmailConfirmAdmin(int orderGID) {
        OrderDao od = new OrderDao();
        OrderGuest orderGuest = od.getOrderGuestByID(orderGID);
        ProductDao pDao = new ProductDao();
        List<OrderDetailGuest> listOd = orderGuest.getOrderDetails();

        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<body>");
        content.append("<h1>Xác nhận đơn hàng #" + orderGID + "</h1>");
        content.append("<p>Cám ơn bạn đã mua hàng! Đơn hàng của bạn đã được chuẩn bị và giao hàng.</p>");

        // Thêm thông tin chi tiết đơn hàng vào email
        content.append("<h2>Thông tin chi tiết đơn hàng:</h2>");
        content.append("<table border='1' cellpadding='5' cellspacing='0'>");
        content.append("<tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th></tr>");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Thay đổi các giá trị dưới đây bằng thông tin thực tế từ đơn hàng của bạn
        for (OrderDetailGuest og : listOd) {
            Product p = pDao.getProductById(og.getProductId());
            content.append("<tr><td>" + p.getName() + "</td><td>" + currencyFormat.format(og.getUnitPrice()) + " </td><td>" + og.getQuantity() + "</td><td>" + currencyFormat.format(og.getUnitPrice() * og.getQuantity()) + " </td></tr>");
        }

        // Tổng tiền và thông tin khách hàng
        content.append("<tr><td colspan='3' style='text-align:right'>Tổng tiền:</td><td>" + currencyFormat.format(orderGuest.getTotalPrice()) + " </td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Họ và tên khách hàng:</td><td>" + orderGuest.getFullName() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Email khách hàng:</td><td>" + orderGuest.getEmail() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Số điện thoại khách hàng:</td><td>" + orderGuest.getPhoneNumber() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Địa chỉ giao hàng:</td><td>" + orderGuest.getAddress() + "</td></tr>");

        content.append("</table>");
        content.append("</body>");
        content.append("</html>");

        // Gửi email
        return content.toString();
    }
     
      public static String sendEmailConfirmAdminHuy(int orderGID) {
        OrderDao od = new OrderDao();
        OrderGuest orderGuest = od.getOrderGuestByID(orderGID);
        ProductDao pDao = new ProductDao();
        List<OrderDetailGuest> listOd = orderGuest.getOrderDetails();

        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<body>");
        content.append("<h1>Xác nhận đơn hàng #" + orderGID + "</h1>");
        content.append("<p>Cám ơn bạn đã mua hàng! Đơn hàng của bạn đã hủy.</p>");

        // Thêm thông tin chi tiết đơn hàng vào email
        content.append("<h2>Thông tin chi tiết đơn hàng:</h2>");
        content.append("<table border='1' cellpadding='5' cellspacing='0'>");
        content.append("<tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th></tr>");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Thay đổi các giá trị dưới đây bằng thông tin thực tế từ đơn hàng của bạn
        for (OrderDetailGuest og : listOd) {
            Product p = pDao.getProductById(og.getProductId());
            content.append("<tr><td>" + p.getName() + "</td><td>" + currencyFormat.format(og.getUnitPrice()) + " </td><td>" + og.getQuantity() + "</td><td>" + currencyFormat.format(og.getUnitPrice() * og.getQuantity()) + " </td></tr>");
        }

        // Tổng tiền và thông tin khách hàng
        content.append("<tr><td colspan='3' style='text-align:right'>Tổng tiền:</td><td>" + currencyFormat.format(orderGuest.getTotalPrice()) + " </td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Họ và tên khách hàng:</td><td>" + orderGuest.getFullName() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Email khách hàng:</td><td>" + orderGuest.getEmail() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Số điện thoại khách hàng:</td><td>" + orderGuest.getPhoneNumber() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Địa chỉ giao hàng:</td><td>" + orderGuest.getAddress() + "</td></tr>");

        content.append("</table>");
        content.append("</body>");
        content.append("</html>");

        // Gửi email
        return content.toString();
    }
     public static String sendEmailConfirmAdminHP(int orderGID) {
        OrderDao od = new OrderDao();
        OrderGuest orderGuest = od.getOrderGuestByID(orderGID);
        ProductDao pDao = new ProductDao();
        List<OrderDetailGuest> listOd = orderGuest.getOrderDetails();

        StringBuilder content = new StringBuilder();
        content.append("<html>");
        content.append("<body>");
        content.append("<h1>Xác nhận đơn hàng #" + orderGID + "</h1>");
        content.append("<p>Cám ơn bạn đã mua hàng!</p>");

        // Thêm thông tin chi tiết đơn hàng vào email
        content.append("<h2>Thông tin chi tiết đơn hàng:</h2>");
        content.append("<table border='1' cellpadding='5' cellspacing='0'>");
        content.append("<tr><th>Sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th></tr>");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        // Thay đổi các giá trị dưới đây bằng thông tin thực tế từ đơn hàng của bạn
        for (OrderDetailGuest og : listOd) {
            Product p = pDao.getProductById(og.getProductId());
            content.append("<tr><td>" + p.getName() + "</td><td>" + currencyFormat.format(og.getUnitPrice()) + " </td><td>" + og.getQuantity() + "</td><td>" + currencyFormat.format(og.getUnitPrice() * og.getQuantity()) + " </td></tr>");
        }

        // Tổng tiền và thông tin khách hàng
        content.append("<tr><td colspan='3' style='text-align:right'>Tổng tiền:</td><td>" + currencyFormat.format(orderGuest.getTotalPrice()) + " </td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Họ và tên khách hàng:</td><td>" + orderGuest.getFullName() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Email khách hàng:</td><td>" + orderGuest.getEmail() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Số điện thoại khách hàng:</td><td>" + orderGuest.getPhoneNumber() + "</td></tr>");
        content.append("<tr><td colspan='3' style='text-align:right'>Địa chỉ giao hàng:</td><td>" + orderGuest.getAddress() + "</td></tr>");

        content.append("</table>");
        content.append("</body>");
        content.append("</html>");

        // Gửi email
        return content.toString();
    }
    
}
