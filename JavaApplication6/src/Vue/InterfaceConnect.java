/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 *
 * @author danytadrous
 */

public class InterfaceConnect extends JFrame {

    private JTextField userNameText;
    private JTextField passwordText;

    public InterfaceConnect() {
        this.setTitle("INTERFACE CONNECT");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(12,3));
                                                                                                                                                                   
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        JLabel signIn = new JLabel();
        signIn.setText("SIGN IN");
        this.add(signIn);

        for (int i = 0; i < 5; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        JLabel userName = new JLabel();
        userName.setText("EMAIL");
        this.add(userName);

        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        userNameText = new JTextField();
        this.add(userNameText);

        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        JLabel password = new JLabel();
        password.setText("PASSWORD");
        this.add(password);

        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        passwordText = new JTextField();
        this.add(passwordText);

        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

        JButton ButtonConnect = new JButton("CONNECT");
        this.add(ButtonConnect);
        ButtonConnect.addActionListener(new ButtonConnectListener());

        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }

    }

    private class ButtonConnectListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String InputUsernameText, InputPasswordText;
            InputUsernameText = userNameText.getText();
            InputPasswordText = passwordText.getText();

            Connection conn = null;
            try {
                // database parameters
                String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
                String user = "root";
                String password = "root";

                // create a connection to the database
                conn = DriverManager.getConnection(url, user, password); 
                
                //CONNECT ENSEIGNANT
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Utilisateur WHERE(Email = ? AND Passwd = ? AND Droit = 3)");
                stmt.setString(1, InputUsernameText);
                stmt.setString(2, InputPasswordText);
                ResultSet rs = stmt.executeQuery();
                
                //CONNECT ETUDIANT
                PreparedStatement stmt1 = conn.prepareStatement("SELECT * FROM Utilisateur WHERE(Email = ? AND Passwd = ? AND Droit = 4)");
                stmt1.setString(1, InputUsernameText);
                stmt1.setString(2, InputPasswordText);
                ResultSet rs1 = stmt1.executeQuery();
                
                //CONNECT ADMIN
                PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM Utilisateur WHERE(Email = ? AND Passwd = ? AND Droit = 1)");
                stmt2.setString(1, InputUsernameText);
                stmt2.setString(2, InputPasswordText);
                ResultSet rs2 = stmt2.executeQuery();
                
                //CONNECT REFERENT
                PreparedStatement stmt3 = conn.prepareStatement("SELECT * FROM Utilisateur WHERE(Email = ? AND Passwd = ? AND Droit = 2)");
                stmt3.setString(1, InputUsernameText);
                stmt3.setString(2, InputPasswordText);
                ResultSet rs3 = stmt3.executeQuery();
                
                
                
                
                
                if (rs.next()) {
                    String name = rs.getString("Prenom");
                    new InterfaceEnseignant(name); 
                } else if (rs1.next()){
                    String name = rs1.getString("Prenom");
                    int semaineBDD = 1;
                    new InterfaceEtudiant(name, 1);
                } else if (rs2.next()){
                    String name = rs2.getString("Prenom");
                    new InterfaceAdmin(name);
                } else if (rs3.next()){
                    String name = rs3.getString("Prenom");
                    new InterfaceReferent(name);
                }  else {
                            dispose();
                            new InterfaceConnect();
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
}