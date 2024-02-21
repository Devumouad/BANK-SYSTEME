package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseChecker {
    public static boolean isMySQLServerRunning() {
        String url = "jdbc:mysql://localhost:3306/banksysteme";
        String user = "root";
        String password = "";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        if (isMySQLServerRunning()) {
            System.out.println("MySQL server is running.");
        } else {
            System.out.println("MySQL server is not running.");
        }
    }
}
