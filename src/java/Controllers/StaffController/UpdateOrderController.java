/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers.StaffController;

import DAL.OrderDao;
import DAL.ProductDao;
import Models.Item;
import Models.OrderDetailGuest;
import Models.Orders;
import Models.Product;
import SendEmail.SendEmail;
import static SendEmail.SendEmail.sendEmailConfirmAdmin;
import static SendEmail.SendEmail.sendEmailConfirmAdminHP;
import static SendEmail.SendEmail.sendEmailConfirmAdminHuy;
import static SendEmail.SendEmail.sendEmailConfirmAdminVC;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class UpdateOrderController extends HttpServlet {

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
            out.println("<title>Servlet UpdateOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateOrderController at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        String accountid = request.getParameter("acid");

        OrderDao dal = new OrderDao();
        Orders order = new Orders();
        List<Product> list = new ArrayList<>();
        ProductDao dao = new ProductDao();
        if (!(accountid.equals("0"))) {
            order = dal.getOrderCusById(Integer.parseInt(id));
            list = dao.getProductByCOrder(Integer.parseInt(id));
        } else {
            order = dal.getOrderGetByID(Integer.parseInt(id));
            list = dao.getProductByGOrder(Integer.parseInt(id));
        }
        String data[] = {"Chờ xác nhận", "Đã xác nhận", "Chờ giao hàng", "Hoàn thành", "Đã hủy"};
        List<String> status = new ArrayList<>();
        status.add(data[order.getStatus() - 1]);
        status.add(data[order.getStatus()]);
        status.add(data[4]);

        request.setAttribute("list", list);
        request.setAttribute("order", order);
        request.setAttribute("listStatus", status);
        request.getRequestDispatcher("Views/Staff/updateOrder.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String payment = request.getParameter("payment");
        String status = request.getParameter("status");
        String totalPrice = request.getParameter("total");
        String orderID = request.getParameter("orderID");
        String statusold_raw = request.getParameter("statusold");
        String accountID = request.getParameter("accID");
        int statusold = Integer.parseInt(statusold_raw);
        String data1[] = {"Chờ xác nhận", "Đã xác nhận", "Chờ giao hàng", "Hoàn thành", "Đã hủy"};
//        int stt =0;
        int statusId = 0;
        for (int i = 0; i < 5; i++) {
            if (data1[i].equals(status)) {
                statusId = ++i;
                break;
            }
        }
        OrderDao dao = new OrderDao();

        Orders orders = new Orders(0, Integer.parseInt(orderID), name, email, phone, address, Float.parseFloat(totalPrice),
                null, statusId, Integer.parseInt(payment), Integer.parseInt(accountID));

        dao.updateOrder(orders, status);
        List<OrderDetailGuest> list = dao.getAllByOrderId(Integer.parseInt(orderID), orders.getAccountID());

        if (status.equals(data1[4])) {
            
                for (OrderDetailGuest od : list) {
                    dao.updateProductQuantity(od.getProductId(), od.getQuantity(), "+");


                String gmail = dao.getEmailByOrderId(Integer.parseInt(orderID), orders.getAccountID());
                SendEmail sd = new SendEmail();
                sd.sendEmail(gmail, "Book88", sendEmailConfirmAdminHuy(Integer.parseInt(orderID)));
                }
            }else if(status.equals(data1[3])){
                  String gmail = dao.getEmailByOrderId(Integer.parseInt(orderID), orders.getAccountID());
                SendEmail sd = new SendEmail();
                sd.sendEmail(gmail, "Book88", sendEmailConfirmAdminHP(Integer.parseInt(orderID)));
            }

            else if(status.equals(data1[2])) {
          
                //update quantity
                // -
//                for (OrderDetailGuest od : list) {
//                    dao.updateProductQuantity(od.getProductId(), od.getQuantity(), "-");
//                }
//                String gmail = dao.getEmailByOrderId(Integer.parseInt(orderID), orders.getAccountID());
//                SendEmail sd = new SendEmail();
//                sd.sendEmail(gmail, "Book88", sendEmailConfirmAdmin(Integer.parseInt(orderID)));
            

            String gmail = dao.getEmailByOrderId(Integer.parseInt(orderID), orders.getAccountID());
            SendEmail sd = new SendEmail();
            sd.sendEmail(gmail, "Book88", sendEmailConfirmAdminVC(Integer.parseInt(orderID)));

        }else{
                  String gmail = dao.getEmailByOrderId(Integer.parseInt(orderID), orders.getAccountID());
          SendEmail sd = new SendEmail();
             sd.sendEmail(gmail, "BookBook88", sendEmailConfirmAdmin(Integer.parseInt(orderID)));
            }
       
        response.sendRedirect("staffdashboard");
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
