package AdminControllers;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class ReplyContact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ReplyContact</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReplyContact at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    String to = request.getParameter("to");
    String subject = request.getParameter("subject");
    String messageContent = request.getParameter("message");

    if (to == null || to.isEmpty() || subject == null || subject.isEmpty() || messageContent == null || messageContent.isEmpty()) {
        request.setAttribute("messager", "To, subject, and message cannot be null or empty");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Views/Admin/SendContactEmail.jsp");
    dispatcher.forward(request, response);
        return;
    }
    String from = "shopbooksb88@gmail.com";
    String password = "vplzvaciejmiqgll"; 

    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");

    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(from, password);
        }
    });

    try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(messageContent);

        Transport.send(message);
        System.out.println("Message sent successfully");

        request.setAttribute("message", "Gửi thành công");
    } catch (MessagingException e) {
        e.printStackTrace();
        request.setAttribute("message", "Failed to send email");
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("Views/Admin/SendContactEmail.jsp");
    dispatcher.forward(request, response);
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
