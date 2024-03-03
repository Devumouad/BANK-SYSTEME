package bank.management.system.EmployeeInterface.EmployeeInterface;

import bank.management.system.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LoginEmp extends JFrame implements ActionListener {
    JLabel labelBank ,labelCard ,label3;
    JTextField PinTextField;
    JPasswordField PasswordField;
    JButton button1,button2,button3;

    public LoginEmp(){
        super("Bank Managment System");
        setResizable(false);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(i1.getImage());

        Image i2 = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350,10,100,100);
        add(image);
        setLayout(null);


        ImageIcon card = new ImageIcon(ClassLoader.getSystemResource("icon/card.png"));
        Image CardInstance = card.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon CardImage = new ImageIcon(CardInstance);
        JLabel secondImage = new JLabel(CardImage);
        secondImage.setBounds(630,350,100,100);
        add(secondImage);
        setLayout(null);

        labelBank = new JLabel("Welcome to ATM");
        labelBank.setForeground(Color.white);
        labelBank.setFont(new Font("AvantGarden",Font.BOLD,30));
        labelBank.setBounds(280,125,450,40);
        add(labelBank);



        labelCard = new JLabel("User Reference: ");
        labelCard.setFont(new Font("Ralway",Font.BOLD,26));
        labelCard.setForeground(Color.white);
        labelCard.setBounds(150,190,375,30);
        add(labelCard);
        label3 = new JLabel("Password: ");
        label3.setFont(new Font("Ralway",Font.BOLD,28));
        label3.setForeground(Color.white);
        label3.setBounds(150,250,375,30);
        add(label3);

        PinTextField = new JTextField(15);
        PinTextField.setBounds(350,190,300,30);
        PinTextField.setFont(new Font("Arial", Font.BOLD,14));
        add(PinTextField);

        PasswordField = new JPasswordField(15);
        PasswordField.setBounds(350,250,300,30);
        PasswordField.setFont(new Font("Arial",Font.BOLD,14));
        add(PasswordField);


        button1 = new JButton("SIGN IN ");
        button1.setFont(new Font("Arial",Font.BOLD,14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(Color.black);
        button1.addActionListener(this);
        button1.setBounds(300,300,100,30);
        add(button1);
        button2 = new JButton("CLEAR");
        button2.setFont(new Font("Arial",Font.BOLD,14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(Color.black);
        button2.addActionListener(this);

        button2.setBounds(430,300,100,30);
        add(button2);
        button3 = new JButton("SIGN UP");
        button3.setFont(new Font("Arial",Font.BOLD,14));
        button3.setForeground(Color.WHITE);
        button3.setBackground(Color.black);
        button3.addActionListener(this);
        button3.setBounds(300,350,230,30);
        add(button3);

        ImageIcon Background = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image BackgroundInstance = Background.getImage().getScaledInstance(850,480,Image.SCALE_DEFAULT);
        ImageIcon BackgroundImage = new ImageIcon(BackgroundInstance);
        JLabel thirdImage = new JLabel(BackgroundImage);
        thirdImage.setBounds(0,0,850,480);
        add(thirdImage);





        setLayout(null);
        setSize(850,480);
        setLocation(450 , 200);
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {

        try{
            JFrame frame = new JFrame();

            if (e.getSource() == button1) {
                String pass = PasswordField.getText();
                String hashedPassword = PasswordHashing.hashPassword(pass);
                String text2 = PinTextField.getText();
                String[] valuesToCheck = {"@", "gmail", ".com"};
                List<String> checkingValues = new ArrayList<>();

                for(String valuesck : valuesToCheck ){
                    if (!text2.contains(valuesck)){
                        checkingValues.add("true");
                    }

                }
                if(checkingValues.size()>0){

                    JOptionPane.showMessageDialog(frame,"email is not in the correct format ");

                }else {
                    Conn conn = new Conn();
                    String selectQuery = "SELECT userReference,passwordEmp, username,sector FROM usersemp WHERE userReference = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
                    preparedStatement.setString(1, text2);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    String sectoree= null;
                    String userReference = null;
                    if (resultSet.next()) {
                        String passwordFromDB = resultSet.getString("passwordEmp");
                         sectoree = resultSet.getString("sector");
                        String nameFromDB = resultSet.getString("username");
                        userReference = resultSet.getString("userReference");

                        if (hashedPassword.equals(passwordFromDB)) {
                            String alertGreen = "Login successful for user:" + text2;
                            JOptionPane.showMessageDialog(frame, alertGreen,
                                    "alert", JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            // Passwords don't match
                            String alertRed = "Your password is incorrect";
                            JOptionPane.showMessageDialog(frame, alertRed,
                                    "alert", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        String alertGreen = "No user found with email: " + text2;
                        JOptionPane.showMessageDialog(frame, alertGreen,
                                "alert", JOptionPane.INFORMATION_MESSAGE);
                    }

                    resultSet.close();
                    preparedStatement.close();


                    new DashBoardEmp(userReference,sectoree);
                    setVisible(false);


                }
            } else if (e.getSource()==button2) {
                PinTextField.setText("");
                PasswordField.setText("");

            } else if (e.getSource()==button3) {
                setVisible(false);
                new SignUpEmp();
            }

        }catch(Exception E){

        }
    }

    public static void main(String[] args) {
        if(databaseChecker.isMySQLServerRunning()){
            new LoginEmp();

        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "MySQL server is down. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


}
