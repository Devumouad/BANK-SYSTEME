package bank.management.system.EmployeeInterface.EmployeeInterface;

import bank.management.system.Conn;
import bank.management.system.DatabaseMethodes;
import bank.management.system.SecondeSingUp;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashBoardEmp extends JFrame {

    static String userReference;
    JLabel userTypeLabel;

    DashBoardEmp(String userR) {
        super("Bank System - Dashboard");
        this.userReference = userR;

        getContentPane().setBackground(new Color(222, 255, 228));

        setLayout(null);
        setSize(850, 600);
        setLocation(360, 40);

        setResizable(false);

        String userType = TypeOf();

        userTypeLabel = new JLabel("User Type: " + userType);
        userTypeLabel.setBounds(50, 50, 200, 30);
        add(userTypeLabel);

        setVisible(true);

    }




    private String TypeOf() {
        String userType = "";
        try {
            Conn conn = new Conn();
            String query = "SELECT sector FROM usersemp WHERE userReference = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setString(1, userReference);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                userType = resultSet.getString("sector");
            } else {
                System.out.println("User not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Failed to retrieve user type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return userType;
    }

    public static void main(String[] args) {
        new DashBoardEmp(userReference);
    }
}
