package bank.management.system;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Random;

import java.awt.event.ActionListener;
import java.util.Arrays;


public class SecondeSingUp extends JFrame implements ActionListener {
    JComboBox<String> RelgComboBox,categoryComboBox,incomComboBox,Occupation;
    static String formno, PathFace,PathBack;
    JButton Button,fileButton,fileButton2;
    Random ran = new Random();
    JTextField panText;
    JLabel fileLabel;
    long first4 = (ran.nextLong()% 9000L) +1000L;
    String InterNO = "" + Math.abs(first4);

    SecondeSingUp(String first){
        super("APPLICATION FORM");
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(icon.getImage());
        setResizable(false);
        ImageIcon bank = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image bankInstance = bank.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon bankImage = new ImageIcon(bankInstance);
        JLabel image = new JLabel(bankImage);
        image.setBounds(150,5,100,100);
        add(image);

        this.formno=first;
        JLabel l1 =new JLabel("Page 2 : -");
        l1.setFont(new Font("Raleway",Font.BOLD,22));
        l1.setBounds(300,30,600,40);
        add(l1);

        JLabel l2 = new JLabel("Additiional Details");
        l2.setFont(new Font("Raleway",Font.BOLD,22));
        l2.setBounds(300,60,600,40);
        add(l2);

        JLabel l3 = new JLabel("Religion");
        l3.setFont(new Font("Raleway",Font.BOLD,18));
        l3.setBounds(100,120,100,30);
        add(l3);

        String religion[]= {
                "Select Religion ",

                "Hindu",
                "Muslim",
                "Christien",
                "Other"
        } ;



        RelgComboBox = new JComboBox<>(religion);
        RelgComboBox.setBackground(new Color(222,255,228));
        RelgComboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        RelgComboBox.setBounds(350, 120, 320, 30);
        add(RelgComboBox);


        JLabel l4 = new JLabel("Category");
        l4.setFont(new Font("Raleway",Font.BOLD,18));
        l4.setBounds(100,170,100,30);
        add(l4);
        String Category[] = {"Select Category ","General","OBC","SC","ST","Other"};
        categoryComboBox = new JComboBox<>(Category);
        categoryComboBox.setBackground(new Color(222,255,228));
        categoryComboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        categoryComboBox.setBounds(350, 170, 320, 30);
        add(categoryComboBox);

        JLabel l5 = new JLabel("Income : ");
        l5.setFont(new Font("Raleway",Font.BOLD,18));
        l5.setBounds(100,220,100,30);
        add(l5);

        String income[]= {
                "Select Income ",
                "Prefer not to say",
                "<1.50.00000",
                "<2.50.00000",
                "<5.00.00000",
                "Upto <10.00.000",
                "Above 10.00.000",
        } ;



        incomComboBox = new JComboBox<>(income);
        incomComboBox.setBackground(new Color(222,255,228));
        incomComboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        incomComboBox.setBounds(350, 220, 320, 30);
        add(incomComboBox);


        JLabel l6 = new JLabel("Occupation : ");
        l6.setFont(new Font("Raleway",Font.BOLD,18));
        l6.setBounds(100,270,130,30);
        add(l6);

        String Occupations[]= {
                "Select Occupations ",
                "Salaried",
                "Self Employed",
                "Student",
                "Retired",
                "Other",
        } ;



        Occupation = new JComboBox<>(Occupations);
        Occupation.setBackground(new Color(222,255,228));
        Occupation.setFont(new Font("Raleway", Font.BOLD, 14));
        Occupation.setBounds(350, 270, 320, 30);
        add(Occupation);


        JLabel l7 = new JLabel("Phone Number");
        l7.setFont(new Font("Raleway",Font.BOLD,18));
        l7.setBounds(100,320,150,30);
        add(l7);

        panText = new JTextField();
        panText.setFont(new Font("Raleway", Font.BOLD, 14));
        panText.setText("+212");
        panText.setBounds(350, 320, 320, 30);
        FocusChecker paneFocus = new FocusChecker(panText, "+212:Enter Phone Number");

        add(panText);
        fileLabel = new JLabel("ID Picture:");
        fileLabel.setFont(new Font("Raleway", Font.BOLD, 18));
        fileLabel.setBounds(100, 370, 150, 30);
        add(fileLabel);
        fileButton = new JButton("FACE");
        fileButton.setFont(new Font("Raleway", Font.BOLD, 14));
        fileButton.setBounds(350, 370, 100, 30);
        fileButton.addActionListener(this);
        add(fileButton);
        fileButton2 = new JButton("BACK");
        fileButton2.setFont(new Font("Raleway", Font.BOLD, 14));
        fileButton2.setBounds(500, 370, 100, 30);
        fileButton2.addActionListener(this);
        add(fileButton2);
        Button = new JButton("Next");
        Button.setFont(new Font("Raleway",Font.BOLD,17));
        Button.setBackground(Color.black);
        Button.setForeground(Color.white);
        Button.setBounds(450,420,80,30);
        Button.addActionListener(this);
        add(Button);





        setLayout(null);
        setSize(850,750);
        setLocation(450,80);
        getContentPane().setBackground(new Color(222,255,228));
        setVisible(true);
    }

    public static void main(String[] args) {
        if(databaseChecker.isMySQLServerRunning()){
            new SecondeSingUp(formno);

        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "MySQL server is down. Please try again later.",
                    "Server Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String add_Id = InterNO;

        String formb= formno;
        String phoneNumber = panText.getText();
        if(e.getSource()==fileButton){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a File");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg");
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                fileButton.setText(selectedFile.getAbsolutePath());
                PathFace=selectedFile.getAbsolutePath();

                System.out.println("Selected file: " +PathFace);
            }
        }
        else if (e.getSource()==fileButton2){
            JFileChooser fileChooser2 = new JFileChooser();
            fileChooser2.setDialogTitle("Choose a File");
            fileChooser2.setFileSelectionMode(JFileChooser.FILES_ONLY);
            FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Image Files", "png", "jpg", "jpeg");
            fileChooser2.setFileFilter(filter2);
            int returnValue2 = fileChooser2.showOpenDialog(null);
            if (returnValue2 == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser2.getSelectedFile();
                fileButton2.setText(selectedFile.getAbsolutePath());
                PathBack=selectedFile.getAbsolutePath();
                System.out.println("Selected file: " + PathBack);
            }}
        else{
            String selectedOccupations = (String) Occupation.getSelectedItem();
            String selectedRelegion = (String) RelgComboBox.getSelectedItem();
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            String selectedIncom = (String) incomComboBox.getSelectedItem();



            try {
                if(selectedRelegion.equals("Select Relegion") ||
                        selectedCategory.equals("Select Category") ||
                        selectedIncom.equals("Select Income") ||
                        selectedOccupations.equals("Select Occupation")||
                        phoneNumber.equals("+212:Enter Phone Number")


                ){
                    JOptionPane.showMessageDialog(null,"Fill all the fields");
                }else{


                    Conn conn = new Conn();
                    String insertQuery = "INSERT INTO additionalInfo values ('"+add_Id+"','"+formb+"', '"+selectedCategory+"', '"+selectedOccupations+"'" +
                            ", '"+phoneNumber+"', '"+selectedRelegion+"', '"+selectedIncom+"','"+PathFace+"','"+PathBack+"') ";
                    conn.statement.executeUpdate(insertQuery);
                    new HomePage(formno);
                    setVisible(false);

                }

            }catch (Exception E){
                E.printStackTrace();

            }

        }

        





    }

}
