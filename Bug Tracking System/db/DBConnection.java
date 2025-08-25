package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Update the URL, user, and password as per your MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/BDS"; // Replace 'BDS' with your DB name
    private static final String USER = "root";                           // Replace with your MySQL username
    private static final String PASSWORD = "System@123";       // Replace with your MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connection successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ Failed to connect to the database.");
            e.printStackTrace();
        }
        return conn;
    }

    // Test connection
    public static void main(String[] args) {
        getConnection();
    }
}
