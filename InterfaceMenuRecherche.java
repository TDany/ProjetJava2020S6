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


public class InterfaceMenuRecherche extends JFrame{
    
    
    JComboBox choixSemaine = new JComboBox();
    JComboBox del = new JComboBox();
    

    
    public InterfaceMenuRecherche(){
        
        
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(8,7));
        
        JButton ButtonRetourEDT = new JButton("Retour à l'EDT");
        this.add(ButtonRetourEDT);
        ButtonRetourEDT.addActionListener(new ButtonRetourEDTListener());
        
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel Choix = new JLabel();
        Choix.setText("Que voulez vous faire ?");
        this.add(Choix);
        
        for (int i = 0; i < 18; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRechercherEnseignant = new JButton("Rechercher par enseignant");
        this.add(ButtonRechercherEnseignant);
        ButtonRechercherEnseignant.addActionListener(new ButtonRechercherEnseignantListener());
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRechercherGroupe = new JButton("Rechercher par groupe");
        this.add(ButtonRechercherGroupe);
        ButtonRechercherGroupe.addActionListener(new ButtonRechercherGroupeListener());
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRechercherSalle = new JButton("Rechercher par salle");
        this.add(ButtonRechercherSalle);
        ButtonRechercherSalle.addActionListener(new ButtonRechercherSalleListener());
        
        for (int i = 0; i < 22; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        
   }
    
    private class ButtonRechercherEnseignantListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            new InterfaceRechercherEnseignant();
            
        }
    }
    
    private class ButtonRechercherGroupeListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            new InterfaceRechercherGroupe();
            
        }
    }
    
    private class ButtonRechercherSalleListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            new InterfaceRechercherSalle();
            
        }
    }
    
    private class ButtonRetourEDTListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            new InterfaceAdmin(null);
            
        }
    }
}
