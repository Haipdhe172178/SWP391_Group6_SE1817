package Controllers;

import DAL.AccountDAO;
import Models.Account;
import Models.Constants;
import Models.GoogleUserDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

@WebServlet(name = "LoginGoogleHandler", urlPatterns = {"/LoginGoogleHandler"})
public class LoginGoogleHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account gAccount = loginWithGoogle(request);
        if (gAccount != null) {
            HttpSession session = request.getSession();
            session.setAttribute("account", gAccount);
            response.sendRedirect("home");
        } else {
            
            response.sendRedirect("login");
        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form()
                        .add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI)
                        .add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE)
                        .build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        return jobj.get("access_token").toString().replaceAll("\"", "");
    }

    public static GoogleUserDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        return new Gson().fromJson(response, GoogleUserDto.class);
    }

    private Account loginWithGoogle(HttpServletRequest request) {
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);
            GoogleUserDto googleUser = getUserInfo(accessToken);

            if (googleUser == null) {
                return null;
            }

            AccountDAO accountDAO = new AccountDAO();
            Account account = new Account();
            if (!accountDAO.checkEmailExists(googleUser.getEmail())) {
                account.setUserName(googleUser.getEmail());
                account.setPassWord("12345678");
                account.setEmail(googleUser.getEmail());
                account.setRoleId(3);  // Assuming 3 is the role ID for a standard user
                account.setFullName(googleUser.getGiven_name());
                account.setPhoneNumber("");
                account.setAddress("");
                accountDAO.createUser(account.getFullName(), account.getUserName(), account.getPassWord(), account.getEmail(), account.getPhoneNumber(), account.getAddress());
            }
                account = accountDAO.getAccountByEmail(googleUser.getEmail());
            return account;
        } catch (IOException ex) {
            Logger.getLogger(LoginGoogleHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
