/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.util.logging.*;
import java.sql.*;

/**
 *
 * @author danytadrous
 */
public class SeanceDAO extends DAO<Seance>{
    
    public SeanceDAO(){
        super();
    }
    
    public void ajouterSeance(){
        
        Seance seance = new Seance();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Seance(Semaine, Date, Heure_Debut, Heure_Fin, Etat) VALUES(?,?,?,?,?)");
            stmt.setInt(1, seance.getSemaine());
            stmt.setString(2, seance.getDate());
            stmt.setString(3, seance.getHeureDebut());
            stmt.setString(4, seance.getHeureFin());
            stmt.setInt(5, seance.getEtat());
            //STMT TO GET LES CLES ETRANGERES AUSSI 
            stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public void suppSeance(){
        
        Seance seance = new Seance();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Seance WHERE ID = ?");
            stmt.setInt(1, seance.getID());
            stmt.executeQuery();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
