/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import DAL.CategoryDao;
import DAL.DiscountDAO;
import DAL.FeedbackDAO;
import DAL.HomeDAO;

import DAL.NewsDao;
import Models.Account;
import Models.Category;
import Models.Feedback;
import Models.ImageBackground;
import Models.News;
import Models.Product;
import Models.UsedCoupon;
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
public class HomeControllers extends HttpServlet {

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
            out.println("<title>Servlet HomeControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeControllers at " + request.getContextPath() + "</h1>");
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
           ArrayList<ImageBackground> imageBackground = new ArrayList<>();
        ArrayList<Product> Sellmany = new ArrayList<>();
         ArrayList<Product> dataVanHoc = new ArrayList<>();
          ArrayList<Product> dataNuocNgoai = new ArrayList<>();
           ArrayList<Product> dataChuyenThong = new ArrayList<>();
            ArrayList<Product> dataKhoaHoc = new ArrayList<>();
          ArrayList<UsedCoupon> codename = new ArrayList<>();
          
          DiscountDAO dao = new DiscountDAO();
          codename = dao.Displaycode();
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys = categoryDao.getallCategorys();
        request.setAttribute("category", categorys);
        HomeDAO dal = new HomeDAO();
        dataVanHoc = dal.get3addnew(2);
         dataNuocNgoai = dal.get3addnew(3);
          dataChuyenThong = dal.get3addnew(4);
           dataKhoaHoc= dal.get3addnew(5);
        imageBackground = dal.getImageBackground();
        Sellmany = dal.get6sellmany();

        //Them list, news, feedback cho homepage
        NewsDao nd = new NewsDao();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        AccountDAO accDAO = new AccountDAO();
        List<News> listNews = nd.getFourNewsLated();
        List<Feedback> listMostRating = feedbackDAO.getFeedbackMostRating();
        List<Account> listAcc = accDAO.getAllAccount();

        request.setAttribute("listMostRating", listMostRating);
        request.setAttribute("listAccount", listAcc);
        request.setAttribute("news", listNews);
        request.setAttribute("data1", Sellmany);
        request.setAttribute("imageBG", imageBackground);
        request.setAttribute("data01", dataVanHoc);
        request.setAttribute("data02", dataNuocNgoai);
        request.setAttribute("data03", dataChuyenThong);
        request.setAttribute("data04", dataKhoaHoc);
        request.setAttribute("codediscount", codename);
        request.getRequestDispatcher("Views/Home.jsp").forward(request, response);

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
