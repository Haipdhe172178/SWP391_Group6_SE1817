package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS TUF
 */
public class RegisterServlet extends HttpServlet {

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
        request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
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
        processRequest(request, response);
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
        String fullName = request.getParameter("name");
        String userName = request.getParameter("username");
        String password = request.getParameter("pass");
        String rePassword = request.getParameter("re_pass");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        AccountDAO accountDAO = new AccountDAO();

        if (fullName == null || fullName.isEmpty()
                || userName == null || userName.isEmpty()
                || password == null || password.isEmpty()
                || rePassword == null || rePassword.isEmpty()
                || email == null || email.isEmpty()
                || phoneNumber == null || phoneNumber.isEmpty()
                || address == null || address.isEmpty()) {

            request.setAttribute("notification", "All fields are required. Please fill in all fields.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (accountDAO.checkUserNameExists(userName)) {
            request.setAttribute("notification", "Tài khoản đã tồn tại. Làm ơn nhập tài khoản khác.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (accountDAO.checkEmailExists(email)) {
            request.setAttribute("notification", "Email đã tồn tại. Làm ơn nhập email khác.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (!password.equals(rePassword)) {
            request.setAttribute("notification", "Mật khẩu không đúng. Làm ơn thử lại.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        boolean userCreated = accountDAO.createUser(fullName, userName, password, email, phoneNumber, address);

        if (userCreated) {
            response.sendRedirect("login?success=true");
        } else {
            request.setAttribute("notification", "Lỗi tạo tài khoản. Làm ơn thử lại.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
        }
    }

    private void setFormData(HttpServletRequest request, String fullName, String userName, String email, String phoneNumber, String address) {
        request.setAttribute("name", fullName);
        request.setAttribute("username", userName);
        request.setAttribute("email", email);
        request.setAttribute("phoneNumber", phoneNumber);
        request.setAttribute("address", address);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
