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
public class InterfaceCoursSalle extends JFrame{
    
    private int id_salle;
    
    public InterfaceCoursSalle(int ID_Salle){
        
        id_salle = ID_Salle;
        SeanceDAO S2 = new SeanceDAO();
        ArrayList<Seance> seancesalle = new ArrayList<Seance>();
        this.setTitle("INTERFACE AFFICHAGE COURS GROUPE");
        this.setSize(1000, 1000);
        this.setVisible(true);
        int nbLignes = seancesalle.size();
        this.setLayout(new GridLayout(nbLignes,8));
        
        seancesalle = S2.afficherSeanceSalle(id_salle);
        //Affichage des cours correspondant au groupe choisi
        for (int i = 0; i < seancesalle.size(); i++){
            JLabel ID = new JLabel();
            ID.setText(" ID : " + seancesalle.get(i).getID());
            this.add(ID);
            
            JLabel HeureDebut = new JLabel();
            HeureDebut.setText(" Heure debut : " + seancesalle.get(i).getHeureDebut());
            this.add(HeureDebut);
            
            JLabel HeureFin = new JLabel();
            HeureFin.setText(" Heure fin : " + seancesalle.get(i).getHeureFin());
            this.add(HeureFin);
            
            JLabel Semaine = new JLabel();
            Semaine.setText(" Semaine : " + seancesalle.get(i).getSemaine());
            this.add(Semaine);
            
            JLabel Date = new JLabel();
            Date.setText(" Date : " + seancesalle.get(i).getDate());
            this.add(Date);
            
            JLabel Etat = new JLabel();
            Etat.setText(" Etat : " + seancesalle.get(i).getEtat());
            this.add(Etat);
            
            JLabel Type = new JLabel();
            Type.setText(" Type : " + seancesalle.get(i).getNomTypeCours());
            this.add(Type);
            
            JLabel Cours = new JLabel();
            Cours.setText(" Cours : " + seancesalle.get(i).getNomCours());
            this.add(Cours);
           
        }
        
        
   }

    
}
