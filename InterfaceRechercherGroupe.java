/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Seance;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class InterfaceRechercherGroupe extends JFrame{
    
    JComboBox groupe = new JComboBox();
    
    public InterfaceRechercherGroupe(){
        ArrayList<Seance> seances = new ArrayList<Seance>();
        this.setTitle("INTERFACE RECHERCHER GROUPE");
        this.setSize(1000, 1000);
        this.setVisible(true);
        int nbLignes = seances.size();
        this.setLayout(new GridLayout(5,8));
        
        //BUTTON FOR SEARCH
        JButton ButtonRecherche = new JButton("Recherche");
        this.add(ButtonRecherche);
        ButtonRecherche.addActionListener(new ButtonRechercheListener());
        
        ArrayList<String> listegroupe = new ArrayList<String>();
        Connection connn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projetjava2020";
            String user = "root";
            String password = "";

            connn = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn.prepareStatement("SELECT Nom FROM groupe");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listegroupe.add(rs.getString(1));
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

        groupe = new JComboBox<>(listegroupe.toArray());
        this.add(groupe);

        // END OF BUTTON FOR SEARCH
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRetour = new JButton("Retour");
        this.add(ButtonRetour);
        ButtonRetour.addActionListener(new ButtonRetourListener());
        
        for (int i = 0; i < 40; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
    }
    
    private class ButtonRechercheListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            String Groupe = (String) groupe.getSelectedItem();
            
            Connection conn1 = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projetjava2020";
            String user = "root";
            String password = "";

            conn1 = DriverManager.getConnection(url, user, password);
               
            PreparedStatement stmt = conn1.prepareStatement("Select ID FROM groupe WHERE Nom='"+Groupe+"'");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){

                
                int ID_Groupe = rs.getInt("ID");
                new InterfaceCoursGroupe(ID_Groupe);
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
