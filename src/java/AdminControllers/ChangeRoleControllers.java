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
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author huyca
 */
@MultipartConfig
public class ChangeRoleControllers extends HttpServlet {

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
            out.println("<title>Servlet ChangeRoleControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeRoleControllers at " + request.getContextPath() + "</h1>");
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
        String accountId = request.getParameter("accountId");

        int accountIds = Integer.parseInt(accountId);
        AccountDAO accountDAO = new AccountDAO();
        Account account = accountDAO.getAccountByid(accountIds);
        RoleDao roleDao = new RoleDao();
        List<Role> role = roleDao.getAllRole();
        request.setAttribute("acc", account);
        request.setAttribute("role", role);
        request.getRequestDispatcher("Views/Admin/ChangeRole.jsp").forward(request, response);
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

        int accountId = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("name");
        int roleId = Integer.parseInt(request.getParameter("roleID"));
        String userName = request.getParameter("userName");
        String password = request.getParameter("passWord");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String statusStr = request.getParameter("status");
        int status = Integer.parseInt(statusStr);

        Part part = request.getPart("imgAccount");
        String imgProduct = null;
        boolean isValidImage = true;

        if (part != null && part.getSize() > 0) {
            String contentType = part.getContentType();
            String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            if (contentType == null || !contentType.startsWith("image/")
                    || !fileExtension.matches("jpg|jpeg|png|gif|svg")) {
                session.setAttribute("imgAccountError", "File tải lên phải là hình ảnh có định dạng JPG, JPEG, PNG, GIF hoặc SVG.");
                isValidImage = false;
            } else {
                String path = request.getServletContext().getRealPath("/img");
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File image = new File(dir, fileName);
                part.write(image.getAbsolutePath());
                imgProduct = request.getContextPath() + "/img/" + fileName;
            }
        } else {
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getAccountByid(accountId);
            imgProduct = account.getImgAccount();
        }

        if (!isValidImage) {
            response.sendRedirect(request.getContextPath() + "/change?accountId=" + accountId);
            return;
        }

        AccountDAO accountDAO = new AccountDAO();
        Account oldAccount = accountDAO.getAccountByid(accountId);
        String oldEmail = oldAccount.getEmail();
        String oldUserName = oldAccount.getUserName();

        boolean emailExists = accountDAO.checkEmailExists(email);
        boolean userNameExists = accountDAO.checkUserNameExists(userName);

        if ((emailExists && !email.equals(oldEmail)) || (userNameExists && !userName.equals(oldUserName))) {
            if (emailExists && !email.equals(oldEmail)) {
                session.setAttribute("emailError", "Email đã tồn tại.");
            }
            if (userNameExists && !userName.equals(oldUserName)) {
                session.setAttribute("userNameError", "Tên đăng nhập đã tồn tại.");
            }
            session.setAttribute("fullName", fullName);
            session.setAttribute("roleId", roleId);
            session.setAttribute("userName", userName);
            session.setAttribute("password", password);
            session.setAttribute("gender", gender);
            session.setAttribute("email", email);
            session.setAttribute("phoneNumber", phoneNumber);
            session.setAttribute("address", address);
            session.setAttribute("status", status);
            response.sendRedirect(request.getContextPath() + "/change?accountId=" + accountId);
            return;
        }
         String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt()); 
        boolean isUpdated = accountDAO.updateStaff(accountId, fullName, userName, hashPassword, gender, email, phoneNumber, address, roleId, imgProduct, status);
        if (isUpdated) {
             session.removeAttribute("imgAccountError");
            session.setAttribute("notification", "success");
        } else {
            session.setAttribute("notification", "failure");
        }

        response.sendRedirect(request.getContextPath() + "/change?accountId=" + accountId);
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
