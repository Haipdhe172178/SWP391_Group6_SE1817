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
public class SingleProductControllers extends HttpServlet {

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
            out.println("<title>Servlet SingleProdcutControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SingleProdcutControllers at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("productID"));
        //DAO
        ProductDao productDao = new ProductDao();
        AuthorDao authorDao = new AuthorDao();
        CategoryDao cateDao = new CategoryDao();
        ObjectAgeDao ageDao = new ObjectAgeDao();
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        
        Product product = productDao.getProductById(id);
        Author author = authorDao.getAuthorById(product.getAuthorId());
        Category category = cateDao.getCategoryByID(product.getCategoryId());
        ObjectAge objectAge = ageDao.getObjectAgesByID(product.getAgeId());

        //LIST
        List<Product> listP = productDao.getProductsByCategoryId(product.getCategoryId(), "fourRandom");
        List<News> listNews = nd.getFourNewsLated();
        List<Feedback> listFeedback = feedbackDAO.getFeedbackByProductId(id);
        List<Author> listAuthor = authorDao.getallAuthors();
        List<Account> listAcc= accDAO.getAllAccount();
        List<Feedback> listMostRating = feedbackDAO.getFeedbackMostRating();
        
        
        //QUANTITY
        int quantitySold = productDao.getQuantitySoldByProductId(id);
        int avgRating = feedbackDAO.avgRating(id);
        
        request.setAttribute("avgRating", avgRating);
        request.setAttribute("listMostRating", listMostRating);
        request.setAttribute("listAccount", listAcc);
        request.setAttribute("listAuthor", listAuthor);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("quantitySold", quantitySold);
        request.setAttribute("relatedProduct", listP);
        request.setAttribute("product", product);
        request.setAttribute("objectAge", objectAge);
        request.setAttribute("category", category);
        request.setAttribute("author", author);
        request.setAttribute("news", listNews);

        request.getRequestDispatcher("Views/SingleProduct.jsp").forward(request, response);
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
