package bank.management.system.EmployeeInterface.EmployeeInterface;

import bank.management.system.Conn;
import bank.management.system.DatabaseMethodes;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashBoardEmp extends JFrame {

    String userReference ;
    DashBoardEmp(String userR){
        super("Bank System - Dashboard");
        this.userReference = userR;
        TypeOf();
        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(360,40);
        setResizable(false);
        setVisible(true);

    }
    public String TypeOf (){
        String usertype = "" ;
        try{
            Conn conn = new Conn();
            String query ="select user_id,sector from usersemp where userReference = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,this.userReference);
            ResultSet resultSet =  preparedStatement.executeQuery();
            this.userReference = resultSet.getString("sector");
        }catch (Exception E){
            E.printStackTrace();
        }
        return usertype;
    }
}
