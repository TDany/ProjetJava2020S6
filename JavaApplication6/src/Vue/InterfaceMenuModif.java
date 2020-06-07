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


public class InterfaceMenuModif extends JFrame{
    
    
    JComboBox choixSemaine = new JComboBox();
    JComboBox del = new JComboBox();
    String[] semaine = {"1", "2", "3", "4", "5"};
    String[] salle = {"Toutes les salles", "E1", "E2", "E3", "E4", "E5"};
    String[] date = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi"};
    String[] heure = {"8h-9h", "9h-10h", "10h-11h", "11h-12h", "12h-13h", "13h-14h", "14h-15h", "15h-16h", "16h-17h", "17h-18h", "18h-19h"};
    String[] prof = {"DJOUDI", "COUDRAY", "SEGADO", "LECOR", "MOUHALI", "CRAMBES"};
    String[] matiere = {"JAVA", "ANGLAIS", "WEB"};
    String[] groupe = {"1", "2"};
    String[] classe = {"P101", "P102", "P201", "P202", "P301", "P302"};
    String[] site = {"E1", "E2", "E3", "E4", "E5"};
    String[] promo = {"2020", "2021", "2022"};
    String[] etat = {"1", "0"};

    
    public InterfaceMenuModif(){
        
        
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(8,7));
        
        JButton ButtonRetourEDT = new JButton("Retour à l'EDT");
        this.add(ButtonRetourEDT);
        ButtonRetourEDT.addActionListener(new InterfaceMenuModif.ButtonRetourEDTListener());
        
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
        
        JButton ButtonAjouter = new JButton("Ajouter");
        this.add(ButtonAjouter);
        ButtonAjouter.addActionListener(new ButtonAjouterListener());
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonSupprimer = new JButton("Supprimer");
        this.add(ButtonSupprimer);
        ButtonSupprimer.addActionListener(new ButtonSupprimerListener());
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonModifier = new JButton("Modifier");
        this.add(ButtonModifier);
        ButtonModifier.addActionListener(new ButtonModifierListener());
        
        for (int i = 0; i < 22; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        
   }
    
    private class ButtonAjouterListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            new InterfaceAjouter();
            
        }
    }
    
    private class ButtonModifierListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            
            
        }
    }
    
    private class ButtonSupprimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            new InterfaceSupprimer();
            
        }
    }
    
    private class ButtonRetourEDTListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            new InterfaceAdmin(null);
            
        }
    }
}
