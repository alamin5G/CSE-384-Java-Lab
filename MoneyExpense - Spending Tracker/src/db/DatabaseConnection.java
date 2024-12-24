/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author alami
 */
public class DatabaseConnection {
    
    public static Connection connection;
    public static Statement statement;
    
    static{
        try {
            // host location with dbname, username, password
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spendingTracker", "root", "252646");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
