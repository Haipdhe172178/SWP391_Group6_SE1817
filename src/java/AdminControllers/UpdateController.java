/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package AdminControllers;

import DAL.AuthorDao;
import DAL.CategoryDao;
import DAL.ObjectAgeDao;
import DAL.ProductDao;
import Models.Author;
import Models.Category;
import Models.ObjectAge;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author USER
 */
@MultipartConfig
public class UpdateController extends HttpServlet {

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
            out.println("<title>Servlet UpdateController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateController at " + request.getContextPath() + "</h1>");
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

        String meo = request.getParameter("meo") == null ? "" : request.getParameter("meo");
        String error = "";
        if (meo.equals("1")) {
            error = "Bạn phải nhập giá tiền hoặc số lượng sản phẩm lớn hơn 0 ";
        }
        if (meo.equals("2")) {
            error = "Bạn không được bỏ trống ô nào cả ";
        }

        ProductDao dal = new ProductDao();
        Product p = new Product();
        p = dal.get1Productbyid(id);
        CategoryDao categoryDao = new CategoryDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao oad = new ObjectAgeDao();

        List<Category> categorys = categoryDao.getallCategorys();
        List<ObjectAge> oas = oad.getallObjectAges();
        List<Author> au = authorDao.getallAuthors();

        request.setAttribute("category", categorys);
        request.setAttribute("obage", oas);
        request.setAttribute("author", au);
        request.setAttribute("error", error);
        request.setAttribute("data", p);
        request.getRequestDispatcher("Views/Admin/UpdateProduct.jsp").forward(request, response);
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
        try {
            String id = request.getParameter("ID");
            request.setAttribute("ID", id);
            int ids = Integer.parseInt(id);
            String productName = request.getParameter("name");
            request.setAttribute("name", productName);
            float productPrice = Float.parseFloat(request.getParameter("price"));
            request.setAttribute("price", productPrice);
            int productQuantity = Integer.parseInt(request.getParameter("quantity"));
            request.setAttribute("quantity", productQuantity);
            String description = request.getParameter("description");
            request.setAttribute("description", description);
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            request.setAttribute("category", categoryId);
            int authorId = Integer.parseInt(request.getParameter("author"));
            request.setAttribute("author", authorId);
            int ageId = Integer.parseInt(request.getParameter("ageId"));
            request.setAttribute("ageId", ageId);
            int status = Integer.parseInt(request.getParameter("status"));
            request.setAttribute("status", status);
            // Xử lý ảnh
            String imgProduct = null;

            Part part = request.getPart("imgProduct");
            if (part != null && part.getSize() > 0) {

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

            } else {
                imgProduct = request.getParameter("imgProduct12");
            }

            // Tạo đối tượng Product mới
            Product product = new Product();

            product.setName(productName);
            product.setPrice(productPrice);
            product.setQuantity(productQuantity);
            product.setDescription(description);
            Category category = new Category();
            category.setCategoryId(categoryId);
            product.setCategory(category);
            Author author = new Author();
            author.setAuthorID(authorId);
            product.setAuthor(author);
            ObjectAge objectAge = new ObjectAge();
            objectAge.setAgeId(ageId);
            product.setStatus(status);
            product.setOage(objectAge);
            product.setImgProduct(imgProduct);
            product.setProductId(ids);

            // Thêm sản phẩm vào cơ sở dữ liệu
            ProductDao productDao = new ProductDao();
            //productDao.updateProduct(product);
            if (productName.isBlank() || description.isBlank()) {
                response.sendRedirect("update" + "?id=" + id + "&meo=2");
            } else {
                if (productPrice > 0 && productQuantity > 0) {
                    productDao.updateProduct(product);
                    response.sendRedirect(request.getContextPath() + "/data");
                } else {

                    response.sendRedirect("update" + "?id=" + id + "&meo=1");
                }
            }

        } catch (NumberFormatException | IOException | ServletException ex) {
            ex.printStackTrace();
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
