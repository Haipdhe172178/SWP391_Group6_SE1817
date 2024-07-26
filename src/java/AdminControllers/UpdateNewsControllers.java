package AdminControllers;

import DAL.NewsDao;
import Models.News;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateNewsControllers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int page = 1;
        int recordsPerPage = 6;
        String sortOrder = "decrease";
        String searchTerm = request.getParameter("s");
        String status = request.getParameter("status");
        int topicId = request.getParameter("topicId") != null ? Integer.parseInt(request.getParameter("topicId")) : -1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        if (request.getParameter("sortOrder") != null) {
            sortOrder = request.getParameter("sortOrder");
        }

        NewsDao newsDAO = new NewsDao();
        List<News> listNews;
        int totalRecords;

        if (searchTerm != null && !searchTerm.isEmpty()) {
            listNews = newsDAO.searchNews(searchTerm, page, sortOrder);
            totalRecords = newsDAO.getSearchCount(searchTerm);
        } else {
            listNews = newsDAO.getFilteredNews(status, topicId, page, sortOrder);
            totalRecords = newsDAO.getFilteredNewsCount(status, topicId);
        }

        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("listNews", listNews);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("sortOrder", sortOrder);
        request.setAttribute("searchTerm", searchTerm);
        request.setAttribute("status", status);
        request.setAttribute("topicId", topicId);
        request.setAttribute("topics", newsDAO.getTopic());
        request.getRequestDispatcher("Views/Admin/News.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
