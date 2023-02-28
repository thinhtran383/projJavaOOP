package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/?user=root";
        String username = "root";
        String password = "Thinh@123";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("success");
            System.out.println(connection.getCatalog());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
