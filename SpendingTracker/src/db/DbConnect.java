package db;
import java.sql.*;
import javax.swing.JOptionPane;
public class DbConnect {
    public static Connection c;
    public static Statement st;
    static{
        try{
            c=DriverManager.getConnection(
      "jdbc:mysql://localhost:3306/spendingTracker"+"?useSSL=false",
                                  
                 "root", "252646");
            st=c.createStatement();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
