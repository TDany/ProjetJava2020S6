/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Seance;
import Modele.SeanceDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 *
 * @author admin
 */
public class InterfaceRechercherEnseignant extends JFrame{
    
    JComboBox prof = new JComboBox();
    
    public InterfaceRechercherEnseignant(){
        
       
        ArrayList<Seance> seances = new ArrayList<Seance>();
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(5,8));
        
        //BUTTON FOR SEARCH
        JButton ButtonRecherche = new JButton("Recherche");
        this.add(ButtonRecherche);
        ButtonRecherche.addActionListener(new ButtonRechercheListener());
        
        ArrayList<String> listeprof = new ArrayList<String>();
        Connection connn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projetjava2020";
            String user = "root";
            String password = "";

            connn = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn.prepareStatement("SELECT Nom FROM utilisateur WHERE Droit = 3");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listeprof.add(rs.getString(1));
            }

            connn.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (connn != null) {
                    connn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        prof = new JComboBox<>(listeprof.toArray());
        this.add(prof);

        // END OF BUTTON FOR DELETE ITEM
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRetour = new JButton("Retour");
        this.add(ButtonRetour);
        ButtonRetour.addActionListener(new ButtonRetourListener());
        
        for (int i = 0; i < 44; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
   }

    private class ButtonRechercheListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            String Prof = (String) prof.getSelectedItem();
            
            Connection conn1 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projetjava2020";
            String user = "root";
            String password = "";

            conn1 = DriverManager.getConnection(url, user, password);
               
            PreparedStatement stmt = conn1.prepareStatement("Select ID FROM utilisateur WHERE Nom='"+Prof+"'");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){

                
                int ID_Prof = rs.getInt("ID");
                new InterfaceCoursEnseignant(ID_Prof);
            }

            conn1.close();

        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                if (conn1 != null) {
                    conn1.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
            
        }
    }
    
    private class ButtonRetourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            new InterfaceMenuRecherche();
            
        }
    }
}
