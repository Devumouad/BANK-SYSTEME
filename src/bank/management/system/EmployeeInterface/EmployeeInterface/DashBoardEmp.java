package bank.management.system.EmployeeInterface.EmployeeInterface;

import bank.management.system.Conn;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DashBoardEmp extends JFrame {

    static String userReference;
    static String sectoree;
    JLabel userTypeLabel;
    ArrayList<Object> users =new ArrayList<Object>();
    Object obj=null;

    DashBoardEmp(String userR,String S) {
        super("Bank System - Dashboard");
        this.userReference = userR;
        this.sectoree = S;

        getContentPane().setBackground(new Color(222, 255, 228));

        setLayout(null);
        setSize(850, 600);
        setLocation(360, 40);

        setResizable(false);

        String userType = TypeOf();
        ArrayList<Object> tableofUsers = getUsers(sectoree);
//        for (int i = 0; i < tableofUsers.size(); i++) {
//            Object obj = tableofUsers.get(i);
//
//            if (obj instanceof Object[]) {
//                Object[] array = (Object[]) obj;
//
//                int arrayLength = array.length;
//
//                for (int j = 0; j < arrayLength; j++) {
//                    Object element = array[j];
//                    if(element!=null){
//                        System.out.println(element);
//
//                    }
//                }
//            }
//        }


        userTypeLabel = new JLabel("ALL Users for : " + userType);
        userTypeLabel.setBounds(200, 20, 500, 50);
        userTypeLabel.setFont(new Font("Raleway",Font.BOLD,28));
        add(userTypeLabel);

        String[] columns = {"First Name", "Last Name", "Age"};
        Object[][] data = new Object[users.size()][columns.length];
        for (int i = 0; i < users.size(); i++) {
            data[i] = (Object[]) users.get(i);
        }
        JTable table = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setForeground(new Color(222, 255, 228));
        scrollPane.setBounds(50, 100, 750, 400); // Adjust bounds
        add(scrollPane);

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
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Failed to retrieve user type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return userType;
    }
    private ArrayList<Object> getUsers(String sector){
        try{
            Conn conn = new Conn();
            String query = null;
            PreparedStatement preparedStatement = null;
            query = "Select username, lname,fname from usersemp where  sector = ?  ";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,sector);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String username=  resultSet.getString("username");

                String fname=  resultSet.getString("fname");
                String lname=  resultSet.getString("lname");
                obj = new String[]{username, fname,lname};
                users.add(obj);
            }

        }catch(Exception E){
            E.printStackTrace();

        }
        return users;
    }

    public static void main(String[] args) {
        new DashBoardEmp("230" , "Procession");
    }
}
