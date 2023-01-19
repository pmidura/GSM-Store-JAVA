package projectapp;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Midura Patryk
 */

public class ConnectDB {
    
    Connection conn;
    Statement stmt;
    
    public void ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user = "root";
            String password = "root";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sklepgsm?autoReconnect=true&useSSL=false", user, password);
            stmt = conn.createStatement();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}

