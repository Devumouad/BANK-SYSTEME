package bank.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Conn {
    Connection connection;
    String userName = "root";
    String password = "";
    Statement statement;


    public Conn() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSysteme", userName, password);
            statement = connection.createStatement();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Conn();
    }


    public PreparedStatement prepareStatement(String insertQuery) {
        try {
            return connection.prepareStatement(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
