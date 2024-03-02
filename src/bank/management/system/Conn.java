package bank.management.system;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Conn {
    Connection connection;
    String userName = "root";
    String password = "";
    public Statement statement;


    public Conn() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankSysteme", userName, password);
            statement = connection.createStatement();
        } catch (Exception E) {
            E.printStackTrace();
        }
    }
    public static void main(String[] args) {
        if(databaseChecker.isMySQLServerRunning()){
            new Conn();

        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "MySQL server is down. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
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
