/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AccountDAO;
import DAL.CategoryDao;
import Models.Account;
import Models.Category;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author Hai Pham
 */
@MultipartConfig
public class ProfileController extends HttpServlet {

    public static final String PROFILE_PAGE = "Views/Profile.jsp";
    public static final String MESSAGE_SUCCESS = "messageSuccess";
    public static final String MESSAGE_FAIL = "messageFail";

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
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
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
        if (request.getSession().getAttribute("account") != null) {
            CategoryDao categoryDao = new CategoryDao();
            List<Category> categorys = categoryDao.getallCategorys();
            request.setAttribute("cate", categorys);
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
        } else {
            response.sendRedirect("home");
        }

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

        String action = request.getParameter("action");
        AccountDAO accDAO = new AccountDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if (a == null) {
            response.sendRedirect("home");
            return;
        }
        CategoryDao categoryDao = new CategoryDao();
        List<Category> categorys = categoryDao.getallCategorys();
        request.setAttribute("cate", categorys);
        boolean isCompleted;
        switch (action) {
            //Xử lí ảnh avt
            // Xử lí ảnh avt
            case "changeAvt":
            try {
                Part part = request.getPart("avatar");
                String imgURL = null;
                if (part != null && part.getSize() > 0) {
                    String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
                    String fileExtension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

                    // Check if the file extension is either png or jpg
                    if (fileExtension.equals("png") || fileExtension.equals("jpg")) {
                        String realPath = request.getServletContext().getRealPath("/img_account");

                        if (!Files.exists(Path.of(realPath))) {
                            Files.createDirectories(Path.of(realPath));
                        }
                        part.write(realPath + "/" + filename);
                        imgURL = "img_account/" + filename;

                        isCompleted = accDAO.updateAvatar(a, imgURL);
                        if (isCompleted) {
                            a = accDAO.check(a.getUserName());
                            session.setAttribute("account", a);
                            request.setAttribute(MESSAGE_SUCCESS, "Cập nhật thành công");
                        } else {
                            request.setAttribute(MESSAGE_FAIL, "Cập nhật thất bại");
                        }
                    } else {
                        request.setAttribute(MESSAGE_FAIL, "Vui lòng chỉ chọn những tệp JPG hoặc PNG");
                    }
                } else {
                    request.setAttribute(MESSAGE_FAIL, "Vui lòng chọn ảnh");
                }
            } catch (Exception e) {
                request.setAttribute(MESSAGE_FAIL, "Cập nhật thất bại");
            }
            request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
            break;

            //Xử lí thông tin account
            case "changeInfo":
                String fullname = request.getParameter("fullname");
                String gender = request.getParameter("gender");
                String phone = request.getParameter("phonenumber");
                String address = request.getParameter("address");

                a.setFullName(fullname);
                a.setGender(gender);
                a.setPhoneNumber(phone);
                a.setAddress(address);
                isCompleted = accDAO.updateAccountInfo(a);
                if (isCompleted) {
                    session.setAttribute("account", a);
                    request.setAttribute(MESSAGE_SUCCESS, "Cập nhật thành công");
                } else {
                    request.setAttribute(MESSAGE_FAIL, "Cập nhật thất bại");
                }
                request.getRequestDispatcher(PROFILE_PAGE).forward(request, response);
                break;
        }
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
