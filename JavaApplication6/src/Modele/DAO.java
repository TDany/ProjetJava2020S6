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
public abstract class DAO<D> {
    protected Connection connect = null;
    private ResultSet rs;
    private ResultSetMetaData rsMeta;
    
    public DAO(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e){
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, e);
            }
            //DATABASE PARAMETER
            String url = "jdbc:mysql://localhost:8889/ProjetJava2020";
            String user = "root";
            String password = "root";
            //CONNECTION TO THE DATABASE
            connect = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    //public abstract D find(int ID);
    
}
