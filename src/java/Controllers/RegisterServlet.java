/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS TUF
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
         request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
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
        String fullName = request.getParameter("name");
        String userName = request.getParameter("username");
        String password = request.getParameter("pass");
        String rePassword = request.getParameter("re_pass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        AccountDAO accountDAO = new AccountDAO();

        if (accountDAO.checkUserNameExists(userName)) {
            request.setAttribute("notification", "Tài khoản đã tồn tại. Làm ơn nhập tài khoản khác.");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
            return;
        }

        if (accountDAO.checkEmailExists(email)) {
            request.setAttribute("notification", "Email đã tồn tại. Làm ơn nhập email khác.");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
            return;
        }

        if (!password.equals(rePassword)) {
            request.setAttribute("notification", "Mật khẩu không đúng. Làm ơn thử lại.");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
            return;
        }

        boolean userCreated = accountDAO.createUser(fullName, userName, password, email, phoneNumber, address);

        if (userCreated) {
            request.setAttribute("notification","Đăng ký thành công");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
        } else {
            request.setAttribute("notification", "There was an error creating your account. Please try again.");
            request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}