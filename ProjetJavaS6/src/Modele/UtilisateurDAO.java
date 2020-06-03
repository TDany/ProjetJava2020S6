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
public class UtilisateurDAO extends DAO<Utilisateur>{
    
    public UtilisateurDAO(){
        super();
    }
    
    public void ajouterUtilisateur(){
        
        Utilisateur utilisateur = new Utilisateur();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("INSERT INTO Utilisateur (Email, Passwd, Nom, Prenom) VALUES(?,?,?,?)");
            stmt.setString(1, utilisateur.getEmail());
            stmt.setString(2, utilisateur.getPasswd());
            stmt.setString(3, utilisateur.getNom());
            stmt.setString(4, utilisateur.getPrenom());
            //STMT TO GET LES CLES ETRANGERES AUSSI 
            stmt.executeUpdate();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public void suppUtilisateur(){
        
        Utilisateur utilisateur = new Utilisateur();
        
        try {
            
            PreparedStatement stmt = connect.prepareStatement("DELETE FROM Utilisateur WHERE ID = ?");
            stmt.setInt(1, utilisateur.getID());
            stmt.executeQuery();
            
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public Utilisateur afficherUtilisateur(){
        
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        ResultSet rs = null;
        
        try {
            PreparedStatement stmt = connect.prepareStatement("SELECT * FROM Utilisateur");
            rs = stmt.executeQuery();
             
            while (rs.next()){
                int ID = rs.getInt("ID");
                String Email = rs.getString("Email");
                String Passwd = rs.getString("Passwd");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                int Droit = rs.getInt("Droit");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setID(ID);
                utilisateur.setEmail(Email);
                utilisateur.setPasswd(Passwd);
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                utilisateur.setDroit(Droit);
                
                utilisateurs.add(utilisateur);
                
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
        
    }
    
    
}
