/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import DAL.ContactDAO;
import DAL.FeedbackDAO;
import DAL.NewsDao;
import Models.Account;
import Models.Contact;
import Models.Feedback;
import Models.News;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class ContactControllers extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContactControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContactControllers at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ContactDAO contactDAO = new ContactDAO();
        List<Contact> contacts = contactDAO.getAll();
        Contact contact = contacts.isEmpty() ? new Contact() : contacts.get(0);

        //Lấy news và feedback
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        List<Account> listAcc = accDAO.getAllAccount();
        List<News> listNews = nd.getFourNewsLated();
        List<Feedback> listMostRating = feedbackDAO.getFeedbackMostRating();
        request.setAttribute("listAccount", listAcc);
        request.setAttribute("listMostRating", listMostRating);
        request.setAttribute("news", listNews);

        request.setAttribute("contact", contact);
        request.getRequestDispatcher("Views/Contact.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String topic = request.getParameter("topic");
        String message = request.getParameter("message");

        Contact contact = new Contact();
        contact.setUserName(userName);
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setTopic(topic);
        contact.setMessage(message);

        ContactDAO contactDAO = new ContactDAO();
        boolean success = contactDAO.insertContact(contact);

        if (success) {
            request.setAttribute("message", "Liên hệ của bạn đã được gửi thành công!");
        } else {
            request.setAttribute("message", "Có lỗi xảy ra, vui lòng thử lại!");
        }
        request.getRequestDispatcher("Views/Contact.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
