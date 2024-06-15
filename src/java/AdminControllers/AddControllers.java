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
public class AddControllers extends HttpServlet {

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
            out.println("<title>Servlet AddControllers</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddControllers at " + request.getContextPath() + "</h1>");
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
        CategoryDao categoryDao = new CategoryDao();
        AuthorDao authorDao = new AuthorDao();
        ObjectAgeDao oad = new ObjectAgeDao();

        List<Category> categorys = categoryDao.getallCategorys();
        List<ObjectAge> oas = oad.getallObjectAges();
        List<Author> au = authorDao.getallAuthors();

        request.setAttribute("category", categorys);
        request.setAttribute("obage", oas);
        request.setAttribute("author", au);
        request.getRequestDispatcher("Views/Admin/AddProduct.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            String productName = request.getParameter("name");
            String priceString = request.getParameter("price");
            String quantityString = request.getParameter("quantity");
            String description = request.getParameter("description");
            String categoryIdString = request.getParameter("categoryId");
            String authorIdString = request.getParameter("author");
            String ageIdString = request.getParameter("ageId");

            boolean hasError = false;

            if (productName == null || productName.isBlank()) {
                session.setAttribute("errorName", "Name cannot be empty or blank");
                hasError = true;
            }
            if (description == null || description.isBlank()) {
                session.setAttribute("errorDescription", "Description cannot be empty or blank");
                hasError = true;
            }

            float productPrice = 0;

            productPrice = Float.parseFloat(priceString);
            if (productPrice <= 0) {
                session.setAttribute("errorPrice", "Price must be greater than zero");
                hasError = true;
            }

            int productQuantity = 0;

            productQuantity = Integer.parseInt(quantityString);
            if (productQuantity <= 0) {
                session.setAttribute("errorQuantity", "Quantity must be greater than zero");
                hasError = true;
            }

            int categoryId = 0;
            int authorId = 0;
            int ageId = 0;
            try {
                categoryId = Integer.parseInt(categoryIdString);
                authorId = Integer.parseInt(authorIdString);
                ageId = Integer.parseInt(ageIdString);
            } catch (NumberFormatException e) {
                hasError = true;
            }

            session.setAttribute("name", productName);
            session.setAttribute("price", priceString);
            session.setAttribute("quantity", quantityString);
            session.setAttribute("description", description);
            session.setAttribute("categoryId", categoryIdString);
            session.setAttribute("author", authorIdString);
            session.setAttribute("ageId", ageIdString);

            if (hasError) {
                session.setAttribute("errorImage", "Please re-upload the image");
                session.setAttribute("notification", "error");
                response.sendRedirect(request.getContextPath() + "/add");
                return;
            }

            Part part = request.getPart("imgProduct");
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

            ProductDao productDao = new ProductDao();
            if (productName != null) {
                Product existingProduct = productDao.getProductByName(productName);
                if (existingProduct != null) {
                    session.setAttribute("notification", "error");
                    session.setAttribute("errorMessage", "Product already exists");
                } else {
                    Product product = new Product();
                    product.setName(productName);
                    product.setPrice(productPrice);
                    product.setQuantity(productQuantity);
                    product.setDescription(description);
                    product.setImgProduct(imgProduct);
                    Category category = new Category();
                    category.setCategoryId(categoryId);
                    product.setCategory(category);
                    Author author = new Author();
                    author.setAuthorID(authorId);
                    product.setAuthor(author);
                    ObjectAge objectAge = new ObjectAge();
                    objectAge.setAgeId(ageId);
                    product.setOage(objectAge);

                    productDao.addProduct(product);
                    session.setAttribute("notification", "success");

                    session.removeAttribute("name");
                    session.removeAttribute("price");
                    session.removeAttribute("quantity");
                    session.removeAttribute("description");
                    session.removeAttribute("categoryId");
                    session.removeAttribute("author");
                    session.removeAttribute("ageId");
                    session.removeAttribute("errorName");
                    session.removeAttribute("errorPrice");
                    session.removeAttribute("errorQuantity");
                    session.removeAttribute("errorDescription");
                    session.removeAttribute("errorImage");
                    session.removeAttribute("errorMessage");
                }
            }
            response.sendRedirect(request.getContextPath() + "/add");

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
