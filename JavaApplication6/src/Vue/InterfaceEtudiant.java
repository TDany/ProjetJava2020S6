/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.Seance;
import Modele.SeanceDAO;
import Modele.Utilisateur;
import Modele.UtilisateurDAO;

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
 * 
 * @author danytadrous
 */
public class InterfaceEtudiant extends JFrame {
    
    private String m_name;
    int m_semaine;
    
    Integer [] semaine = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
    JComboBox choixSemaine = new JComboBox(semaine);
    
    public InterfaceEtudiant(String name, int Semaine){
        m_name = name;
       m_semaine = Semaine;
        
        this.setTitle("INTERFACE PLANNING ETUDIANT");
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setLayout(new GridLayout(14,8));
        
        JLabel nameUser = new JLabel();
        nameUser.setText("Hi " + m_name);
        nameUser.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        this.add(nameUser);
        
        JLabel monEDT = new JLabel();
        monEDT.setText("Mon emploi du temps");
        this.add(monEDT);
        
        for (int i = 0; i < 6; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel PhrasePourChoixSemaine = new JLabel();
        PhrasePourChoixSemaine.setText("Quelle semaine ?");
        this.add(PhrasePourChoixSemaine);
        
        
        //JComboBox choixSemaine = new JComboBox(semaine);
        this.add(choixSemaine);
        
        for (int i = 0; i < 7; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JButton ButtonEDT = new JButton("Afficher mon EDT");
        this.add(ButtonEDT);
        ButtonEDT.addActionListener(new ButtonEDTListener());
        
        for (int i = 0; i < 8; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel leLundi = new JLabel();
        leLundi.setText("Lundi");
        leLundi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leLundi.setOpaque(true);
        leLundi.setBackground(Color.white);
        this.add(leLundi);
        
        JLabel leMardi = new JLabel();
        leMardi.setText("Mardi");
        leMardi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leMardi.setOpaque(true);
        leMardi.setBackground(Color.white);
        this.add(leMardi);
        
        JLabel leMercredi = new JLabel();
        leMercredi.setText("Mercredi");
        leMercredi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leMercredi.setOpaque(true);
        leMercredi.setBackground(Color.white);
        this.add(leMercredi);
        
        JLabel leJeudi = new JLabel();
        leJeudi.setText("Jeudi");
        leJeudi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leJeudi.setOpaque(true);
        leJeudi.setBackground(Color.white);
        this.add(leJeudi);
        
        JLabel leVendredi = new JLabel();
        leVendredi.setText("Vendredi");
        leVendredi.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        leVendredi.setOpaque(true);
        leVendredi.setBackground(Color.white);
        this.add(leVendredi);
        
        for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure89 = new JLabel();
        heure89.setText("8H - 9H");
        heure89.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure89.setOpaque(true);
        heure89.setBackground(Color.white);
        this.add(heure89);
        
        
        //ICICICICICICICIICICICICICI
        //JPanel p = new JPanel();
        this.add(PanelDescLundi8h00());
        this.add(PanelDescMardi8h00());
        this.add(PanelDescMercredi8h00());
        this.add(PanelDescJeudi8h00());
        this.add(PanelDescVendredi8h00());
        
        /*for (int i = 0; i < 4; i++) {
            JPanel lesCours89 = new JPanel();
            lesCours89.setBackground(Color.white);
            this.add(lesCours89);
        }*/
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure910 = new JLabel();
        heure910.setText("9H - 10H");
        heure910.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure910.setOpaque(true);
        heure910.setBackground(Color.white);
        this.add(heure910);
        
        for (int i = 0; i < 5; i++) {
            JLabel lesCours910 = new JLabel();
            lesCours910.setText("Pause");
            lesCours910.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            lesCours910.setOpaque(true);
            lesCours910.setBackground(Color.white);
            this.add(lesCours910);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1011 = new JLabel();
        heure1011.setText("10H - 11H");
        heure1011.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1011.setOpaque(true);
        heure1011.setBackground(Color.white);
        this.add(heure1011);
        
        
        this.add(PanelDescLundi10h00());
        this.add(PanelDescMardi10h00());
        this.add(PanelDescMercredi10h00());
        this.add(PanelDescJeudi10h00());
        this.add(PanelDescVendredi10h00());
        
        /*for (int i = 0; i < 5; i++) {
            JPanel lesCours1011 = new JPanel();
            lesCours1011.setBackground(Color.white);
            this.add(lesCours1011);
        }*/
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1112 = new JLabel();
        heure1112.setText("11H - 12H");
        heure1112.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1112.setOpaque(true);
        heure1112.setBackground(Color.white);
        this.add(heure1112);
        
        for (int i = 0; i < 5; i++) {
            JLabel lesCours1112 = new JLabel();
            lesCours1112.setText("Pause");
            lesCours1112.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            lesCours1112.setOpaque(true);
            lesCours1112.setBackground(Color.white);
            this.add(lesCours1112);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1213 = new JLabel();
        heure1213.setText("12H - 13H");
        heure1213.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1213.setOpaque(true);
        heure1213.setBackground(Color.white);
        this.add(heure1213);
        
        for (int i = 0; i < 5; i++) {
            JLabel lesCours1213 = new JLabel();
            lesCours1213.setText("Pause");
            lesCours1213.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            lesCours1213.setOpaque(true);
            lesCours1213.setBackground(Color.white);
            this.add(lesCours1213);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1314 = new JLabel();
        heure1314.setText("13H - 14H");
        heure1314.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1314.setOpaque(true);
        heure1314.setBackground(Color.white);
        this.add(heure1314);
        
        
        this.add(PanelDescLundi13h00());
        this.add(PanelDescMardi13h00());
        this.add(PanelDescMercredi13h00());
        this.add(PanelDescJeudi13h00());
        this.add(PanelDescVendredi13h00());
        
        /*for (int i = 0; i < 5; i++) {
            JPanel lesCours1314 = new JPanel();
            lesCours1314.setBackground(Color.white);
            this.add(lesCours1314);
        }*/
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1415 = new JLabel();
        heure1415.setText("14H - 15H");
        heure1415.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1415.setOpaque(true);
        heure1415.setBackground(Color.white);
        this.add(heure1415);
        
        
        this.add(PanelDescLundi14h00());
        this.add(PanelDescMardi14h00());
        this.add(PanelDescMercredi14h00());
        this.add(PanelDescJeudi14h00());
        this.add(PanelDescVendredi14h00());
        
       /* for (int i = 0; i < 5; i++) {
            JPanel lesCours1415 = new JPanel();
            lesCours1415.setBackground(Color.white);
            this.add(lesCours1415);
        }*/
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
         
        JLabel heure1516 = new JLabel();
        heure1516.setText("15H - 16H");
        heure1516.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1516.setOpaque(true);
        heure1516.setBackground(Color.white);
        this.add(heure1516);
        
        for (int i = 0; i < 5; i++) {
            JLabel lesCours1516 = new JLabel();
            lesCours1516.setText("Pause");
            lesCours1516.setFont(new Font("Times New Roman", Font.ITALIC, 25));
            lesCours1516.setOpaque(true);
            lesCours1516.setBackground(Color.white);
            this.add(lesCours1516);
        }
        
         for (int i = 0; i < 2; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
        
        JLabel heure1617 = new JLabel();
        heure1617.setText("16H - 17H");
        heure1617.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        heure1617.setOpaque(true);
        heure1617.setBackground(Color.white);
        this.add(heure1617);
        
        this.add(PanelDescLundi16h00());
        this.add(PanelDescMardi16h00());
        this.add(PanelDescMercredi16h00());
        this.add(PanelDescJeudi16h00());
        this.add(PanelDescVendredi16h00());
        
        /*for (int i = 0; i < 5; i++) {
            JPanel lesCours1617 = new JPanel();
            lesCours1617.setBackground(Color.white);
            this.add(lesCours1617);
        }*/
        
         for (int i = 0; i < 9; i++) {
            JPanel panel = new JPanel();
            this.add(panel);
        }
       
         
    }
    
    /*SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi8h00();
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel();
            Cours.setText(seances.get(i).getNomCours());
            this.add(Cours);
            JLabel Prof = new JLabel();
            Prof.setText(seances.get(i).getNomProf());
            this.add(Prof);
        }*/
    
    
    
    
    
    public JPanel PanelDescLundi8h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi8h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescLundi10h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi10h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    
    
    public JPanel PanelDescLundi13h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi13h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescLundi14h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi14h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescLundi16h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi16h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescMardi8h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi8h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescMardi10h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi10h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescMardi13h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi13h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    
    public JPanel PanelDescMardi14h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi14h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescMardi16h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMardi16h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescMercredi8h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMercredi8h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescMercredi10h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMercredi10h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescMercredi13h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMercredi13h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescMercredi14h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMercredi14h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescMercredi16h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceMercredi16h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescJeudi8h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceJeudi8h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescJeudi10h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceJeudi10h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescJeudi13h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceJeudi13h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescJeudi14h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceJeudi14h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescJeudi16h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceJeudi16h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescVendredi8h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceVendredi8h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescVendredi10h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceVendredi10h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescVendredi13h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceVendredi13h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    public JPanel PanelDescVendredi14h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceVendredi14h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    public JPanel PanelDescVendredi16h00(){
        
        JPanel pdesc = new JPanel();
        pdesc.setLayout(new GridLayout(3,2));
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceVendredi16h00(m_semaine);
        
        for (int i = 0; i < seances.size(); i++){
            JLabel Cours = new JLabel(seances.get(i).getNomCours());
            pdesc.add(Cours);
            JLabel TypeCours = new JLabel(seances.get(i).getNomTypeCours());
            pdesc.add(TypeCours);
            JLabel Prof = new JLabel(seances.get(i).getNomProf());
            pdesc.add(Prof);
            JLabel Groupe = new JLabel(seances.get(i).getNomGroupe());
            pdesc.add(Groupe);
            JLabel Salle = new JLabel(seances.get(i).getNomSalle());
            pdesc.add(Salle);
        }
        
        return pdesc;
    }
    
    
    private class ButtonEDTListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
            int Semaine = (int) choixSemaine.getSelectedItem();
            System.out.println("Semaine " +Semaine);
            System.out.println("Semaine " +m_semaine);
            m_semaine = Semaine;
             
            dispose();
            new InterfaceEtudiant(m_name, Semaine);
         

    }
    }
}