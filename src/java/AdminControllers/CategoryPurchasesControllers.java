/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.OrderDao;
import Models.CategorySales;
import Models.ProductSales;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author huyca
 */
public class CategoryPurchasesControllers extends HttpServlet {

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
            out.println("<title>Servlet CategoryPurchasesControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryPurchasesControllers at " + request.getContextPath() + "</h1>");
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
        String categoryIdParam = request.getParameter("categoryId");
        int categoryId = Integer.parseInt(categoryIdParam);
        String searchName = request.getParameter("s");
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        String sort = request.getParameter("sort");
        OrderDao orderDao = new OrderDao();
        int total = 0;
        List<ProductSales> productSaleses;
        if (searchName != null && !searchName.isEmpty()) {
            productSaleses = orderDao.searchProductsByCategory(categoryId, index, searchName);
            total = orderDao.countProductsSearch(categoryId, searchName);
        } else if (sort != null) {
            productSaleses = orderDao.sortProductsByCategory(categoryId, index, sort);
            total = orderDao.countProductsByCategory(categoryId);
        } else {
            productSaleses = orderDao.getProductsByCategory(categoryId, index);
            total = orderDao.countProductsByCategory(categoryId);
        }

        int endPage = total / 5;
        if (total % 5 != 0) {
            endPage++;
        }

        String query = "";
        if (sort != null && !sort.isEmpty()) {
            query += "&sort=" + sort;
        }
        if (searchName != null && !searchName.isEmpty()) {
            query += "&s=" + searchName;
        }

        String categoryName = orderDao.getCategoryNameById(categoryId);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("categoryId", categoryId);
        request.setAttribute("ListA", productSaleses);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("sort", sort);
        request.setAttribute("query", query);
        request.getRequestDispatcher("Views/Admin/CategoryPurchases.jsp").forward(request, response);
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
