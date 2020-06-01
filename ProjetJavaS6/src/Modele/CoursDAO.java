/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.util.*;
import java.util.logging.*;
import java.sql.*;

import Modele.Cours;

/**
 *
 * @author danytadrous
 */
public class CoursDAO extends DAO<Cours> {
    
    public CoursDAO(){
        super();
    }
    
    public Cours find(int ID){
        
        Cours cours = new Cours();
        
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Cours WHERE ID-"+ID);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.first())
                cours = new Cours(ID,rs.getString("NOM"));
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        
        return cours;
    }
    
    
    
    public void ajouterCours(){
        
        Cours cours = new Cours();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Cours(Nom) VALUES(?);");
            stmt.setString(1, cours.getNom());
            stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public List<Cours> lister() {
        List<Cours> courss = new ArrayList<Cours>();
        
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT Nom FROM Cours");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()){
                String nom = rs.getString("Nom");
                
                Cours cours = new Cours();
                cours.setNom(nom);
                courss.add(cours);
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    public void suppCours(){
        
        Cours cours = new Cours();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Cours WHERE Nom = ?");
            stmt.setString(1, cours.getNom());
            stmt.executeQuery();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
}
