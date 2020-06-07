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
public class InterfaceSupprimer extends JFrame{
    
    JComboBox del = new JComboBox();
    
    public InterfaceSupprimer(){
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        int nbLignes = seances.size();
        nbLignes= nbLignes++;
        this.setLayout(new GridLayout(nbLignes,8));
        
        //BUTTON FOR DELETE ITEM
        JButton ButtonSupprimer = new JButton("Supprimer");
        this.add(ButtonSupprimer);
        ButtonSupprimer.addActionListener(new InterfaceSupprimer.ButtonSupprimerListener());
        ArrayList<Integer> listedel = new ArrayList<Integer>();

        Connection connn = null;
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";

            connn = DriverManager.getConnection(url, user, password);
                

            PreparedStatement stmt = connn.prepareStatement("SELECT ID FROM seance");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                listedel.add(rs.getInt(1));
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

        del = new JComboBox<>(listedel.toArray());
        this.add(del);

        // END OF BUTTON FOR DELETE ITEM
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRetour = new JButton("Retour");
        this.add(ButtonRetour);
        ButtonRetour.addActionListener(new InterfaceSupprimer.ButtonRetourListener());
        
        for (int i = 0; i < 4; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        
        seances = S1.afficherSeance();
        
        for (int i = 0; i < seances.size(); i++){
            
            JLabel ID = new JLabel();
            ID.setText(" ID : " + seances.get(i).getID());
            this.add(ID);
            
            JLabel HeureDebut = new JLabel();
            HeureDebut.setText(" Heure debut : " + seances.get(i).getHeureDebut());
            this.add(HeureDebut);
            
            JLabel HeureFin = new JLabel();
            HeureFin.setText(" Heure fin : " + seances.get(i).getHeureFin());
            this.add(HeureFin);
            
            JLabel Semaine = new JLabel();
            Semaine.setText(" Semaine : " + seances.get(i).getSemaine());
            this.add(Semaine);
            
            JLabel Date = new JLabel();
            Date.setText(" Date : " + seances.get(i).getDate());
            this.add(Date);
            
            JLabel Etat = new JLabel();
            Etat.setText(" Etat : " + seances.get(i).getEtat());
            this.add(Etat);
            
            JLabel Type = new JLabel();
            Type.setText(" Type : " + seances.get(i).getNomTypeCours());
            this.add(Type);
            
            JLabel Cours = new JLabel();
            Cours.setText(" Cours : " + seances.get(i).getNomCours());
            this.add(Cours);
            
            //System.out.println(" ID : " + seances.get(i).getID());
            //System.out.println(" HeureDebut : " +seances.get(i).getHeureDebut());
            //System.out.println(" HeureFin : " +seances.get(i).getHeureFin());
            //System.out.println(" Semaine : " +seances.get(i).getSemaine());
            //System.out.println(" Date : " +seances.get(i).getDate());
            //System.out.println(" Etat : " +seances.get(i).getEtat());
            //System.out.println(" Type : " +seances.get(i).getIDType());
            //System.out.println(" Cours : " +seances.get(i).getIDCours());
        }
        
        
   }

    private class ButtonSupprimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            int ID;
            ID = (int) del.getSelectedItem();
            SeanceDAO s = new SeanceDAO();
            s.suppSeance(ID);
            dispose();
            new InterfaceSupprimer();
            
        }
    }
    
    private class ButtonRetourListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            dispose();
            new InterfaceMenuModif();
            
        }
    }
}
