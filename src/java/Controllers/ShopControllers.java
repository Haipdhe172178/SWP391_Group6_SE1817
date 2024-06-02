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
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

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

        List<Product> list;
        int count;
        int endPage;

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

        endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

        StringBuilder query = new StringBuilder();
        if (!sortBy.equals("default")) {
            query.append("&sortBy=").append(sortBy);
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
