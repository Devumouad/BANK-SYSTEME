package bank.management.system;

import bank.management.system.EmployeeInterface.EmployeeInterface.LoginEmp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstInterface extends JFrame implements ActionListener {
    JButton button1, button2;

    FirstInterface() {
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(i1.getImage());
        setTitle("Bank Management System");
        setSize(350, 200); // Set the size of the frame

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Background image
        ImageIcon backgroundImageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image backgroundImage = backgroundImageIcon.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon scaledBackgroundImageIcon = new ImageIcon(backgroundImage);
        JLabel backgroundLabel = new JLabel(scaledBackgroundImageIcon);
        backgroundLabel.setBounds(0, 0, 900, 700);
        add(backgroundLabel);

        JLabel question = new JLabel("Are you a ??");
        question.setFont(new Font("AvantGarden", Font.BOLD, 30));

        question.setBounds(70, 50, 200, 40);
        backgroundLabel.add(question);

        button1 = new JButton("Client");
        button1.setBounds(50, 100, 100, 30);
        button1.addActionListener(this);
        backgroundLabel.add(button1);

        button2 = new JButton("Employee");
        button2.setBounds(170, 100, 100, 30);
        button2.addActionListener(this);
        backgroundLabel.add(button2);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new FirstInterface();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            new Login();
            setVisible(false);
        } else if (e.getSource() == button2) {
            new LoginEmp();
            setVisible(false);

        }
    }
}
