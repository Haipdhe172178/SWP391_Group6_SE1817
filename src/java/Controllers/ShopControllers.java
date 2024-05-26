/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import DAL.AuthorDao;
import DAL.CategoryDao;
import DAL.FeedbackDAO;
import DAL.NewsDao;
import DAL.ObjectAgeDao;
import DAL.ProductDao;
import Models.Account;
import Models.Author;
import Models.Category;
import Models.Feedback;
import Models.News;
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
public class ShopControllers extends HttpServlet {

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
            out.println("<title>Servlet ShopControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopControllers at " + request.getContextPath() + "</h1>");
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

        String sortBy = request.getParameter("sortBy");

        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao objectAgeDao = new ObjectAgeDao();

        List<Author> authors = authorDao.getallAuthors();
        List<Category> categories = categoryDao.getallCategorys();
        List<ObjectAge> objectAges = objectAgeDao.getallObjectAges();

        List<Product> list;
        int count;
        int endPage;

        if (sortBy != null) {
            if (sortBy.equals("name_asc")) {
                list = productDao.pagingProductsSortedByName(index, true);
            } else if (sortBy.equals("name_desc")) {
                list = productDao.pagingProductsSortedByName(index, false);
            } else if (sortBy.equals("price_asc")) {
                list = productDao.pagingProductsSortedByPrice(index, true);
            } else if (sortBy.equals("price_desc")) {
                list = productDao.pagingProductsSortedByPrice(index, false);
            } else {
                list = productDao.pagingProducts(index);
            }
        } else {
            list = productDao.pagingProducts(index);
        }

        count = productDao.getTotalProduct();

        String categoryIdStr = request.getParameter("categoryId");
        int categoryId = 0;
        if (categoryIdStr != null) {
            categoryId = Integer.parseInt(categoryIdStr);
        }

        if (categoryId != 0) {
            list = productDao.pagingProductsByCategory(index, categoryId);
            count = productDao.getTotalProductsByCategory(categoryId);
        }

        String objectAge = request.getParameter("objage");
        int obAge = 0;
        if (objectAge != null) {
            obAge = Integer.parseInt(objectAge);
        }
        if (obAge != 0) {
            list = productDao.pagingProductsByAgeId(index, obAge);
            count = productDao.countProductsByAgeId(obAge);

        }
        String priceFilter = request.getParameter("price_filter");
        float minPrice = 0;
        float maxPrice = 0;
        if (priceFilter != null) {
            switch (priceFilter) {
                case "lessthan10":
                    maxPrice = 100000;
                    break;
                case "10to20":
                    minPrice = 100000;
                    maxPrice = 200000;
                    break;
                case "20to30":
                    minPrice = 200000;
                    maxPrice = 300000;
                    break;
                case "30to40":
                    minPrice = 300000;
                    maxPrice = 400000;
                    break;
                case "morethan50":
                    minPrice = 500000;
                    break;
                default:
                    break;
            }
        }

        if (minPrice > 0 || maxPrice > 0) {
            list = productDao.pagingProductsByPriceRange(index, minPrice, maxPrice);
            count = productDao.countPriceRange(minPrice, maxPrice);

        }

        String searchKeyword = request.getParameter("s");
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            list = productDao.pagingProductsByKeyword(index, searchKeyword);
            count = productDao.getTotalProductsByKeyword(searchKeyword);
        }
        endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        String query = "";
        if (categoryIdStr != null) {
            query += "&&categoryId=" + categoryIdStr;
        }
        if (objectAge != null) {
            query += "&&objage=" + objectAge;
        }
        if (priceFilter != null) {
            query += "&&price_filter=" + priceFilter;
        }
        if (searchKeyword != null) {
            query += "&&s=" + searchKeyword;
        }

        //Thêm list news và feedback
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        List<News> listNews = nd.getFourNewsLated();
        List<Feedback> listMostRating = feedbackDAO.getFeedbackMostRating();
        List<Account> listAcc = accDAO.getAllAccount();
        request.setAttribute("listMostRating", listMostRating);
        request.setAttribute("listAccount", listAcc);
        request.setAttribute("news", listNews);
        request.setAttribute("query", query);
        request.setAttribute("author", authors);
        request.setAttribute("category", categories);
        request.setAttribute("objage", objectAges);
        request.setAttribute("count", count);
        request.setAttribute("ListA", list);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

        request.getRequestDispatcher("Views/Shop.jsp").forward(request, response);
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
