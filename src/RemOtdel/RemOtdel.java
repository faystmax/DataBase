/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemOtdel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

/**
 *
 * @author tigler
 */
public class RemOtdel {
    public static Statement st;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // TODO code application logic here
        Locale.setDefault(Locale.ENGLISH);
        Class.forName("oracle.jdbc.OracleDriver");
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SERVERADM", "parol_prostoiy");
        st = connection.createStatement();
        //connection.close();
        Login loginFrame = new Login();
        loginFrame.setVisible(true);
    }

}
