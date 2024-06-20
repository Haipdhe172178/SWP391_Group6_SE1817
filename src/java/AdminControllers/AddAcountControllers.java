/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.AccountDAO;
import DAL.RoleDao;
import Models.Account;
import Models.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author huyca
 */
@MultipartConfig
public class AddAcountControllers extends HttpServlet {

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
            out.println("<title>Servlet AddAcountControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAcountControllers at " + request.getContextPath() + "</h1>");
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
        RoleDao roleDao = new RoleDao();
        List<Role> role = roleDao.getAllRole();
        AccountDAO accountDAO = new AccountDAO();
        List<Account> account = accountDAO.getAllAccount();
        request.setAttribute("account", account);
        request.setAttribute("role", role);
        request.getRequestDispatcher("/Views/Admin/AddAccount.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        // Retrieve form data
        String fullName = request.getParameter("fullName");
        String userName = request.getParameter("userName");
        String passWord = request.getParameter("passWord");
        String confirmPassWord = request.getParameter("confirmPassWord");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        int roleId = Integer.parseInt(request.getParameter("roleId"));
        Part part = request.getPart("imgAccount");

        // Handle file upload
        String imgProduct = null;
        if (part != null && part.getSize() > 0) {
            String path = request.getServletContext().getRealPath("/img");
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            File image = new File(dir, fileName);
            part.write(image.getAbsolutePath());
            imgProduct = request.getContextPath() + "/img/" + fileName;
        }

        // Check if username or email already exists
        AccountDAO accountDAO = new AccountDAO();
        boolean userNameExists = accountDAO.checkUserNameExists(userName);
        boolean emailExists = accountDAO.checkEmailExists(email);

        if (userNameExists || emailExists) {
            // Set error messages
            if (userNameExists) {
                session.setAttribute("userNameError", "Tên đăng nhập đã tồn tại.");
            }
            if (emailExists) {
                session.setAttribute("emailError", "Email đã tồn tại.");
            }

            session.setAttribute("fullName", fullName);
            session.setAttribute("userName", userName);
            session.setAttribute("passWord", passWord);
            session.setAttribute("confirmPassWord", confirmPassWord);
            session.setAttribute("gender", gender);
            session.setAttribute("email", email);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("address", address);
            session.setAttribute("roleId", roleId);

            response.sendRedirect(request.getContextPath() + "/addac");
            return;
        }
        boolean accountCreated = accountDAO.createAccount(fullName, userName, passWord, gender, email, phoneNumber, address, roleId, imgProduct);                   
        session.setAttribute("notification", "success");
        response.sendRedirect(request.getContextPath() + "/addac");
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
