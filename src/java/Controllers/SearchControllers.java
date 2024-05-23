/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyca
 */
public class SearchControllers extends HttpServlet {

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
            out.println("<title>Servlet SearchControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchControllers at " + request.getContextPath() + "</h1>");
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
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao objectAgeDao = new ObjectAgeDao();

        List<Author> authors = authorDao.getallAuthors();
        List<Category> categories = categoryDao.getallCategorys();
        List<ObjectAge> objectAges = objectAgeDao.getallObjectAges();

        String keyword = request.getParameter("s");
        String categoryIdParam = request.getParameter("categoryId");
        String sortBy = request.getParameter("sortBy");
        Integer categoryId = null;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdParam);
        }

        int count = 0;
        List<Product> list = new ArrayList<>();

        if (keyword != null && !keyword.trim().isEmpty()) {
            count = productDao.getTotalProductsByKeyword(keyword);
            if (sortBy != null && !sortBy.isEmpty()) {
                switch (sortBy) {
                    case "name_asc":
                        list = productDao.pagingProductsSortedByName(index, true, keyword);
                        break;
                    case "name_desc":
                        list = productDao.pagingProductsSortedByName(index, false, keyword);
                        break;
                    case "price_asc":
                        list = productDao.pagingProductsSortedByPrice(index, true, keyword);
                        break;
                    case "price_desc":
                        list = productDao.pagingProductsSortedByPrice(index, false, keyword);
                        break;
                    default:
                        list = productDao.pagingProductsByKeyword(index, keyword);
                        break;
                }
            } else {
                list = productDao.pagingProductsByKeyword(index, keyword);
            }
        } else if (categoryId != null) {
            count = productDao.getTotalProductsByCategory(categoryId);
            if (sortBy != null && !sortBy.isEmpty()) {
                switch (sortBy) {
                    case "name_asc":
                        list = productDao.pagingProductsSortedByName(index, true, categoryId);
                        break;
                    case "name_desc":
                        list = productDao.pagingProductsSortedByName(index, false, categoryId);
                        break;
                    case "price_asc":
                        list = productDao.pagingProductsSortedByPrice(index, true, categoryId);
                        break;
                    case "price_desc":
                        list = productDao.pagingProductsSortedByPrice(index, false, categoryId);
                        break;
                    default:
                        list = productDao.pagingProductsByCategory(index, categoryId);
                        break;
                }
            } else {
                list = productDao.pagingProductsByCategory(index, categoryId);
            }
        }

        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

        // Set attributes
        request.setAttribute("ListA", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("author", authors);
        request.setAttribute("category", categories);
        request.setAttribute("objage", objectAges);
        request.setAttribute("productCount", count);

        if (categoryId != null) {
            request.setAttribute("currentCategoryId", categoryId);
        }
        if (keyword != null) {
            request.setAttribute("currentKeyword", keyword);
        }
        if (sortBy != null) {
            request.setAttribute("sortBy", sortBy);
        }

        request.getRequestDispatcher("Views/Search.jsp").forward(request, response);
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
