/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author danytadrous
 */
public class DaoFactory {
    private String url;
    private String username;
    private String password;
    
    DaoFactory (String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public static DaoFactory getInstance(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            
        }
        
        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:8889/ProjetJava2020", "root", "root");
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    
    //ON RECUPERE ICI LE DAO - EN GROS LES CLASSES DES TABLES DE LA BDD
    public UtilisateurDao getUtilisateurDao() {
        return new UtilisateurDaoImpl(this);
    }

    
}
