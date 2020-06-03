/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication6;

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
public class InterfaceModif extends JFrame{
    
    
    JComboBox choixSemaine = new JComboBox();
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

    
    public InterfaceModif(){
        
        
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(16,8));
        
        
        for (int i = 0; i < 17; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel SemaineRecherche = new JLabel();
        SemaineRecherche.setText("Semaine");
        this.add(SemaineRecherche);
        
        JLabel DateRecherche = new JLabel();
        DateRecherche.setText("Date");
        this.add(DateRecherche);
        
        JLabel HeureRecherche = new JLabel();
        HeureRecherche.setText("Heure");
        this.add(HeureRecherche);
        
        JLabel ProfRecherche = new JLabel();
        ProfRecherche.setText("Prof");
        this.add(ProfRecherche);
        
        JLabel SalleRecherche = new JLabel();
        SalleRecherche.setText("Salle");
        this.add(SalleRecherche);
        
        JLabel EtatRecherche = new JLabel();
        EtatRecherche.setText("Etat");
        this.add(EtatRecherche);
        
        for (int i = 0; i < 1; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel PhrasePourChoixCours = new JLabel();
        PhrasePourChoixCours.setText("Selectionnez un cours");
        this.add(PhrasePourChoixCours);
        
        
        JComboBox choixSemaine = new JComboBox(semaine);
        this.add(choixSemaine);
        
        JComboBox choixDate = new JComboBox(date);
        this.add(choixDate);
        
        JComboBox choixHeure = new JComboBox(heure);
        this.add(choixHeure);
        
        JComboBox choixProf = new JComboBox(prof);
        this.add(choixProf);
        
        JComboBox choixSalle = new JComboBox(salle);
        this.add(choixSalle);
        
        JComboBox choixEtat = new JComboBox(etat);
        this.add(choixEtat);
        
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel MatiereRecherche = new JLabel();
        MatiereRecherche.setText("Matiere");
        this.add(MatiereRecherche);
        
        JLabel GroupeRecherche = new JLabel();
        GroupeRecherche.setText("Groupe");
        this.add(GroupeRecherche);
        
        JLabel ClasseRecherche = new JLabel();
        ClasseRecherche.setText("Classe");
        this.add(ClasseRecherche);
        
        JLabel PromoRecherche = new JLabel();
        PromoRecherche.setText("Promo");
        this.add(PromoRecherche);
        
        JLabel SiteRecherche = new JLabel();
        SiteRecherche.setText("Site");
        this.add(SiteRecherche);
        
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
       
        JComboBox choixMatiere = new JComboBox(matiere);
        this.add(choixMatiere);
        
        JComboBox choixGroupe = new JComboBox(groupe);
        this.add(choixGroupe);
        
        JComboBox choixClasse = new JComboBox(classe);
        this.add(choixClasse);
        
        JComboBox choixPromo = new JComboBox(promo);
        this.add(choixPromo);
        
        JComboBox choixSite = new JComboBox(site);
        this.add(choixSite);
        
        for (int i = 0; i < 11; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonAjouter = new JButton("Ajouter");
        this.add(ButtonAjouter);
        ButtonAjouter.addActionListener(new ButtonAjouterListener());
        
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonSupprimer = new JButton("Supprimer");
        this.add(ButtonSupprimer);
        ButtonSupprimer.addActionListener(new ButtonSupprimerListener());
        
        for (int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel Modifier = new JLabel();
        Modifier.setText("Modifier ici");
        this.add(Modifier);
        
        JLabel SemaineRecherche2 = new JLabel();
        SemaineRecherche2.setText("Semaine");
        this.add(SemaineRecherche2);
        
        JLabel DateRecherche2 = new JLabel();
        DateRecherche2.setText("Date");
        this.add(DateRecherche2);
        
        JLabel HeureRecherche2 = new JLabel();
        HeureRecherche2.setText("Heure");
        this.add(HeureRecherche2);
        
        JLabel ProfRecherche2 = new JLabel();
        ProfRecherche2.setText("Prof");
        this.add(ProfRecherche2);
        
        JLabel SalleRecherche2 = new JLabel();
        SalleRecherche2.setText("Salle");
        this.add(SalleRecherche2);
        
        JLabel EtatRecherche2 = new JLabel();
        EtatRecherche2.setText("Etat");
        this.add(EtatRecherche2);
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JComboBox choixSemaine2 = new JComboBox(semaine);
        this.add(choixSemaine2);
        
        JComboBox choixDate2 = new JComboBox(date);
        this.add(choixDate2);
        
        JComboBox choixHeure2 = new JComboBox(heure);
        this.add(choixHeure2);
        
        JComboBox choixProf2 = new JComboBox(prof);
        this.add(choixProf2);
        
        JComboBox choixSalle2 = new JComboBox(salle);
        this.add(choixSalle2);
        
        JComboBox choixEtat2 = new JComboBox(etat);
        this.add(choixEtat2);
        
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel MatiereRecherche2 = new JLabel();
        MatiereRecherche2.setText("Matiere");
        this.add(MatiereRecherche2);
        
        JLabel GroupeRecherche2 = new JLabel();
        GroupeRecherche2.setText("Groupe");
        this.add(GroupeRecherche2);
        
        JLabel ClasseRecherche2 = new JLabel();
        ClasseRecherche2.setText("Classe");
        this.add(ClasseRecherche2);
        
        JLabel PromoRecherche2 = new JLabel();
        PromoRecherche2.setText("Promo");
        this.add(PromoRecherche2);
        
        JLabel SiteRecherche2 = new JLabel();
        SiteRecherche2.setText("Site");
        this.add(SiteRecherche2);
        
        for (int i = 0; i < 3; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
       
        JComboBox choixMatiere2 = new JComboBox(matiere);
        this.add(choixMatiere2);
        
        JComboBox choixGroupe2 = new JComboBox(groupe);
        this.add(choixGroupe2);
        
        JComboBox choixClasse2 = new JComboBox(classe);
        this.add(choixClasse2);
        
        JComboBox choixPromo2 = new JComboBox(promo);
        this.add(choixPromo2);
        
        JComboBox choixSite2 = new JComboBox(site);
        this.add(choixSite2);
        
        for (int i = 0; i < 13; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonModifier = new JButton("Modifier");
        this.add(ButtonModifier);
        ButtonModifier.addActionListener(new ButtonModifierListener());
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonRetourEDT = new JButton("Retour à l'EDT");
        this.add(ButtonRetourEDT);
        ButtonRetourEDT.addActionListener(new ButtonRetourEDTListener());
        
        for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
   }
    
    private class ButtonAjouterListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            
            
        }
    }
    
    private class ButtonModifierListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            
            
        }
    }
    
    private class ButtonSupprimerListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            
            
        }
    }
    
    private class ButtonRetourEDTListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            
            new InterfaceAdmin(null);
            
        }
    }
}
