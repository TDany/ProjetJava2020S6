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
public class InterfaceCoursEnseignant extends JFrame{
    
    private int id_prof;
    
    public InterfaceCoursEnseignant(int ID_Prof){
        
        id_prof = ID_Prof;
        SeanceDAO S2 = new SeanceDAO();
        ArrayList<Seance> seanceprof = new ArrayList<Seance>();
        this.setTitle("INTERFACE PLANNING MODIF");
        this.setSize(1000, 1000);
        this.setVisible(true);
        int nbLignes = seanceprof.size();
        this.setLayout(new GridLayout(nbLignes,8));
        
        seanceprof = S2.afficherSeanceProf(id_prof);
        
        for (int i = 0; i < seanceprof.size(); i++){
            JLabel ID = new JLabel();
            ID.setText(" ID : " + seanceprof.get(i).getID());
            this.add(ID);
            
            JLabel HeureDebut = new JLabel();
            HeureDebut.setText(" Heure debut : " + seanceprof.get(i).getHeureDebut());
            this.add(HeureDebut);
            
            JLabel HeureFin = new JLabel();
            HeureFin.setText(" Heure fin : " + seanceprof.get(i).getHeureFin());
            this.add(HeureFin);
            
            JLabel Semaine = new JLabel();
            Semaine.setText(" Semaine : " + seanceprof.get(i).getSemaine());
            this.add(Semaine);
            
            JLabel Date = new JLabel();
            Date.setText(" Date : " + seanceprof.get(i).getDate());
            this.add(Date);
            
            JLabel Etat = new JLabel();
            Etat.setText(" Etat : " + seanceprof.get(i).getEtat());
            this.add(Etat);
            
            JLabel Type = new JLabel();
            Type.setText(" Type : " + seanceprof.get(i).getNomTypeCours());
            this.add(Type);
            
            JLabel Cours = new JLabel();
            Cours.setText(" Cours : " + seanceprof.get(i).getNomCours());
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

    
}
