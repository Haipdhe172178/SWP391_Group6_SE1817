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
public class FilterControllers extends HttpServlet {

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
            out.println("<title>Servlet FilterControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FilterControllers at " + request.getContextPath() + "</h1>");
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

        List<Product> list;
        int count = 0;
        int endPage;

        String[] categoryIdStrArray = request.getParameterValues("categoryId");
        List<Integer> selectedCategoryIds = new ArrayList<>();
        boolean selectAll = false;
        if (categoryIdStrArray != null) {
            for (String categoryIdStr : categoryIdStrArray) {
                if (categoryIdStr != null && !categoryIdStr.isEmpty()) {
                    if (categoryIdStr.equals("all")) {
                        selectAll = true;
                        break;
                    }
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
        // Lấy danh sách sản phẩm và số lượng sản phẩm tương ứng với các tiêu chí đã chọn
        if (!selectedCategoryIds.isEmpty() && ageId != 0 && (minPrice > 0 || maxPrice > 0)) {
            list = productDao.paginProductByCateIdAgeIdPrice(index, selectedCategoryIds, ageId, minPrice, maxPrice, true, true);
            count = productDao.countProductsByCategoryIdsAgeIDPrice(selectedCategoryIds, ageId, minPrice, maxPrice);
            //Lấy danh sách sản phẩm và số lượng sản phẩm tương ứng với categoryId va ageId
        } else if (!selectedCategoryIds.isEmpty() && ageId != 0) {
            list = productDao.pagingProductsByCateIdAgeId(index, selectedCategoryIds, ageId);
            count = productDao.countProductsByCategoryIdsAgeID(selectedCategoryIds, ageId);
            //Lấy danh sách sản phẩm và số lượng sản phẩm tương ứng với categoryId va price   
        } else if (!selectedCategoryIds.isEmpty() && (minPrice > 0 || maxPrice > 0)) {
            list = productDao.pagingProductsByCateIdPrice(index, selectedCategoryIds, minPrice, maxPrice);
            count = productDao.countProductsByCareIdPrice(selectedCategoryIds, minPrice, maxPrice);
        } else if (ageId != 0 && (minPrice > 0 || maxPrice > 0)) {
            list = productDao.pagingProductAgeIdPrice(index, ageId, minPrice, maxPrice);
            count = productDao.countProductAgeIdPrice(ageId, minPrice, maxPrice);
        } else if (!selectedCategoryIds.isEmpty()) {
            // Nếu người dùng chỉ lọc theo categoryId
            list = productDao.pagingProductsByCatagoryId(index, selectedCategoryIds);
            count = productDao.countProductsByCategoryIds(selectedCategoryIds);
        } else if (ageId != 0) {
            // Nếu người dùng chỉ lọc theo ageId
            list = productDao.pagingProductsByAgeId(index, ageId);
            count = productDao.countProductsByAgeId(ageId);
        } else if (minPrice > 0 || maxPrice > 0) {
            // Nếu người dùng lọc theo khoảng giá
            list = productDao.pagingProductsByPriceRange(index, minPrice, maxPrice);
            count = productDao.countPriceRange(minPrice, maxPrice);
        } else if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
            list = productDao.pagingProductsByKeyword(index, searchKeyword);
            count = productDao.getTotalProductsByKeyword(searchKeyword);
        } else {
            // Nếu không có tiêu chí lọc nào được chọn, sử dụng phân trang thông thường
            list = productDao.pagingProducts(index);
            count = productDao.getTotalProduct();
        }

        endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

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

        request.getRequestDispatcher("Views/Filter.jsp").forward(request, response);
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
