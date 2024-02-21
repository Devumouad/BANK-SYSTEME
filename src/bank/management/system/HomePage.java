package bank.management.system;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomePage extends JFrame implements ActionListener {
    JLabel labelForWlcm;
    JButton button1, button2, button3;
    static String formid = "";
    String name;

    HomePage(String id_number) {
        super("Bank Management System");
        this.formid = id_number;
        setResizable(true);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        setIconImage(i1.getImage());
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(370, 10, 100, 100);
        add(image);
        setLayout(null);

        labelForWlcm = new JLabel("Welcome to ATM");
        labelForWlcm.setForeground(Color.white);
        labelForWlcm.setFont(new Font("AvantGarden", Font.BOLD, 30));
        labelForWlcm.setBounds(300, 125, 450, 40);
        add(labelForWlcm);

        button1 = new JButton("Account History");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(Color.WHITE);
        button1.setBackground(new Color(64, 162, 227));
        button1.addActionListener(this);
        button1.setFocusable(false);
        button1.setBounds(130, 200, 150, 60);
        add(button1);

        button2 = new JButton("Savings");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(Color.WHITE);
        button2.setBackground(new Color(64, 162, 227));
        button2.addActionListener(this);
        button2.setBounds(350, 200, 150, 60);
        button2.setFocusable(false);
        add(button2);

        button3 = new JButton("Balance");
        button3.setFont(new Font("Arial", Font.BOLD, 14));
        button3.setForeground(Color.white);
        button3.setBackground(new Color(64, 162, 227));
        button3.setFocusable(false);
        button3.addActionListener(this);
        button3.setBounds(570, 200, 150, 60);
        add(button3);

        requestFocusInWindow();

        // Create and add pie chart
        JFreeChart pieChart = createPieChart();
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setBounds(60, 280, 250, 160);
        add(chartPanel);

        ImageIcon Background = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        Image BackgroundInstance = Background.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon BackgroundImage = new ImageIcon(BackgroundInstance);
        JLabel thirdImage = new JLabel(BackgroundImage);
        thirdImage.setBounds(0, 0, 900, 700);
        add(thirdImage);

        setSize(900, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            Conn conn = new Conn();
            String selectQuery = "SELECT formno, password, name FROM users WHERE formno = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(selectQuery);
            preparedStatement.setString(1, id_number);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                labelForWlcm.setText("Welcome Mr " + this.name);
            } else {
                System.out.println(this.name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Handle button actions
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Method to create a pie chart
    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Savings", 5000);
        dataset.setValue("Investments", 3000);
        dataset.setValue("Checking", 2000);

        JFreeChart chart=  ChartFactory.createPieChart(
                "Account Balances",
                dataset,
                false,
                false,
                false
        );
        chart.setBackgroundPaint(new Color(0, 137, 224));

        PiePlot plot = (PiePlot) chart.getPlot();
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("icon/backbg.png"));
        plot.setBackgroundImage(backgroundImage.getImage());

        return  chart;

    }

    public static void main(String[] args) {
        new HomePage(formid);
    }
}
