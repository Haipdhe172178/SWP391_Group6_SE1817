package Controllers;

import DAL.AccountDAO;
import org.mindrot.jbcrypt.BCrypt; // Sử dụng thư viện này cho hàm hashpw
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("Views/Register.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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

            request.setAttribute("notification", "Tất cả các trường đều bắt buộc. Vui lòng điền đầy đủ.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (accountDAO.checkUserNameExists(userName)) {
            request.setAttribute("notification", "Tài khoản đã tồn tại. Vui lòng nhập tài khoản khác.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (accountDAO.checkEmailExists(email)) {
            request.setAttribute("notification", "Email đã tồn tại. Vui lòng nhập email khác.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        if (!password.equals(rePassword)) {
            request.setAttribute("notification", "Mật khẩu không khớp. Vui lòng thử lại.");
            setFormData(request, fullName, userName, email, phoneNumber, address);
            processRequest(request, response);
            return;
        }

        //Mã hóa mật khẩu
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt()); 

        boolean userCreated = accountDAO.createUser(fullName, userName, hashPassword, email, phoneNumber, address); 

        if (userCreated) {
            response.sendRedirect("login?success=true");
        } else {
            request.setAttribute("notification", "Lỗi tạo tài khoản. Vui lòng thử lại.");
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
