/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Modele.Utilisateur;
import Vue.DaoFactory;

/**
 *
 * @author danytadrous
 */
public class UtilisateurDaoImpl implements UtilisateurDao{
    private DaoFactory daoFactory;
    
    UtilisateurDaoImpl(DaoFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    @Override
    public void ajouter(Utilisateur utilisateur){
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Utilisateur> lister() {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet result = null;
        
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            result = statement.executeQuery("SELECT nom, prenom FROM noms;");
            
            while (result.next()){
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
