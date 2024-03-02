package bank.management.system;

import bank.management.system.EmployeeInterface.EmployeeInterface.LoginEmp;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class DatabaseMethodes {
    private Conn conn; // Database connection

    DatabaseMethodes(String[] where) {
        conn = new Conn(); // Establish database connection
        // Your constructor code here
    }

    public DatabaseMethodes() {
        conn = new Conn();
    }

    // Method to get the total money sent for a specific form number
    public double getMoneySent(String formNumber) {
        double totalMoneySent = 0;
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        String sql = "SELECT SUM(moneySent) AS totalMoneySent FROM MoneySent WHERE formno = ? and Month(dateInserted) = ? and Year(dateInserted) = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, formNumber);
            preparedStatement.setInt(2, currentMonth);
            preparedStatement.setInt(3, currentYear);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalMoneySent = resultSet.getDouble("totalMoneySent");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalMoneySent;
    }

    // Method to get the total money received for a specific form number
    public double getMoneyReceived(String formNumber) {
        double totalMoneyReceived = 0;

        // SQL query to get the total money received
        String sql = "SELECT SUM(moneyReceived) AS totalMoneyReceived FROM MoneyReceived WHERE formno = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, formNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalMoneyReceived = resultSet.getDouble("totalMoneyReceived");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalMoneyReceived;
    }
    public double getPercentageOfMoneySent(String formNumber) {
        double percentageMoneySent = 0;

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();

        String sqlTotalMoneySent = "SELECT SUM(moneySent) AS totalMoneySent FROM MoneySent WHERE MONTH(dateInserted) = ? AND YEAR(dateInserted) = ?";

        String sqlMoneySent = "SELECT SUM(moneySent) AS moneySent FROM MoneySent WHERE formno = ? AND MONTH(dateInserted) = ? AND YEAR(dateInserted) = ?";

        try (PreparedStatement preparedStatementTotalMoneySent = conn.prepareStatement(sqlTotalMoneySent);
             PreparedStatement preparedStatementMoneySent = conn.prepareStatement(sqlMoneySent)) {

            preparedStatementTotalMoneySent.setInt(1, currentMonth);
            preparedStatementTotalMoneySent.setInt(2, currentYear);
            ResultSet resultSetTotalMoneySent = preparedStatementTotalMoneySent.executeQuery();

            double totalMoneySent = 0;
            if (resultSetTotalMoneySent.next()) {
                totalMoneySent = resultSetTotalMoneySent.getDouble("totalMoneySent");
            }

            preparedStatementMoneySent.setString(1, formNumber);
            preparedStatementMoneySent.setInt(2, currentMonth);
            preparedStatementMoneySent.setInt(3, currentYear);
            ResultSet resultSetMoneySent = preparedStatementMoneySent.executeQuery();

            double moneySent = 0;
            if (resultSetMoneySent.next()) {
                moneySent = resultSetMoneySent.getDouble("moneySent");
            }

            if (totalMoneySent != 0) {
                percentageMoneySent = (moneySent / totalMoneySent) * 100;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return percentageMoneySent;
    }
    public double getCurrentBalance(String formNumber){
        double balance;
        balance = 1.0;
        JFrame Frame = new JFrame();
        try{
               String BalanceCheck = "Select balance from userbalance where userId = ?";
               PreparedStatement prepareBalanceCheck = conn.prepareStatement(BalanceCheck);
               prepareBalanceCheck.setString(1,formNumber);
               ResultSet resultBalance = prepareBalanceCheck.executeQuery();
               if(resultBalance.next()){
                   balance = Double.parseDouble(resultBalance.getString("balance"));

               }else{
                   JOptionPane.showMessageDialog(Frame,"there is an error in your syntax");

               }

        }catch (Exception E){
            E.printStackTrace();;
        }

       return  balance;
    };
    public  int getCountOfUsers(String table){
        int Count = 0;

        JFrame Frame = new JFrame();
        try{
            String BalanceCheck = "Select count(*) as count from "+ table;
            PreparedStatement prepareBalanceCheck = conn.prepareStatement(BalanceCheck);
            ResultSet resultBalance = prepareBalanceCheck.executeQuery();

            if(resultBalance.next()){
                Count =resultBalance.getInt("count");

            }else{
                JOptionPane.showMessageDialog(Frame,"there is an error in your syntax");

            }

        }catch (Exception E){
            E.printStackTrace();;
        }
        return Count;
    }



    public static void main(String[] args) {
          new DatabaseMethodes();


           }
}
