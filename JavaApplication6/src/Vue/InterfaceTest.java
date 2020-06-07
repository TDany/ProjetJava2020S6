/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Modele.DAO;
import Modele.Seance;
import Modele.SeanceDAO;
import Modele.Utilisateur;
import Modele.UtilisateurDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author danytadrous
 */
public class InterfaceTest extends JFrame {
   
    public InterfaceTest() {
       
        System.out.println("hey");
        
        //SeanceDAO s = null;
        //s.afficherSeance();
       
        
        /*UtilisateurDAO u = new UtilisateurDAO();
        ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        utilisateurs = u.afficherUtilisateur();
        
        for (int i = 0; i < utilisateurs.size(); i++){
            System.out.println(utilisateurs.get(i).getID());
            System.out.println(utilisateurs.get(i).getEmail());
            System.out.println(utilisateurs.get(i).getNom());
            System.out.println(utilisateurs.get(i).getPrenom());
            System.out.println("DROIT : " + utilisateurs.get(i).getDroit());
        }*/
        
        SeanceDAO S1 = new SeanceDAO();
        ArrayList<Seance> seances = new ArrayList<Seance>();
        seances = S1.afficherSeanceLundi8h00(2);
        
        for (int i = 0; i < seances.size(); i++){
            System.out.println(" ID : " + seances.get(i).getID());
            System.out.println(" HeureDebut : " +seances.get(i).getHeureDebut());
            System.out.println(" HeureFin : " +seances.get(i).getHeureFin());
            System.out.println(" Semaine : " +seances.get(i).getSemaine());
            System.out.println(" Date : " +seances.get(i).getDate());
            System.out.println(" Etat : " +seances.get(i).getEtat());
            System.out.println(" Type : " +seances.get(i).getNomTypeCours());
            System.out.println(" Cours : " +seances.get(i).getNomCours());
            System.out.println(" PROF : " +seances.get(i).getNomProf());
            System.out.println(" SALLE : " +seances.get(i).getNomSalle());
            System.out.println(" GROUPE : " +seances.get(i).getNomGroupe());
        }
        
        
        
                        
                        
        
         
           //u.suppUtilisateur(3);
          //System.out.println(u.afficherUtilisateur().getEmail());
        
    }
    
}
