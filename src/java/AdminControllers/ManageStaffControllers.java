package AdminControllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAL.AccountDAO;
import DAL.RoleDao;
import Models.Account;
import Models.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author huyca
 */
public class ManageStaffControllers extends HttpServlet {

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
            out.println("<title>Servlet ManageStaffControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageStaffControllers at " + request.getContextPath() + "</h1>");
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
        String indexPage = request.getParameter("index");
        int index;
        if (indexPage != null) {
            index = Integer.parseInt(indexPage);
        } else {
            index = 1;
        }

        final int roleId = 2;
        List<Account> account;
        int count;
        int endPage;

        String statusFilter = request.getParameter("statusFilter");
        String searchKeyword = request.getParameter("s");

        if (statusFilter != null && !statusFilter.isEmpty()) {
            int status = Integer.parseInt(statusFilter);
            account = accountDAO.getAccountsByStatus(status, index, roleId);
            count = accountDAO.getTotalAccountsByStatus(status, roleId);
        } else if (searchKeyword != null && !searchKeyword.isEmpty()) {
            account = accountDAO.searchAccounts(searchKeyword, index, roleId);
            count = accountDAO.getTotalAccountsByKeyword(searchKeyword, roleId);
        } else {
            account = accountDAO.pagingAccounts(index, roleId);
            count = accountDAO.getTotalAccounts(roleId);
        }

        endPage = count / 5;
        if (count % 5 != 0) {
            endPage++;
        }

        String query = "";
        if (searchKeyword != null) {
            query += "&&s=" + searchKeyword;
        }
        if (statusFilter != null) {
            query += "&&statusFilter=" + statusFilter;
        }

        request.setAttribute("query", query);
        request.setAttribute("endP", endPage);
        request.setAttribute("account", account);
        request.setAttribute("role", role);
        request.getRequestDispatcher("Views/Admin/ManageStaff.jsp").forward(request, response);
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
        processRequest(request, response);
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
