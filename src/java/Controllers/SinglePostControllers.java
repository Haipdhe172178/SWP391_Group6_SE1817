package Controllers;

import DAL.CategoryDao;
import DAL.NewsDao;
import Models.Category;
import Models.News;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class SinglePostControllers extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        NewsDao newsDao = new NewsDao();
        News news = newsDao.getNewsById(id);
        CategoryDao categoryDao = new CategoryDao();
        List<Category> cate = categoryDao.getallCategorys();
        request.setAttribute("category", cate);
        request.setAttribute("news", news);
        request.setAttribute("active", "news");
        request.getRequestDispatcher("Views/SinglePost.jsp").forward(request, response);
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
