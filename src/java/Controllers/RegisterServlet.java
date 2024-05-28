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
        String fullName = request.getParameter("FullName");
        String userName = request.getParameter("UserName");
        String password = request.getParameter("Password");
        String rePassword = request.getParameter("rePassword");
        String email = request.getParameter("Email");
        String phoneNumber = request.getParameter("PhoneNumber");
        String address = request.getParameter("Address");
        AccountDAO accountDAO = new AccountDAO();

        if (accountDAO.checkUserNameExists(userName)) {
            request.setAttribute("errorMessage", "Username is already taken. Please choose another one.");
            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
            return;
        }

        if (accountDAO.checkEmailExists(email)) {
            request.setAttribute("errorMessage", "Email is already in use. Please choose another one.");
            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
            return;
        }

        if (!password.equals(rePassword)) {
            request.setAttribute("errorMessage", "Passwords do not match. Please re-enter your password.");
            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
            return;
        }

        boolean userCreated = accountDAO.createUser(fullName, userName, password, email, phoneNumber, address);

        if (userCreated) {
            request.setAttribute("success","register successfully");
            response.sendRedirect("Views/Home.jsp");
        } else {
            request.setAttribute("errorMessage", "There was an error creating your account. Please try again.");
            request.getRequestDispatcher("Views/Home.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
