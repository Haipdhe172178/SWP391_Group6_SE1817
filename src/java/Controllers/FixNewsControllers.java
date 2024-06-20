package Controllers;

import Models.News;
import Models.Topic;
import DAL.NewsDao;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig
public class FixNewsControllers extends HttpServlet {
    private NewsDao newsDao = new NewsDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            News news = newsDao.getNewsById(id);
            List<Topic> topics = newsDao.getTopic();
            request.setAttribute("news", news);
            request.setAttribute("topics", topics);
            RequestDispatcher rd = request.getRequestDispatcher("Views/Admin/fixNews.jsp");
            rd.forward(request, response);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("newsId"));
            String title = request.getParameter("title");
            String dateUpload = request.getParameter("dateUpload");
            String content = request.getParameter("content");
            String source = request.getParameter("source");
            int topicId = Integer.parseInt(request.getParameter("topicId"));
            boolean status = Boolean.parseBoolean(request.getParameter("status"));

            Part img1Part = request.getPart("img1");
            Part img2Part = request.getPart("img2");

            String img1Filename = uploadFile(img1Part, request);
            String img2Filename = uploadFile(img2Part, request);

            Topic topic = new Topic();
            topic.setTopicId(topicId);
            News news = new News();
            news.setNewId(id);
            news.setTopic(topic);
            news.setTitle(title);
            news.setContent(content);
            news.setImgNews1(img1Filename);
            news.setImgNews2(img2Filename);
            news.setDateUpload(java.sql.Date.valueOf(dateUpload));
            news.setSource(source);
            news.setStatus(status);

            newsDao.updateNews(news);

            request.setAttribute("message", "Cập nhật tin tức thành công");
            response.sendRedirect("upnews");
        }
    }

    private String uploadFile(Part part, HttpServletRequest request) throws IOException {
        String fileName = extractFileName(part);
        String savePath = getServletContext().getRealPath("/") + "uploads" + File.separator + fileName;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.getParentFile().exists()) {
            fileSaveDir.getParentFile().mkdirs();
        }
        part.write(savePath);
        return "uploads/" + fileName;
    }

    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
        return "Servlet to handle the update of news records.";
    }
}
