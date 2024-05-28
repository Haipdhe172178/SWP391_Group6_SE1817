/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import Models.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 *
 * @author Hai Pham
 */
@MultipartConfig
public class ProfileController extends HttpServlet {

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
        if (request.getSession().getAttribute("account") != null) {
            request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
        } else {
            response.sendRedirect("home");
        }
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
        try {
            Part part = request.getPart("avatar");
            String realPath = request.getServletContext().getRealPath("/img_account");
            String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();

            if (!Files.exists(Path.of(realPath))) {
                Files.createDirectories(Path.of(realPath));
            }
            part.write(realPath + "/" + filename);

            AccountDAO accDAO = new AccountDAO();
            HttpSession session = request.getSession();
            Account a = (Account) session.getAttribute("account");
            boolean isCompleted = accDAO.updateAvatar(a, "img_account/" + filename);
            if (isCompleted) {
                a = accDAO.check(a.getUserName(), a.getPassWord());
                session.setAttribute("account", a);
                request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Failed to update avatar");
                request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "Failed to update avatar");
            request.getRequestDispatcher("Views/Profile.jsp").forward(request, response);
        }

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
