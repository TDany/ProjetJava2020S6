/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author danytadrous
 */
public class TestExemple extends JFrame {

    public TestExemple() {

        this.setTitle("HYPERPLANNING");
        this.setSize(800, 800);
        this.setVisible(true);
        this.setLayout(new GridLayout(5, 2));

        JLabel custtex = new JLabel();
        custtex.setText("NUMERO");
        this.add(custtex);

        JLabel itetex = new JLabel();
        itetex.setText("ID_Groupe");
        this.add(itetex);

        Connection conn = null;
        try {
            // database parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
               
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Etudiant");

            ResultSet rs = stmt.executeQuery();

            
            while (rs.next()) {

                JLabel cust = new JLabel();
                cust.setText(rs.getString("Numero"));
                this.add(cust);

                JLabel ite = new JLabel();
                ite.setText(rs.getString("ID_Groupe"));
                this.add(ite);
            }

            conn.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
