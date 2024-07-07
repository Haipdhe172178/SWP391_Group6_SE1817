/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.CategoryDao;
import DAL.NewsDao;
import Models.Category;
import Models.News;
import Models.Topic;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author huyca
 */
public class BlogControllers extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogControllers at " + request.getContextPath() + "</h1>");
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
        //Get topicID
        String tidRaw = request.getParameter("id");

        //Get page number
        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        //DAO
        NewsDao newsDao = new NewsDao();
        List<Topic> listTopic = newsDao.getTopic();
        List<News> listNews;

        //quantity of news
        int quantity;
        String sort = request.getParameter("sort");
        if (sort == null) {
            sort = "decrease";
        }
        //Get all or get by cateID
        if (tidRaw == null || tidRaw.isEmpty() || tidRaw.equals("0")) {
            listNews = newsDao.getNewsPagination(page, sort);
            quantity = newsDao.getAllNews().size();
        } else {
            int id = Integer.parseInt(tidRaw);
            request.setAttribute("tid", id);
            listNews = newsDao.getNewsPaginationByTopic(page, id, sort);
            quantity = newsDao.getNewsByCateId(id).size();
        }

        //Page cuối cùng
        int endPage = (quantity / 6) + (quantity % 6 == 0 ? 0 : 1);

        //Khởi tạo 
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys = categoryDao.getallCategorys();
        request.setAttribute("active", "news");

        request.setAttribute("cate", categorys);
        request.setAttribute("sortNews", sort);
        request.setAttribute("page", page);
        request.setAttribute("endPage", endPage);
        request.setAttribute("quantityNews", quantity);
        request.setAttribute("listNews", listNews);
        request.setAttribute("listTopic", listTopic);
        request.getRequestDispatcher("Views/Blog.jsp").forward(request, response);
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
