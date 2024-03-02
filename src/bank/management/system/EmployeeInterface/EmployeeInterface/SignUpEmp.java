package bank.management.system.EmployeeInterface.EmployeeInterface;

import bank.management.system.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpEmp extends JFrame implements ActionListener{

    JTextField textName  ,textuserInterface, textEmail ,textAdd;
    JRadioButton male,female;
    JComboBox<String> countryComboBox;
    JDateChooser dateChoser;
    JButton Next;
    JPasswordField pass1,pass2;
    
    String userInterface  ;
    int countUsers;
    SignUpEmp(){
        super("APPLICATION FORM");
        getCountOfUsersAndUpdateCount();




        ImageIcon bank = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(bank.getImage());
        Image bankInstance = bank.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon bankImage = new ImageIcon(bankInstance);
        JLabel image = new JLabel(bankImage);
        image.setBounds(330,10,100,100);
        add(image);
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();
        int Month = today.getMonthValue();
        userInterface = day+""+Month+""+this.countUsers;

        JLabel labelForBank = new JLabel("Welcome new Employee");
        labelForBank.setBounds(200,100,600,50);
        labelForBank.setFont(new Font("Raleway",Font.BOLD,28));
        add(labelForBank);





        JLabel labelName = new JLabel("Name ");
        labelName.setFont(new Font("Raleway",Font.BOLD,20));
        labelName.setBounds(100,170,100,30);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD,14));
        textName.setBounds(300,170,400,30);
        FocusChecker focusName = new FocusChecker(textName, "Enter Name");

        add(textName);



        JLabel labelFName = new JLabel("Last Name ");
        labelFName.setFont(new Font("Raleway",Font.BOLD,20));
        labelFName.setBounds(100,220,200,30);
        add(labelFName);

        textuserInterface = new JTextField();
        textuserInterface.setFont(new Font("Raleway",Font.BOLD,14));
        textuserInterface.setBounds(300,220,400,30);
        FocusChecker fatherFocus = new FocusChecker(textuserInterface, "Enter Father's Name");

        add(textuserInterface);

        JLabel labelG = new JLabel("Gender");
        labelG.setFont(new Font("Raleway",Font.BOLD,20));
        labelG.setBounds(100,270,200,30);
        add(labelG);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway",Font.BOLD,17));
        male.setBackground(new Color(222,255,228));
        male.setBounds(300,270,90,30);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway",Font.BOLD,17));
        female.setBackground(new Color(222,255,228));
        female.setBounds(450,270,90,30);
        add(female);








        JLabel labelCounty = new JLabel("Sector :");
        labelCounty.setFont(new Font("Raleway",Font.BOLD,20));
        labelCounty.setBounds(100,320,200,30);
        add(labelCounty);


        JLabel labelPass1 = new JLabel("Password :");
        labelPass1.setFont(new Font("Raleway",Font.BOLD,20));
        labelPass1.setBounds(100,380,200,30);
        add(labelPass1);
        pass1 = new JPasswordField(20);
        pass1.setFont(new Font("Raleway",Font.BOLD,14));
        pass1.setBounds(300,380,400,30);
        add(pass1);

        JLabel labelPass2 = new JLabel("Confirm Password :");
        labelPass2.setFont(new Font("Raleway",Font.BOLD,20));
        labelPass2.setBounds(100,430,200,30);
        add(labelPass2);
        pass2 = new JPasswordField(20);
        pass2.setFont(new Font("Raleway",Font.BOLD,14));
        pass2.setBounds(300,430,400,30);
        add(pass2);

        String[] countries = {"Select Sector","Creation et Modification","Procession","Service Urgence","Service Clientèle"};

        countryComboBox = new JComboBox<>(countries);
        countryComboBox.setFont(new Font("Raleway", Font.BOLD, 14));

        countryComboBox.setBounds(300, 320, 200, 30);
        add(countryComboBox);

        Next = new JButton("Next");
        Next.setFont(new Font("Raleway",Font.BOLD,17));
        Next.setBackground(Color.black);
        Next.setForeground(Color.white);
        Next.setBounds(620,490,80,30);
        Next.addActionListener(this);
        add(Next);





        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(360,40);
        setResizable(false);
        setVisible(true);



    }


    private void getCountOfUsersAndUpdateCount() {
        try {
            DatabaseMethodes data = new DatabaseMethodes();
            this.countUsers = data.getCountOfUsers("usersEmp");
            System.out.println("Number of users in userEmp table: " + this.countUsers);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String userFname = textName.getText();
        String userLname = textuserInterface.getText();
        String username = userFname.charAt(0)+userLname;
        String gender=null;
        if(male.isSelected()){
            gender = "Male";
        }
        else if(female.isSelected()){
            gender = "Female";

        }




        String selectedCountry = (String) countryComboBox.getSelectedItem();
        String HashedPassword = null;


        try {
            if(textName.getText().equals("") ||
                    textuserInterface.getText().equals("") ||
                    pass1.getPassword().equals("")||
                    pass2.getPassword().equals("")||
                    selectedCountry.equals("Select Sector")

            ){
                JOptionPane.showMessageDialog(null,"Fill all the fields");
            }else{
                char[] passwordChars = pass1.getPassword();
                char[] passwordChars2 = pass2.getPassword();
                boolean passwordMatch = Arrays.equals(passwordChars, passwordChars2);
                if (!passwordMatch) {
                    JOptionPane.showMessageDialog(null, "Password does not match");
                } else {
                    String password = new String(passwordChars);
                    HashedPassword = PasswordHashing.hashPassword(password);
                    Conn conn = new Conn();
                    String insertQuery = "INSERT INTO usersEmp(sector, userReference, passwordEmp, username, gender) VALUES " +
                            "('"+selectedCountry+"', '"+userInterface+"', '"+HashedPassword+"', '"+username+"', '"+gender+"')";

                    conn.statement.executeUpdate(insertQuery);
                    setVisible(false);
                    JOptionPane.showMessageDialog(null,"Your username is :"+username);

                }






            }

        }catch (Exception E){
            E.printStackTrace();

        }


    }
    public static void main(String[] args) {
        if(databaseChecker.isMySQLServerRunning()){
            new SignUpEmp();

        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "MySQL server is down. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
