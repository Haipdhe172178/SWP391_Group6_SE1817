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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author huyca
 */
public class UpdateAuthorControllers extends HttpServlet {

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
            out.println("<title>Servlet UpdateAuthorControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAuthorControllers at " + request.getContextPath() + "</h1>");
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
        String authorId = request.getParameter("id");
        int authorIds = Integer.parseInt(authorId);
        AuthorDao authorDao = new AuthorDao();
        Author au = authorDao.getAuthorById(authorIds);
        request.setAttribute("author", au);
        request.getRequestDispatcher("Views/Admin/UpdateAuthor.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        int authorID = Integer.parseInt(request.getParameter("id"));
        String authorName = request.getParameter("name");
        String authorDescription = request.getParameter("description");
        int authorStatus = Integer.parseInt(request.getParameter("status"));
        AuthorDao authorDao = new AuthorDao();
        Author oldAuthor = authorDao.getAuthorById(authorID);
        String oldName = oldAuthor.getAuthorName();
        
        boolean authorNameExists = authorDao.getAuthor(authorName);
        
        if (authorNameExists && !authorName.contains(oldName)) {
            session.setAttribute("description", authorDescription);
            session.setAttribute("authorName", authorName);
            session.setAttribute("notification", "error");
            session.setAttribute("errorMessage", "Tác giả đã tồn tại: " + authorName);
            response.sendRedirect(request.getContextPath() + "/updatea?id=" + authorID);
            return;
        }
        Author author = new Author(authorID, authorName, authorDescription, authorStatus);
        authorDao.updateAuthor(author);
        session.removeAttribute("errorMessage");
        session.setAttribute("notification", "success");

        response.sendRedirect(request.getContextPath() + "/updatea?id=" + authorID);
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
