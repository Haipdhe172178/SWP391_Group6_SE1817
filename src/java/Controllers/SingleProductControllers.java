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
        String rating = request.getParameter("rating");
        //DAO
        ProductDao productDao = new ProductDao();
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        List<Feedback> listFeedback;

        /*Pagination*/
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }
        
        int quantity = 0;
        //filter by rating
        if (rating != null && !rating.isEmpty()) {
            int filterRate = Integer.parseInt(rating);
            listFeedback = feedbackDAO.getFeedbackByProductId(id, page, filterRate);
            quantity = feedbackDAO.getQuantityFeedbacksByPid(id, rating);
        } else {
            listFeedback = feedbackDAO.getFeedbackByProductId(id, page);
            quantity = feedbackDAO.getQuantityFeedbacksByPid(id, "");
        }
        //Get Feedback
        int endPage = (quantity / 5) + (quantity % 5 == 0 ? 0 : 1);

        /*End Pagination*/
        Product product = productDao.getProductById(id);
        
        //LIST
        List<Product> listP = productDao.getProductsByCategoryId(product.getCategory().getCategoryId(), "fourRandom");

        //QUANTITY sold product
        int quantitySold = productDao.getQuantitySoldByProductId(id);
        int avgRating = feedbackDAO.avgRating(id);

        request.setAttribute("rating", rating);
        request.setAttribute("quantityFeedback", quantity);
        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("productStatus", "product");
        request.setAttribute("avgRating", avgRating);
        request.setAttribute("listFeedback", listFeedback);
        request.setAttribute("quantitySold", quantitySold);
        request.setAttribute("relatedProduct", listP);
        request.setAttribute("product", product);

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
