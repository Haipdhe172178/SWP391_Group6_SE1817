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
        String sortBy = request.getParameter("sortBy");
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        CategoryDao categoryDao = new CategoryDao();
        ProductDao productDao = new ProductDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao objectAgeDao = new ObjectAgeDao();

        List<Author> authors = authorDao.getallAuthors();
        List<Category> categories = categoryDao.getallCategorys();
        List<ObjectAge> objectAges = objectAgeDao.getallObjectAges();

        List<Product> products;
        int count;

        if (sortBy == null || sortBy.isEmpty()) {
            products = productDao.pagingProducts(index);
            count = productDao.getTotalProduct();
        } else {
            switch (sortBy) {
                case "name_asc":
                    products = productDao.pagingProductsSortedByName(index, true);
                    count = productDao.getTotalProduct();  
                    break;
                case "name-desc":
                    products = productDao.pagingProductsSortedByName(index, false);
                    count = productDao.getTotalProduct();  
                    break;
                case "price_asc":
                    products = productDao.pagingProductsSortedByPrice(index, true);
                    count = productDao.getTotalProduct();  
                    break;
                case "price_desc":
                    products = productDao.pagingProductsSortedByPrice(index, false);
                    count = productDao.getTotalProduct(); 
                    break;
                default:
                    products = productDao.pagingProducts(index);
                    count = productDao.getTotalProduct();
                    break;
            }
        }

        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

        request.setAttribute("ListA", products);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);
        request.setAttribute("author", authors);
        request.setAttribute("product", products);
        request.setAttribute("category", categories);
        request.setAttribute("objage", objectAges);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("count", count);
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
