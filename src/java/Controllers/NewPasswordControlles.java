/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.AccountDAO;
import Models.Account;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet implementation class NewPasswordControlles
 */
@WebServlet("/NewPasswordControlles")
public class NewPasswordControlles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");

        // Check if the passwords match
        if (newPassword != null && confPassword != null && newPassword.equals(confPassword)) {
            // Get the user's email from the session
            String email = (String) session.getAttribute("email");

            if (email != null) {
                // Create an Account object with the new password and email
                Account account = new Account();
                account.setPassWord(newPassword);
                account.setEmail(email);

                // Update the password in the database
                AccountDAO accountDAO = new AccountDAO();
                boolean passwordUpdated = accountDAO.updatePassword(account);

                // Redirect based on password update result
                if (passwordUpdated) {
                    // Redirect to login page on success
                    response.sendRedirect(request.getContextPath() + "/login");
                } else {
                    request.setAttribute("status", "resetFailed");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Views/Login.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                // Handle the case where the email is not found in the session
                request.setAttribute("status", "emailNotFound");
                RequestDispatcher dispatcher = request.getRequestDispatcher("Views/newPassword.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Handle the case where the passwords do not match
            request.setAttribute("status", "passwordMismatch");
            RequestDispatcher dispatcher = request.getRequestDispatcher("Views/newPassword.jsp");
            dispatcher.forward(request, response);
        }
    }
}
