package DAL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBContext {
    protected Connection connection;
    public DBContext()
    {
        try {
            // Edit URL , username, password to authenticate with your MS SQL Server
            String url = "jdbc:sqlserver://localhost:1433;databaseName= SB88";
            String username = "sa";
            String password = "123";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
           ex.printStackTrace();
            System.out.println(ex);
        }
    }
    public static void main(String[] args) {
        try {
            DBContext dbContext = new DBContext();
            
            if (dbContext.connection != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Connection failed.");
            }

            dbContext.connection.close();
            System.out.println("Connection closed.");
        } catch (SQLException ex) {
            System.out.println("Error closing connection: " + ex);
        }
    }
}
