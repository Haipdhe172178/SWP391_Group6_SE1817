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
        String priceFilter = request.getParameter("price_filter");
        String objageParam = request.getParameter("objage");

        Integer categoryId = null;
        Integer ageId = null;

        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdParam);
        }

        if (objageParam != null && !objageParam.isEmpty()) {
            ageId = Integer.parseInt(objageParam);
        }

        int count = 0;
        List<Product> list = new ArrayList<>();

        if ((keyword != null && !keyword.trim().isEmpty()) || categoryId != null || ageId != null) {
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

                    }
                } else {
                    list = productDao.pagingProductsByCategory(index, categoryId);
                }
            } else if (ageId != null) {
                count = productDao.countProductsByAgeId(ageId);
                if (sortBy != null && !sortBy.isEmpty()) {
                    switch (sortBy) {
                        case "name_asc":
                            list = productDao.pagingObjectAgeSortedByName(index, true, ageId);
                            break;
                        case "name_desc":
                            list = productDao.pagingObjectAgeSortedByName(index, false, ageId);
                            break;
                        case "price_asc":
                            list = productDao.pagingObjectAgeSortedByPrice(index, true, ageId);
                            break;
                        case "price_desc":
                            list = productDao.pagingObjectAgeSortedByPrice(index, false, ageId);
                            break;

                    }
                } else {
                    list = productDao.pagingProductsByAgeId(index, ageId);
                }
            }
        } else {
            float minPrice = 0;
            float maxPrice = 0;
            if (priceFilter != null && !priceFilter.isEmpty()) {
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

            count = productDao.countPriceRange(minPrice, maxPrice);
            list = productDao.pagingProductsByPriceRange(index, minPrice, maxPrice);
        }

        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }
        request.setAttribute("author", authors);
        request.setAttribute("category", categories);
        request.setAttribute("objage", objectAges);
        request.setAttribute("productCount", count);
        String query = "";
        if (keyword != null) {
            query += "&&keyword=" + keyword;
        }
        if (sortBy != null) {
            query += "&&sortBy=" + sortBy;
        }
        if (categoryId != null) {
            query += "&&categoryId=" + categoryId;
        }
        if (priceFilter != null) {
            query += "&&price_filter=" + priceFilter;
        }
        if (ageId != null) {
            query += "&&objage=" + ageId;
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
