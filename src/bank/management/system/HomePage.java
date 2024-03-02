package bank.management.system;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.jfree.chart.ChartFactory.createBarChart;

public class HomePage extends JFrame implements ActionListener {
    JLabel labelForWlcm,labelForBalance;
    JButton button1, button2, button3;
    static String formid = "";
    String name;
    double moneyavg,Balance,accuired,saved;
    JFrame frame;

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

        labelForBalance = new JLabel("Account Statics");
        labelForBalance.setForeground(Color.white);
        labelForBalance.setFont(new Font("AvantGarden", Font.BOLD, 25));
        labelForBalance.setBounds(320, 300, 450, 40);
        add(labelForBalance);


        JFreeChart pieChart = createPieChart();
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setBounds(80, 380, 300, 200);
        add(chartPanel);


        JFreeChart barChart = createBarChart();
        ChartPanel barChartPanel = new ChartPanel(barChart);
        barChartPanel.setBounds(420, 380, 300, 200);
        add(barChartPanel);

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
            preparedStatement.setString(1, "5056");

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                labelForWlcm.setText("Welcome Mr " + this.name);



            } else {
                System.out.println("Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            frame = new JFrame();
           if(e.getSource()==button3){
               JOptionPane.showMessageDialog(frame,"Your balance is : "+this.Balance + "DH");
           }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JFreeChart createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            DatabaseMethodes date = new DatabaseMethodes();
            this.moneyavg = date.getMoneySent(formid);
            this.accuired = date.getMoneyReceived(formid);
            this.Balance = date.getCurrentBalance(formid);
            this.saved = this.Balance -this.moneyavg;



        }catch(Exception E){
            E.printStackTrace();
        }


        dataset.setValue("Spent", this.moneyavg);
        dataset.setValue("Acquired", this.accuired);
        dataset.setValue("Saved", this.saved);



        JFreeChart chart=  ChartFactory.createPieChart(
                "",
                dataset,
                false,
                false,
                false
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        chart.setBackgroundPaint(new Color(44,117,152,255));
        plot.setOutlinePaint(new Color(44,117,152,255));

        plot.setBackgroundPaint(new Color(44,117,152,255));

        return  chart;

    }
    private JFreeChart createBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5000, "Account Balances", "Spent");
        dataset.addValue(3000, "Account Balances", "Acquired");
        dataset.addValue(2000, "Account Balances", "Saved");

        JFreeChart chart = ChartFactory.createBarChart(
                "",
                "Account Type",
                "Balance",
                dataset
        );
        chart.setBackgroundPaint(new Color(44,117,152,255));

        return chart;
    }

    public static void main(String[] args) {
        new HomePage(formid);
    }
}
