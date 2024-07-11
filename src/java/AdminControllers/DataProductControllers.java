/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.AuthorDao;
import DAL.CategoryDao;
import DAL.ObjectAgeDao;
import DAL.ProductDao;
import Models.Author;
import Models.Category;
import Models.ObjectAge;
import Models.Product;
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
public class DataProductControllers extends HttpServlet {

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
            out.println("<title>Servlet DataControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DataControllers at " + request.getContextPath() + "</h1>");
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
        ProductDao productDao = new ProductDao();

        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }
        String sortParam = request.getParameter("sort");
        boolean ascending = true;
        if ("priceasc".equalsIgnoreCase(sortParam)) {
            ascending = true;
        } else if ("pricedesc".equalsIgnoreCase(sortParam)) {
            ascending = false;
        }

        List<Product> list;
        int count = 0;
        int endPage;

        String searchKeyword = request.getParameter("s");
        String statusFilter = request.getParameter("statusFilter");
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            list = productDao.pagingProductsByKeyword(index, searchKeyword);
            count = productDao.getTotalProductsByKeyword(searchKeyword);
        } else if ("priceasc".equalsIgnoreCase(sortParam) || "pricedesc".equalsIgnoreCase(sortParam)) {
            list = productDao.pagingProductsSortedByPrice(index, ascending);
            count = productDao.getTotalProductBySort(sortParam);
        } else if (statusFilter != null && !statusFilter.isEmpty()) {
            int status = Integer.parseInt(statusFilter);
            list = productDao.getProductByStatus(status, index);
            count = productDao.getTotalProductByStatus(status);
        } else {
            list = productDao.pagingProducts(index);
            count = productDao.getTotalProduct();
        }
        endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        String query = "";
        if (searchKeyword != null) {
            query += "&&s=" + searchKeyword;
        }
        if (sortParam != null) {
            query += "&&sort=" + sortParam;
        }
        if (statusFilter != null) {
            query += "&&statusFilter=" + statusFilter;
        }

        request.setAttribute("query", query);
        request.setAttribute("product", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.getRequestDispatcher("Views/Admin/Product.jsp").forward(request, response);
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
