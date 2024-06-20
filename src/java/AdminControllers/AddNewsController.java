package AdminControllers;

import Models.News;
import Models.Topic;
import DAL.NewsDao;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/AddNewsController")
@MultipartConfig
public class AddNewsController extends HttpServlet {
    private NewsDao newsDao = new NewsDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Topic> topics = newsDao.getTopic();
        request.setAttribute("topics", topics);
        request.getRequestDispatcher("Views/Admin/AddNews.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
        news.setTopic(topic);
        news.setTitle(title);
        news.setContent(content);
        news.setImgNews1(img1Filename);
        news.setImgNews2(img2Filename);
        news.setDateUpload(java.sql.Date.valueOf(dateUpload));
        news.setSource(source);
        news.setStatus(status);

        newsDao.insertNews(news);

        request.setAttribute("message", "Thêm tin tức thành công");
        processRequest(request, response);
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
    public String getServletInfo() {
        return "Servlet for adding news records";
    }
}
