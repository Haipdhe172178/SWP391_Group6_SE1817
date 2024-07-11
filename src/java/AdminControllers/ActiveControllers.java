/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.AccountDAO;
import DAL.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author huyca
 */
public class ActiveControllers extends HttpServlet {

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
            out.println("<title>Servlet ActiveControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ActiveControllers at " + request.getContextPath() + "</h1>");
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
        AccountDAO accountDao = new AccountDAO();
        ProductDao productDao = new ProductDao();
        String action = request.getParameter("action");
        String type = request.getParameter("type");

        if (action != null && !action.isEmpty()) {
            if ("hideacc".equals(action) || "showacc".equals(action)) {
                int accountId = Integer.parseInt(request.getParameter("accountId"));
                if ("hideacc".equals(action)) {
                    accountDao.hideAccount(accountId);
                } else if ("showacc".equals(action)) {
                    accountDao.showAccount(accountId);
                }
                if ("staff".equals(type)) {
                    response.sendRedirect(request.getContextPath() + "/manages");
                } else {
                    response.sendRedirect(request.getContextPath() + "/account");
                }
            } else if ("hideproduct".equals(action) || "showproduct".equals(action)) {
                int productId = Integer.parseInt(request.getParameter("productId"));
                if ("hideproduct".equals(action)) {
                    productDao.hideProduct(productId);
                } else if ("showproduct".equals(action)) {
                    productDao.showProduct(productId);
                }
                response.sendRedirect(request.getContextPath() + "/data");
            }
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
        processRequest(request, response);
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
