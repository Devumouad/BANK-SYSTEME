package bank.management.system;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener{

    JTextField textName  ,textfirst, textEmail ,textAdd;
    JRadioButton male,female;
    JComboBox<String> countryComboBox;
    JDateChooser dateChoser;
    JButton Next;
    JPasswordField pass1,pass2;
    Random ran = new Random();
    long first4 = (ran.nextLong()% 9000L) +1000L;
    String first = "" + Math.abs(first4);
    SignUp(){
        super("APPLICATION FORM");
        

        ImageIcon bank = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(bank.getImage());
        Image bankInstance = bank.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon bankImage = new ImageIcon(bankInstance);
        JLabel image = new JLabel(bankImage);
        image.setBounds(25,10,100,100);
        add(image);


        JLabel labelForBank = new JLabel("APPLICATION FORM NO."+first);
        labelForBank.setBounds(160,20,600,40);
        labelForBank.setFont(new Font("Raleway",Font.BOLD,38));
        add(labelForBank);

        JLabel label2 = new JLabel("Page 1");
        label2.setFont(new Font("Raleway",Font.BOLD,22));
        label2.setBounds(330,70,600,30);
        add(label2);


        JLabel label3 = new JLabel("Personal Details");
        label3.setFont(new Font("Raleway",Font.BOLD,22));
        label3.setBounds(290,90,600,30);
        add(label3);

        JLabel labelName = new JLabel("Name ");
        labelName.setFont(new Font("Raleway",Font.BOLD,20));
        labelName.setBounds(100,170,100,30);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway",Font.BOLD,14));
        textName.setBounds(300,170,400,30);
        FocusChecker focusName = new FocusChecker(textName, "Enter Name");

        add(textName);



        JLabel labelFName = new JLabel("Father's Name ");
        labelFName.setFont(new Font("Raleway",Font.BOLD,20));
        labelFName.setBounds(100,220,200,30);
        add(labelFName);

        textfirst = new JTextField();
        textfirst.setFont(new Font("Raleway",Font.BOLD,14));
        textfirst.setBounds(300,220,400,30);
        FocusChecker fatherFocus = new FocusChecker(textfirst, "Enter Father's Name");

        add(textfirst);

        JLabel labelG = new JLabel("Gender");
        labelG.setFont(new Font("Raleway",Font.BOLD,20));
        labelG.setBounds(100,250,200,30);
        add(labelG);

        male = new JRadioButton("Male");
        male.setFont(new Font("Raleway",Font.BOLD,17));
        male.setBackground(new Color(222,255,228));
        male.setBounds(300,250,90,30);
        add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Raleway",Font.BOLD,17));
        female.setBackground(new Color(222,255,228));
        female.setBounds(450,250,90,30);
        add(female);
        JLabel labelLName = new JLabel("Date of Birth ");
        labelLName.setFont(new Font("Raleway",Font.BOLD,20));
        labelLName.setBounds(100,350,200,30);
        add(labelLName);

        dateChoser = new JDateChooser();
        dateChoser.setForeground(new Color(105,105,105));
        dateChoser.setBounds(300,350,400,30);
        add(dateChoser);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(male);
        buttonGroup.add(female);

        JLabel labelEmail = new JLabel("Email address :");
        labelEmail.setFont(new Font("Raleway",Font.BOLD,20));
        labelEmail.setBounds(100,300,200,30);
        add(labelEmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway",Font.BOLD,14));
        textEmail.setBounds(300,300,400,30);
        add(textEmail);
        FocusChecker EmailFocus = new FocusChecker(textEmail, "Enter Email");







        JLabel labelAddress = new JLabel("Address :");
        labelAddress.setFont(new Font("Raleway",Font.BOLD,20));
        labelAddress.setBounds(100,400,200,30);
        add(labelAddress);

        textAdd = new JTextField();
        textAdd.setFont(new Font("Raleway",Font.BOLD,14));
        textAdd.setBounds(300,400,400,30);
        add(textAdd);
        FocusChecker adresseFocus = new FocusChecker(textAdd, "Enter Address");


        JLabel labelCounty = new JLabel("Country :");
        labelCounty.setFont(new Font("Raleway",Font.BOLD,20));
        labelCounty.setBounds(100,450,200,30);
        add(labelCounty);


        JLabel labelPass1 = new JLabel("Password :");
        labelPass1.setFont(new Font("Raleway",Font.BOLD,20));
        labelPass1.setBounds(100,500,200,30);
        add(labelPass1);
        pass1 = new JPasswordField(20);
        pass1.setFont(new Font("Raleway",Font.BOLD,14));
        pass1.setBounds(300,500,400,30);
        add(pass1);

        JLabel labelPass2 = new JLabel("Confirm Password :");
        labelPass2.setFont(new Font("Raleway",Font.BOLD,20));
        labelPass2.setBounds(100,550,200,30);
        add(labelPass2);
        pass2 = new JPasswordField(20);
        pass2.setFont(new Font("Raleway",Font.BOLD,14));
        pass2.setBounds(300,550,400,30);
        add(pass2);

        String[] countries = {"Select Country", "USA", "Canada", "UK", "Australia", "India", "Germany", "France", "Japan",
                "Algeria", "Angola", "Benin", "Botswana", "Burkina Faso", "Burundi", "Cabo Verde", "Cameroon",
                "Central African Republic", "Chad", "Comoros", "Democratic Republic of the Congo", "Djibouti",
                "Egypt", "Equatorial Guinea", "Eritrea", "Eswatini", "Ethiopia", "Gabon", "Gambia", "Ghana",
                "Guinea", "Guinea-Bissau", "Ivory Coast", "Kenya", "Lesotho", "Liberia", "Libya", "Madagascar",
                "Malawi", "Mali", "Mauritania", "Mauritius", "Morocco", "Mozambique", "Namibia", "Niger", "Nigeria",
                "Republic of the Congo", "Rwanda", "Sao Tome and Principe", "Senegal", "Seychelles", "Sierra Leone",
                "Somalia", "South Africa", "South Sudan", "Sudan", "Tanzania", "Togo", "Tunisia", "Uganda",
                "Zambia", "Zimbabwe"};

        countryComboBox = new JComboBox<>(countries);
        countryComboBox.setFont(new Font("Raleway", Font.BOLD, 14));

        countryComboBox.setBounds(300, 450, 200, 30);
        add(countryComboBox);

        Next = new JButton("Next");
        Next.setFont(new Font("Raleway",Font.BOLD,17));
        Next.setBackground(Color.black);
        Next.setForeground(Color.white);
        Next.setBounds(620,590,80,30);
        Next.addActionListener(this);
        add(Next);





        getContentPane().setBackground(new Color(222,255,228));
        setLayout(null);
        setSize(850,800);
        setLocation(360,40);
        setResizable(false);
        setVisible(true);



    }
    @Override

    public void actionPerformed(ActionEvent e) {
        String formno= first;
        String name = textName.getText();
        String fname = textfirst.getText();
        String date = ((JTextField) dateChoser.getDateEditor().getUiComponent()).getText();
        String gender=null;
        if(male.isSelected()){
            gender = "Male";
        }
        else if(female.isSelected()){
            gender = "Female";

        }
        String Email = textEmail.getText();

        String Address = textAdd.getText();


        String selectedCountry = (String) countryComboBox.getSelectedItem();
        String HashedPassword = null;


        try {
            if(textName.getText().equals("") ||
                    textfirst.getText().equals("") ||
                    textEmail.getText().equals("") ||
                    textAdd.getText().equals("")||
                    pass1.getPassword().equals("")||
                    pass2.getPassword().equals("")||
                    selectedCountry.equals("Select Country")

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
                    String insertQuery = "INSERT INTO users values ('"+formno+"', '"+name+"', '"+fname+"'" +
                            ", '"+date+"', '"+gender+"', '"+Email+"', '"+Address+"', '"+HashedPassword+"', '"+selectedCountry+"') ";
                    conn.statement.executeUpdate(insertQuery);
                    new SecondeSingUp(first);
                    setVisible(false);

                }






            }

        }catch (Exception E){
            E.printStackTrace();

        }


    }
    public static void main(String[] args) {
        if(databaseChecker.isMySQLServerRunning()){
            new SignUp();

        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "MySQL server is down. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
