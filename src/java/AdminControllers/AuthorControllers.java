/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.AuthorDao;
import Models.Author;
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
public class AuthorControllers extends HttpServlet {

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
            out.println("<title>Servlet AuthorControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AuthorControllers at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthorDao ad = new AuthorDao();
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        List<Author> authors;
        int count;
        String statusFilter = request.getParameter("statusFilter");
        String searchKeyword = request.getParameter("s");
        if (statusFilter != null && !statusFilter.isEmpty()) {
            int status = Integer.parseInt(statusFilter);
            authors = ad.getAuthorByStatus(status, index);
            count = ad.getTotalBySatus(status);
        } else if (searchKeyword != null && !searchKeyword.isEmpty()) {
            authors = ad.pagingSearchAuthor(index, searchKeyword);
            count = ad.getTotalAuthorsByKeyword(searchKeyword);
        } else {
            authors = ad.pagingAuthors(index);
            count = ad.getTotalAuthors();
        }
        int endPage = count / 8;
        if (count % 8 != 0) {
            endPage++;
        }

        String query = "";
        if (searchKeyword != null) {
            query += "&s=" + searchKeyword;
        }
        if (statusFilter != null) {
            query += "&&statusFilter=" + statusFilter;
        }
        request.setAttribute("query", query);
        request.setAttribute("author", authors);
        request.setAttribute("endP", endPage);
        request.setAttribute("tag", index);

        request.getRequestDispatcher("Views/Admin/Author.jsp").forward(request, response);
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
//        try (PrintWriter out = response.getWriter()) {
//        String search = request.getParameter("search");
//        AuthorDao authorDao = new AuthorDao();
//        // Assume AuthorDAO is your data access object class
//        List<Author> authors = (List<Author>) authorDao.getAuthorByKeyword(search);
//        out.println("<ul>");
//        for (Author author : authors) {
//            out.println("<li>" + author.getAuthorName()+ "</li>");
//        }
//        out.println("</ul>");
//    }
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
