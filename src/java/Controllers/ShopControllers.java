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
import jakarta.servlet.http.HttpSession;
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
        // Get page index
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        // Sorting
        HttpSession session = request.getSession();
        String sortBy = request.getParameter("sortBy");
        if (sortBy == null) {
            sortBy = (String) session.getAttribute("sortBy");
            if (sortBy == null) {
                sortBy = "default";
            }
        } else {
            session.setAttribute("sortBy", sortBy);
        }

        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao objectAgeDao = new ObjectAgeDao();

        List<Author> authors = authorDao.getallAuthors();
        List<Category> categories = categoryDao.getallCategorys();
        List<ObjectAge> objectAges = objectAgeDao.getallObjectAges();
        
        String[] categoryIdStrArray = request.getParameterValues("categoryId");
        List<Integer> selectedCategoryIds = new ArrayList<>();
        if (categoryIdStrArray != null) {
            for (String categoryIdStr : categoryIdStrArray) {
                if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                    int categoryId = Integer.parseInt(categoryIdStr);
                    selectedCategoryIds.add(categoryId);
                }
            }
        }

        String ageIdStr = request.getParameter("objage");
        int ageId = 0;
        if (ageIdStr != null && !ageIdStr.isEmpty()) {
            ageId = Integer.parseInt(ageIdStr);
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

        String searchKeyword = request.getParameter("s");

        // Get products based on sorting and filtering
        List<Product> list = new ArrayList<>();
        int count = 0;

        if (!selectedCategoryIds.isEmpty() || ageId != 0 || minPrice > 0 || maxPrice > 0) {
            list = productDao.paginProductByFilter(index, selectedCategoryIds, ageId, minPrice, maxPrice);
            count = productDao.countProductsByFilter(selectedCategoryIds, ageId, minPrice, maxPrice);
        } else if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            list = productDao.pagingProductsByKeyword(index, searchKeyword);
            count = productDao.getTotalProductsByKeyword(searchKeyword);
        } else {
            switch (sortBy) {
                case "name_asc":
                    list = productDao.pagingProductsSortedByName(index, true);
                    break;
                case "name_desc":
                    list = productDao.pagingProductsSortedByName(index, false);
                    break;
                case "price_asc":
                    list = productDao.pagingProductsSortedByPrice(index, true);
                    break;
                case "price_desc":
                    list = productDao.pagingProductsSortedByPrice(index, false);
                    break;
                default:
                    list = productDao.pagingProducts(index);
                    break;
            }
            count = productDao.getTotalProduct();
        }

        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

        // Build query for pagination links
        StringBuilder query = new StringBuilder();
        if (!selectedCategoryIds.isEmpty()) {
            for (int categoryId : selectedCategoryIds) {
                query.append("&categoryId=").append(categoryId);
            }
        }
        if (ageId != 0) {
            query.append("&objage=").append(ageId);
        }
        if (priceFilter != null) {
            query.append("&price_filter=").append(priceFilter);
        }
        if (searchKeyword != null && !searchKeyword.isEmpty()) {
            query.append("&s=").append(searchKeyword);
        }
        if (!sortBy.equals("default")) {
            query.append("&sortBy=").append(sortBy);
        }

        // Additional data for the page
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        List<News> listNews = nd.getFourNewsLated();
        List<Feedback> listMostRating = feedbackDAO.getFeedbackMostRating();
        List<Account> listAcc = accDAO.getAllAccount();
        request.setAttribute("listMostRating", listMostRating);
        request.setAttribute("listAccount", listAcc);
        request.setAttribute("news", listNews);
        request.setAttribute("selectedCategoryIds", selectedCategoryIds);
        request.setAttribute("selectedAgeId", ageId);
        request.setAttribute("selectedPriceFilter", priceFilter);
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
